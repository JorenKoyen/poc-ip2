package com.sixhead.poc;

import com.sixhead.poc.actions.Action;
import com.sixhead.poc.conditions.ConditionFormula;
import com.sixhead.poc.effects.Effect;
import com.sixhead.poc.effects.TargetedEffect;

public class CardExecutor {

    /**
     * Handle an action and returns the renewed state
     * from this action
     * @param action the action that needs to be executed
     * @return the new state after execution of the action
     */
    private Object handleAction(Action action) {
        // activate the action by calling output()
        var output = action.output();

        // LOG
        System.out.printf("action '%s' executed -> output: %s\n",
                action.getClass().getSimpleName(), output);

        // return state
        return output;
    }

    /**
     * Handle a conditional specification
     * @param formula the conditional formula
     */
    private void handleCondition(Object state, ConditionFormula formula) {
        if (state == null) {
            throw new RuntimeException("A condition needs some form of state to be passed");
        }

        // extract effect based on input
        var effect = formula.decide(state);

        // LOG
        System.out.printf("condition based on '%s' resulted in effect '%s'\n",
                state,
                effect.getClass().getSimpleName());

        // activate effect
        this.handleEffect(state, effect);
    }

    /**
     * Handle the invocation of an effect
     * @param state the current state used as input for the effect
     * @param effect the effect that is being invoked
     */
    private void handleEffect(Object state, Effect effect) {
        // log activation of effect
        System.out.printf("activating effect '%s'\n",
                effect.getClass().getSimpleName());

        // extra log for targeted effect
        if (effect instanceof TargetedEffect) {
            TargetedEffect te = (TargetedEffect)effect;
            System.out.printf("effect is targeting '%s'\n",
                    te.getTarget().getClass().getSimpleName());
        }

        effect.activate(state);
    }

    public void execute(Card card) {
        Object state = null;
        for (Specification spec : card.getSpecifications()) {
            if (spec instanceof Action) {
                state = this.handleAction((Action) spec);
            } else if (spec instanceof ConditionFormula) {
                this.handleCondition(state, (ConditionFormula) spec);
            } else if (spec instanceof Effect) {
                this.handleEffect(state, (Effect) spec);
            }

        }
    }

}
