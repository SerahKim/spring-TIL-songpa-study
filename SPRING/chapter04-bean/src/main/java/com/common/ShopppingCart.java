package com.common;

import java.util.ArrayList;
import java.util.List;

public class ShopppingCart {
    private final List<Product> items;

    public ShopppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return items;
    }
}
