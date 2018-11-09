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
 * The ResourceListOfTransactionConfigurationData model.
 */
public class ResourceListOfTransactionConfigurationData {
    /**
     * The values property.
     */
    @JsonProperty(value = "values")
    private List<TransactionConfigurationData> values;

    /**
     * The Uri that returns the same result as the original request,
     * but may include resolved as at time(s).
     */
    @JsonProperty(value = "href")
    private String href;

    /**
     * The total number of records returned in the set.
     */
    @JsonProperty(value = "count")
    private Integer count;

    /**
     * The links property.
     */
    @JsonProperty(value = "links")
    private List<Link> links;

    /**
     * Get the values value.
     *
     * @return the values value
     */
    public List<TransactionConfigurationData> values() {
        return this.values;
    }

    /**
     * Set the values value.
     *
     * @param values the values value to set
     * @return the ResourceListOfTransactionConfigurationData object itself.
     */
    public ResourceListOfTransactionConfigurationData withValues(List<TransactionConfigurationData> values) {
        this.values = values;
        return this;
    }

    /**
     * Get the Uri that returns the same result as the original request,
     but may include resolved as at time(s).
     *
     * @return the href value
     */
    public String href() {
        return this.href;
    }

    /**
     * Set the Uri that returns the same result as the original request,
     but may include resolved as at time(s).
     *
     * @param href the href value to set
     * @return the ResourceListOfTransactionConfigurationData object itself.
     */
    public ResourceListOfTransactionConfigurationData withHref(String href) {
        this.href = href;
        return this;
    }

    /**
     * Get the total number of records returned in the set.
     *
     * @return the count value
     */
    public Integer count() {
        return this.count;
    }

    /**
     * Set the total number of records returned in the set.
     *
     * @param count the count value to set
     * @return the ResourceListOfTransactionConfigurationData object itself.
     */
    public ResourceListOfTransactionConfigurationData withCount(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Get the links value.
     *
     * @return the links value
     */
    public List<Link> links() {
        return this.links;
    }

    /**
     * Set the links value.
     *
     * @param links the links value to set
     * @return the ResourceListOfTransactionConfigurationData object itself.
     */
    public ResourceListOfTransactionConfigurationData withLinks(List<Link> links) {
        this.links = links;
        return this;
    }

}