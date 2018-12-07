package com.finbourne.lusid.integration;

import com.finbourne.lusid.ApiClient;
import com.finbourne.lusid.ApiException;
import com.finbourne.lusid.api.AggregationApi;
import com.finbourne.lusid.api.AnalyticsStoresApi;
import com.finbourne.lusid.api.TransactionPortfoliosApi;
import com.finbourne.lusid.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AggregationTests {

    private final String AGGREGATION_KEY = "Holding/default/PV";
    private final String GROUPBY_KEY = "Instrument/default/Name";
    private final String INSTRUMENT_KEY = "instrumentId";
    private final String PRICE_KEY = "price";
    private final String DATE_KEY = "date";

    private final OffsetDateTime EFFECTIVE_DATE = OffsetDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

    private AnalyticsStoresApi analyticsStoresApi;
    private TransactionPortfoliosApi transactionPortfoliosApi;
    private AggregationApi  aggregationApi;
    private List<String> instrumentIds;

    @Before
    public void setUp() throws Exception
    {
        File configJson = new TestConfigurationLoader().loadConfiguration("secrets.json");
        ApiClient apiClient = new ApiClientBuilder(configJson).build();

        this.transactionPortfoliosApi = new TransactionPortfoliosApi(apiClient);
        this.analyticsStoresApi = new AnalyticsStoresApi(apiClient);
        this.aggregationApi = new AggregationApi(apiClient);

        //  ensure instruments are created and exist in LUSID
        this.instrumentIds = new InstrumentLoader().loadInstruments();
        this.instrumentIds.sort(Comparator.naturalOrder());
    }

    @Test
    public void Run_Aggregation_With_StockIn() throws ApiException
    {
        Run_Aggregation(
                () -> {
                    //  transaction details
                    List<Map<String, ? extends Serializable>> transactionSpecs = List.of(
                            Map.of(INSTRUMENT_KEY, this.instrumentIds.get(0), PRICE_KEY, 101.0, DATE_KEY, this.EFFECTIVE_DATE),
                            Map.of(INSTRUMENT_KEY, this.instrumentIds.get(1), PRICE_KEY, 102.0, DATE_KEY, this.EFFECTIVE_DATE),
                            Map.of(INSTRUMENT_KEY, this.instrumentIds.get(2), PRICE_KEY, 103.0, DATE_KEY, this.EFFECTIVE_DATE)
                    );

                    //  create the transaction requests based on the specs
                    return transactionSpecs
                            .stream()
                            .map(s -> buildTransaction((String) s.get(INSTRUMENT_KEY), (Double) s.get(PRICE_KEY), (OffsetDateTime) s.get(DATE_KEY), "StockIn"))
                            .collect(Collectors.toList());
                },
                results -> {
                        assertEquals(3, results.size());
                        assertEquals(20000.0, results.get(0).get(AGGREGATION_KEY));
                        assertEquals(30000.0, results.get(1).get(AGGREGATION_KEY));
                        assertEquals(10000.0, results.get(2).get(AGGREGATION_KEY));
                    }
                );
    }

    @Test
    public void Run_Aggregation_With_Buy() throws ApiException
    {
        Run_Aggregation(
                () -> {
                    //  transaction details
                    List<Map<String, ? extends Serializable>> transactionSpecs = List.of(
                            Map.of(INSTRUMENT_KEY, this.instrumentIds.get(0), PRICE_KEY, 101.0, DATE_KEY, this.EFFECTIVE_DATE),
                            Map.of(INSTRUMENT_KEY, this.instrumentIds.get(1), PRICE_KEY, 102.0, DATE_KEY, this.EFFECTIVE_DATE),
                            Map.of(INSTRUMENT_KEY, this.instrumentIds.get(2), PRICE_KEY, 103.0, DATE_KEY, this.EFFECTIVE_DATE)
                    );

                    //  create the transaction requests based on the specs
                    List<TransactionRequest> requests = transactionSpecs
                            .stream()
                            .map(s -> buildTransaction((String) s.get(INSTRUMENT_KEY), (Double) s.get(PRICE_KEY), (OffsetDateTime) s.get(DATE_KEY), "Buy"))
                            .collect(Collectors.toList());

                    //  add the starting cash
                    requests.add(new TransactionRequest()
                            .transactionId(UUID.randomUUID().toString())
                            .type("FundsIn")
                            .instrumentUid("CCY_GBP")
                            .totalConsideration(new CurrencyAndAmount().currency("GBP").amount(0.0))
                            .transactionDate(EFFECTIVE_DATE)
                            .settlementDate(EFFECTIVE_DATE)
                            .units(30600.0)
                            .source(TransactionRequest.SourceEnum.CLIENT)
                    );

                    return requests;
                },
                results -> {
                    assertEquals(3, results.size());
                    assertEquals(20000.0, results.get(0).get(AGGREGATION_KEY));
                    assertEquals(30000.0, results.get(1).get(AGGREGATION_KEY));
                    assertEquals(10000.0, results.get(2).get(AGGREGATION_KEY));
                });
    }

    public void Run_Aggregation(
            Supplier<List<TransactionRequest>> createTransactionRequests,
            Consumer<List<Map<String, Object>>> validateResults
    ) throws ApiException
    {
        String uuid = UUID.randomUUID().toString();
        String scope = "finbourne";

        //  portfolio request
        String originalPortfolioId = String.format("Id-%s", uuid);
        CreateTransactionPortfolioRequest request = new CreateTransactionPortfolioRequest()
                .displayName(String.format("Portfolio-%s", uuid))
                .code(originalPortfolioId)
                .baseCurrency("GBP")
                .created(EFFECTIVE_DATE);

        //  create portfolio
        Portfolio portfolio = this.transactionPortfoliosApi.createPortfolio(scope, request);

        assertEquals(portfolio.getId().getCode(), originalPortfolioId);

        String portfolioId = portfolio.getId().getCode();

        //  add the transactions
        List<TransactionRequest> transactionRequests = createTransactionRequests.get();

        //  add the trades
        this.transactionPortfoliosApi.upsertTransactions(scope, portfolioId, transactionRequests);

        //  set up the prices used for the aggregation in the analytic stores
        ResourceListOfAnalyticStoreKey analyticsStores = this.analyticsStoresApi.listAnalyticStores(null, null, null, null, null);
        AnalyticStoreKey analyticStore = analyticsStores.getValues()
                .stream()
                .filter(ask -> ask.getDate().equals(EFFECTIVE_DATE))
                .findAny()
                .orElse(null);

        if (analyticStore == null) {
            //  create the analytic store
            CreateAnalyticStoreRequest  createAnalyticStoreRequest = new CreateAnalyticStoreRequest().scope(scope).date(EFFECTIVE_DATE);
            this.analyticsStoresApi.createAnalyticStore(createAnalyticStoreRequest);
        }

        List<InstrumentAnalytic>    prices = List.of(
                new InstrumentAnalytic().instrumentUid(this.instrumentIds.get(0)).value(100.0),
                new InstrumentAnalytic().instrumentUid(this.instrumentIds.get(1)).value(200.0),
                new InstrumentAnalytic().instrumentUid(this.instrumentIds.get(2)).value(300.0)
        );

        //  add prices from the aggregation
        this.analyticsStoresApi.setAnalytics(scope, EFFECTIVE_DATE.getYear(), EFFECTIVE_DATE.getMonthValue(), EFFECTIVE_DATE.getDayOfMonth(), prices);

        AggregationRequest  aggregationRequest = new AggregationRequest()
                .recipeId(new ResourceId().scope(scope).code("default"))
                .metrics(List.of(
                        new AggregateSpec().key(AGGREGATION_KEY).op(AggregateSpec.OpEnum.PROPORTION),
                        new AggregateSpec().key(AGGREGATION_KEY).op(AggregateSpec.OpEnum.SUM)
                ))
                .groupBy(List.of(GROUPBY_KEY))
                .effectiveAt(this.EFFECTIVE_DATE);

        //  do the aggregation
        ListAggregationResponse aggregationResponse = this.aggregationApi.getAggregationByPortfolio(scope, portfolioId, aggregationRequest, null, null, null);

        aggregationResponse.getData().sort((o1, o2) -> {
            String name1 = (String)o1.get(GROUPBY_KEY);
            String name2 = (String)o2.get(GROUPBY_KEY);

            return name1.compareTo(name2);
        });

        validateResults.accept(aggregationResponse.getData());

        /**
         * The aggregation response contains a schema property which describes the data returned.
         * This includes the aggregated values and description of the types.
         */
        ResultDataSchema    resultSchema = aggregationResponse.getDataSchema();

        for (Map<String, Object> aggregation : aggregationResponse.getData()) {
            for (String column : resultSchema.getPropertySchema().keySet()) {
                System.out.println(column + ":\t" + aggregation.get(column) + "\t");
            }
            System.out.println();
        }
    }

    private TransactionRequest buildTransaction(String instrumentId, Double price, OffsetDateTime tradeDate, String type)
    {
        return new TransactionRequest()
                .transactionId(UUID.randomUUID().toString())
                .type(type)
                .instrumentUid(instrumentId)
                .totalConsideration(new CurrencyAndAmount().currency("GBP").amount(100.0 * price))
                .transactionDate(tradeDate)
                .settlementDate(tradeDate)
                .units(100.0)
                .transactionPrice(new TransactionPrice().price(price))
                .source(TransactionRequest.SourceEnum.CLIENT);
    }

}