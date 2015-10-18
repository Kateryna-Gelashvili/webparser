package org.k;

import java.math.BigDecimal;

/**
 * Created by Kateryna on 01.08.2015.
 */
public class Product {
    private String name;
    private BigDecimal price;
    private Currency currency;
    private String website;
    private int id;

    public Product() {
        this.name = "";
        this.price = BigDecimal.ZERO;
        this.currency = null;
        this.website = "";
    }

    public Product(String name, BigDecimal price, Currency currency, String website,int id) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.website = website;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getWebsite() {
        return website;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!name.equals(product.name)) return false;
        if (website != null ? !website.equals(product.website) : product.website != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "webProject.Product " + name + " price = " + price + " " + currency + " " + website;
    }
}
