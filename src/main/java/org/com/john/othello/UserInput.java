package org.com.john.othello;

import java.util.Scanner;

public class UserInput {

	
	public int[] promptUser() {
		return promptUser(new Scanner(System.in));
	}
	
	public int[] promptUser(Scanner scanner) {
		String move;
		do {
			System.out.println("Play your piece \"xy\"");
			move = scanner.nextLine();
		} while(move.length() != 2 || !move.matches("[0-9]+"));
		int x = Character.getNumericValue(move.charAt(0));
		int y = Character.getNumericValue(move.charAt(1));
		int[] coords = {x, y};
		return coords;
	}

	public String promptUserForName() {
		return promptUserForName(new Scanner(System.in));
	}
	public static String promptUserForName(Scanner scanner) {
		String name;
		System.out.println("What is your name?");
		name = scanner.nextLine();
		return name;
	}
}
