package com.sixhead.poc;

import com.sixhead.poc.cards.CardDefinition;
import com.sixhead.poc.cards.OldScratchs;
import com.sixhead.poc.cards.DeathFairy;


import java.util.Collection;
import java.util.List;

public class PocApplication {

	public static void main(String[] args) {
		Collection<CardDefinition> definitions = List.of(
				new OldScratchs(),
				new DeathFairy()
		);

		CardExecutor executor = new CardExecutor();
		for (CardDefinition def : definitions) {
			var card = def.getCard();
			System.out.printf("-----\nexecuting card:\t%s\n", def.getName());
			executor.execute(card);
			System.out.println();
		}

	}

}
