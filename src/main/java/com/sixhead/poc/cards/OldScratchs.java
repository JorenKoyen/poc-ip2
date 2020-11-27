package com.sixhead.poc.cards;

import com.sixhead.poc.Card;
import com.sixhead.poc.actions.DieRollAction;
import com.sixhead.poc.conditions.Condition;
import com.sixhead.poc.conditions.ConditionFormula;
import com.sixhead.poc.conditions.RangeCondition;
import com.sixhead.poc.effects.DamageEffect;
import com.sixhead.poc.effects.HealEffect;
import com.sixhead.poc.execution.*;
import com.sixhead.poc.target.TargetReference;

import java.util.List;

public class OldScratchs implements CardDefinition {
  @Override
  public Card getCard() {
    Card card = new Card();

    // roll a die
    card.addEvent(new ActionGameEvent(new DieRollAction()));

    // damage event
    var dmg = new EffectGameEvent(
        new Object[]{InputVariable.STATE}, // state defines die roll
        new DamageEffect(),
        TargetReference.YOURSELF
    );

    // heal event
    var heal = new EffectGameEvent(
        new Object[]{InputVariable.STATE}, // state defines die roll
        new HealEffect(),
        TargetReference.YOURSELF
    );

    // define condition
    Condition<Integer> dmgCondition = new RangeCondition(1, 3, List.of(dmg));
    Condition<Integer> healCondition = new RangeCondition(4, 6, List.of(heal));
    card.addEvent(new ConditionGameEvent(
        new Condition[]{dmgCondition, healCondition},
        new Object[]{InputVariable.STATE})
    );

    return card;
  }
}
