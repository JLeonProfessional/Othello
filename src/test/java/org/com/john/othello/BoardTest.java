package org.com.john.othello;

import java.util.Arrays;

import junit.framework.Assert;

public class BoardTest {
	
	public void testDefaultBoard() {
		Board board = Board.getInstance();
		Assert.assertNotNull(board);
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
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testWhitePiecePlayed() {
		Board board = Board.getInstance();
		board.playPiece(BoardSpecs.WHITE_COLOR, 5, 3);
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

	public void testBlackPiecePlayed() {
		Board board = Board.getInstance();
		board.playPiece(BoardSpecs.BLACK_COLOR, 5, 4);
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 2, 2, 2, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureLeft() {
		int[][] grid = {
				{1, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 7, 0);
		int[][] expected = {
				{1, 1, 1, 1, 1, 1, 1, 1},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureRight() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 2, 2, 2, 2, 2, 1},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 0, 1);
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureDown() {
		int[][] grid = {
				{1, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 0, 7);
		int[][] expected = {
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureUp() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{2, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0}
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 0, 0);
		int[][] expected = {
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureUpRight() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 2, 0, 0},
				{0, 0, 0, 0, 2, 0, 0, 0},
				{0, 0, 0, 2, 0, 0, 0, 0},
				{0, 0, 2, 0, 0, 0, 0, 0},
				{0, 2, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 0, 7);
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureDownLeft() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 2, 0, 0},
				{0, 0, 0, 0, 2, 0, 0, 0},
				{0, 0, 0, 2, 0, 0, 0, 0},
				{0, 0, 2, 0, 0, 0, 0, 0},
				{0, 2, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0}
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 7, 0);
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 1},
				{0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0},
				{1, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureUpLeft() {
		int[][] grid = {
				{1, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 0, 0, 0, 0, 0, 0},
				{0, 0, 2, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 0, 0, 0, 0},
				{0, 0, 0, 0, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 2, 0, 0},
				{0, 0, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 7, 7);
		int[][] expected = {
				{1, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1},
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testCaptureDownRight() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 0, 0, 0, 0, 0, 0},
				{0, 0, 2, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 0, 0, 0, 0},
				{0, 0, 0, 0, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 2, 0, 0},
				{0, 0, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 1},
		};
		Board board = new Board(grid);
		board.playPiece(BoardSpecs.WHITE_COLOR, 0, 0);
		int[][] expected = {
				{1, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0, 0, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 1},
		};
		int[][] actual = board.getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
}
