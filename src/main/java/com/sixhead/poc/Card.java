package com.sixhead.poc;

import java.util.LinkedList;

public class Card {
    private final LinkedList<Specification> specifications;

    public Card(LinkedList<Specification> specifications) {
        this.specifications = specifications;
    }

    public LinkedList<Specification> getSpecifications() {
        return this.specifications;
    }
}
