package org.com.john.othello;

import java.util.Scanner;

public class UserInput {

	
	public int[] promptUser() {
		return promptUser(new Scanner(System.in));
	}
	
	public int[] promptUser(Scanner scanner) {
		System.out.println("Play your piece \"xy\"");
		String move = scanner.nextLine();
		int x = Character.getNumericValue(move.charAt(0));
		int y = Character.getNumericValue(move.charAt(1));
		int[] coords = {x, y};
		return coords;
	}
}
