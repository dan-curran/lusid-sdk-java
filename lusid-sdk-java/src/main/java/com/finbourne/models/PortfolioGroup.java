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
 * The PortfolioGroup model.
 */
public class PortfolioGroup {
    /**
     * The href property.
     */
    @JsonProperty(value = "href")
    private String href;

    /**
     * The id property.
     */
    @JsonProperty(value = "id")
    private ResourceId id;

    /**
     * The displayName property.
     */
    @JsonProperty(value = "displayName")
    private String displayName;

    /**
     * The description property.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * The portfolios property.
     */
    @JsonProperty(value = "portfolios")
    private List<ResourceId> portfolios;

    /**
     * The subGroups property.
     */
    @JsonProperty(value = "subGroups")
    private List<ResourceId> subGroups;

    /**
     * The version property.
     */
    @JsonProperty(value = "version")
    private Version version;

    /**
     * The links property.
     */
    @JsonProperty(value = "links")
    private List<Link> links;

    /**
     * Get the href value.
     *
     * @return the href value
     */
    public String href() {
        return this.href;
    }

    /**
     * Set the href value.
     *
     * @param href the href value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withHref(String href) {
        this.href = href;
        return this;
    }

    /**
     * Get the id value.
     *
     * @return the id value
     */
    public ResourceId id() {
        return this.id;
    }

    /**
     * Set the id value.
     *
     * @param id the id value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withId(ResourceId id) {
        this.id = id;
        return this;
    }

    /**
     * Get the displayName value.
     *
     * @return the displayName value
     */
    public String displayName() {
        return this.displayName;
    }

    /**
     * Set the displayName value.
     *
     * @param displayName the displayName value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get the description value.
     *
     * @return the description value
     */
    public String description() {
        return this.description;
    }

    /**
     * Set the description value.
     *
     * @param description the description value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the portfolios value.
     *
     * @return the portfolios value
     */
    public List<ResourceId> portfolios() {
        return this.portfolios;
    }

    /**
     * Set the portfolios value.
     *
     * @param portfolios the portfolios value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withPortfolios(List<ResourceId> portfolios) {
        this.portfolios = portfolios;
        return this;
    }

    /**
     * Get the subGroups value.
     *
     * @return the subGroups value
     */
    public List<ResourceId> subGroups() {
        return this.subGroups;
    }

    /**
     * Set the subGroups value.
     *
     * @param subGroups the subGroups value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withSubGroups(List<ResourceId> subGroups) {
        this.subGroups = subGroups;
        return this;
    }

    /**
     * Get the version value.
     *
     * @return the version value
     */
    public Version version() {
        return this.version;
    }

    /**
     * Set the version value.
     *
     * @param version the version value to set
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withVersion(Version version) {
        this.version = version;
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
     * @return the PortfolioGroup object itself.
     */
    public PortfolioGroup withLinks(List<Link> links) {
        this.links = links;
        return this;
    }

}