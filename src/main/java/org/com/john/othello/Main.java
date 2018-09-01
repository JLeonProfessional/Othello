package org.com.john.othello;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.com.john.othello.helpers.BoardTestHelper;
import org.junit.Assert;

public class Main {

	public static void main(String[] args) throws SQLException  {
		GameLogic gl = new GameLogic();
		try {
			gl.playTurn(new Scanner("23"));
			Assert.fail("Should be unreachable");
		} catch(NoSuchElementException e) {
			// Expected
		}
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = gl.getBoard().getGrid();
		BoardTestHelper.validateBoard(expected, actual);
	}
}
