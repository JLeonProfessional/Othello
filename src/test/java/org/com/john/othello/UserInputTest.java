package org.com.john.othello;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.com.john.othello.helpers.BoardTestHelper;
import org.junit.Assert;
import org.junit.Test;


public class UserInputTest {

	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}

	@Test
	public void testInvalidInput_IncorrectNumberOfCharacters() {
		Board board = Board.getInstance();
		try {
			board.playPiece(BoardSpecs.WHITE_COLOR, board.getInput().promptUser(new Scanner("5")));
			Assert.fail("Should be unreachable");
		} catch(NoSuchElementException e) {
			// expected
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
		int[][] actual = board.getGrid();
		BoardTestHelper.validateBoard(expected, actual);
	}

	@Test
	public void testInvalidInput_NotNumeric() {
		Board board = Board.getInstance();
		try {
			board.playPiece(BoardSpecs.WHITE_COLOR, board.getInput().promptUser(new Scanner("ABC...///")));
			Assert.fail("Should be unreachable");
		} catch(NoSuchElementException e) {
			// expected
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
		int[][] actual = board.getGrid();
		BoardTestHelper.validateBoard(expected, actual);
	}
}
