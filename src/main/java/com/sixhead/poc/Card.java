package com.sixhead.poc;

import com.sixhead.poc.execution.GameEvent;

import java.util.ArrayList;
import java.util.Collection;

public class Card {
  private final Collection<GameEvent> events;

  public Card() {this.events = new ArrayList<>();}
  public Card(Collection<GameEvent> events) {this.events = events;}

  public void addEvent(GameEvent event) {
    this.events.add(event);
  }

  public Collection<GameEvent> getEvents() {
    return events;
  }
}