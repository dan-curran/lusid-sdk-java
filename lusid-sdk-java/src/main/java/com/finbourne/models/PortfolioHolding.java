/**
 * Copyright © 2018 FINBOURNE TECHNOLOGY LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package com.finbourne.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PortfolioHolding model.
 */
public class PortfolioHolding {
    /**
     * Unique instrument identifier.
     */
    @JsonProperty(value = "instrumentUid")
    private String instrumentUid;

    /**
     * The properties property.
     */
    @JsonProperty(value = "properties")
    private List<Property> properties;

    /**
     * Type of holding, eg Position, Balance, CashCommitment, Receivable,
     * ForwardFX.
     */
    @JsonProperty(value = "holdingType", required = true)
    private String holdingType;

    /**
     * Quantity of holding.
     */
    @JsonProperty(value = "units")
    private Double units;

    /**
     * Settled quantity of holding.
     */
    @JsonProperty(value = "settledUnits")
    private Double settledUnits;

    /**
     * Book cost of holding in transaction currency.
     */
    @JsonProperty(value = "cost")
    private Double cost;

    /**
     * Book cost of holding in portfolio currency.
     */
    @JsonProperty(value = "costPortfolioCcy")
    private Double costPortfolioCcy;

    /**
     * If this is commitment-type holding, the transaction behind it.
     */
    @JsonProperty(value = "transaction")
    private Transaction transaction;

    /**
     * Get unique instrument identifier.
     *
     * @return the instrumentUid value
     */
    public String instrumentUid() {
        return this.instrumentUid;
    }

    /**
     * Set unique instrument identifier.
     *
     * @param instrumentUid the instrumentUid value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withInstrumentUid(String instrumentUid) {
        this.instrumentUid = instrumentUid;
        return this;
    }

    /**
     * Get the properties value.
     *
     * @return the properties value
     */
    public List<Property> properties() {
        return this.properties;
    }

    /**
     * Set the properties value.
     *
     * @param properties the properties value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withProperties(List<Property> properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get type of holding, eg Position, Balance, CashCommitment, Receivable, ForwardFX.
     *
     * @return the holdingType value
     */
    public String holdingType() {
        return this.holdingType;
    }

    /**
     * Set type of holding, eg Position, Balance, CashCommitment, Receivable, ForwardFX.
     *
     * @param holdingType the holdingType value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withHoldingType(String holdingType) {
        this.holdingType = holdingType;
        return this;
    }

    /**
     * Get quantity of holding.
     *
     * @return the units value
     */
    public Double units() {
        return this.units;
    }

    /**
     * Set quantity of holding.
     *
     * @param units the units value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withUnits(Double units) {
        this.units = units;
        return this;
    }

    /**
     * Get settled quantity of holding.
     *
     * @return the settledUnits value
     */
    public Double settledUnits() {
        return this.settledUnits;
    }

    /**
     * Set settled quantity of holding.
     *
     * @param settledUnits the settledUnits value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withSettledUnits(Double settledUnits) {
        this.settledUnits = settledUnits;
        return this;
    }

    /**
     * Get book cost of holding in transaction currency.
     *
     * @return the cost value
     */
    public Double cost() {
        return this.cost;
    }

    /**
     * Set book cost of holding in transaction currency.
     *
     * @param cost the cost value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withCost(Double cost) {
        this.cost = cost;
        return this;
    }

    /**
     * Get book cost of holding in portfolio currency.
     *
     * @return the costPortfolioCcy value
     */
    public Double costPortfolioCcy() {
        return this.costPortfolioCcy;
    }

    /**
     * Set book cost of holding in portfolio currency.
     *
     * @param costPortfolioCcy the costPortfolioCcy value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withCostPortfolioCcy(Double costPortfolioCcy) {
        this.costPortfolioCcy = costPortfolioCcy;
        return this;
    }

    /**
     * Get if this is commitment-type holding, the transaction behind it.
     *
     * @return the transaction value
     */
    public Transaction transaction() {
        return this.transaction;
    }

    /**
     * Set if this is commitment-type holding, the transaction behind it.
     *
     * @param transaction the transaction value to set
     * @return the PortfolioHolding object itself.
     */
    public PortfolioHolding withTransaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }

}