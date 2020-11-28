package com.sixhead.poc;

import com.sixhead.poc.cards.CardDefinition;
import com.sixhead.poc.cards.OldScratchs;
import com.sixhead.poc.execution.handlers.ActionSpecHandler;
import com.sixhead.poc.execution.handlers.ConditionSpecHandler;
import com.sixhead.poc.execution.Executor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

public class PocApplication {


	public static void main(String[] args) {
		Player yourself = new Player();
		Executor executor = new Executor();
		executor.register(new ActionSpecHandler());
		executor.register(new ConditionSpecHandler());


		Collection<CardDefinition> cards = List.of(
				new OldScratchs()
		);

		for (CardDefinition definition : cards) {
			var card = definition.getCard();
			executor.enqueue(card.getEvents(), yourself);
		}

		try {
			executor.start();
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
