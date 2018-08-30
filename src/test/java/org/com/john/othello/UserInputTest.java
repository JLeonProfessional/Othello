package org.com.john.othello;

import java.util.Arrays;
import java.util.Scanner;

import junit.framework.Assert;


public class UserInputTest {

	
	public void testPlayerOneMove() {
		Board board = Board.getInstance();
		board.playPiece(BoardSpecs.WHITE_COLOR, board.getInput().promptUser(new Scanner("53")));
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
}
