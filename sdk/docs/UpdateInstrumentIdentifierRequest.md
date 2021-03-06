
# UpdateInstrumentIdentifierRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | The type of the identifier to upsert. This must be one of the code types marked as   allowable for instrument identifiers. |  [optional]
**value** | **String** | The value of the identifier. If set to &#x60;null&#x60;, this will remove the identifier completely.  Note that, if an instrument only has one identifier, it is an error to remove this. |  [optional]
**effectiveAt** | [**OffsetDateTime**](OffsetDateTime.md) | The date at which the identifier modification is to be effective from. If unset, will  default to &#x60;now&#x60;. |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
LUSIDINSTRUMENTID | &quot;LusidInstrumentId&quot;
REUTERSASSETID | &quot;ReutersAssetId&quot;
CINS | &quot;CINS&quot;
ISIN | &quot;Isin&quot;
SEDOL | &quot;Sedol&quot;
CUSIP | &quot;Cusip&quot;
TICKER | &quot;Ticker&quot;
CLIENTINTERNAL | &quot;ClientInternal&quot;
FIGI | &quot;Figi&quot;
COMPOSITEFIGI | &quot;CompositeFigi&quot;
SHARECLASSFIGI | &quot;ShareClassFigi&quot;
WERTPAPIER | &quot;Wertpapier&quot;
RIC | &quot;RIC&quot;
QUOTEPERMID | &quot;QuotePermId&quot;



