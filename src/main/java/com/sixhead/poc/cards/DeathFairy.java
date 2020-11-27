package com.sixhead.poc.cards;

import com.sixhead.poc.Card;
import com.sixhead.poc.Specification;

import java.util.LinkedList;

public class DeathFairy implements CardDefinition {
    @Override
    public Card getCard() {

        // card
        LinkedList<Specification> specs = new LinkedList<>();
        return new Card(specs);
    }

    @Override
    public String getName() {
        return "The death fairy's";
    }
}
