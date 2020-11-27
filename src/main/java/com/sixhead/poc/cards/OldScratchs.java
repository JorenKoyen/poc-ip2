package com.sixhead.poc.cards;

import com.sixhead.poc.Card;
import com.sixhead.poc.Player;
import com.sixhead.poc.Specification;
import com.sixhead.poc.actions.Action;
import com.sixhead.poc.actions.DieRollAction;
import com.sixhead.poc.conditions.Condition;
import com.sixhead.poc.conditions.ConditionFormula;
import com.sixhead.poc.conditions.RangeCondition;
import com.sixhead.poc.effects.DamageEffect;
import com.sixhead.poc.effects.Effect;
import com.sixhead.poc.effects.HealEffect;
import com.sixhead.poc.target.Target;

import java.util.LinkedList;
import java.util.List;

public class OldScratchs implements CardDefinition {
    @Override
    public Card getCard() {
        // die roll
        Action dieRoll = new DieRollAction();

        // effect
        Target target = new Player();
        Effect heal = new HealEffect(target);
        Effect dmg = new DamageEffect(target);

        // conditions
        Condition r1 = new RangeCondition(1, 3, dmg);
        Condition r2 = new RangeCondition(4, 6, heal);
        ConditionFormula formula = new ConditionFormula(List.of(r1, r2));

        // card
        LinkedList<Specification> specs = new LinkedList<>();
        specs.add(dieRoll);
        specs.add(formula);
        return new Card(specs);
    }

    @Override
    public String getName() {
        return "Old Scratch";
    }
}
