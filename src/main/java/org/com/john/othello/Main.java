package org.com.john.othello;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException  {
		User playerOne = User.lookupUserByName(UserInput.promptUserForName(new Scanner(System.in)));
		User playerTwo = User.lookupUserByName(UserInput.promptUserForName(new Scanner(System.in)));
		GameLogic gl = new GameLogic(playerOne, playerTwo);
		gl.getBoard().setGrid(new int[][]{{0, 1, 1, 1, 1, 1, 1, 1},
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 1, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 2, 1, 2, 2, 2, 2, 2},
				{2, 0, 2, 2, 2, 2, 2, 2},
				{2, 2, 2, 2, 2, 2, 2, 2}});
		gl.playGame();
	}
}
