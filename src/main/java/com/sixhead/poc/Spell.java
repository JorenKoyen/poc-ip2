package com.sixhead.poc;

import com.sixhead.poc.execution.GameEvent;

import java.util.ArrayList;
import java.util.Collection;

public class Spell {
  private final Card[] cards;

  public Spell(Card source, Card quality, Card delivery) {
    this.cards = new Card[3];
    this.cards[0] = source;
    this.cards[1] = quality;
    this.cards[2] = delivery;
  }

  public Collection<GameEvent> collectEvents() {
    var events = new ArrayList<GameEvent>();
    for (Card card : this.cards) {
      if (card == null) continue;
      events.addAll(card.getEvents());
    }

    return events;
  }
}