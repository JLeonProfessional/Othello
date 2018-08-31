package org.com.john.othello;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import junit.framework.Assert;

public class GameLogicTest {
	
	public void testFirstTurn() {
		GameLogic gl = new GameLogic();
		gl.playTurn(new Scanner("53"));
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
		int[][] actual = gl.getBoard().getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testSecondTurn() {
		GameLogic gl = new GameLogic();
		gl.playTurn(new Scanner("53"));
		gl.playTurn(new Scanner("32"));
		int[][] expected = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 0, 0, 0, 0},
				{0, 0, 0, 2, 1, 1, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		int[][] actual = gl.getBoard().getGrid();
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testGameFinished() {
		GameLogic gl = new GameLogic();
		int[][] grid = {
				{0, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 2, 1, 1, 1, 1},
				{1, 1, 1, 2, 1, 1, 1, 1},
				{1, 1, 1, 2, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 0, 2, 2, 2, 2, 1, 2},
				{1, 1, 1, 1, 1, 1, 1, 1}
		};
		gl.getBoard().setGrid(grid);
		gl.playTurn(new Scanner("16"));
		gl.playTurn(new Scanner("00"));
		int[][] expected = {
				{2, 1, 1, 1, 1, 1, 1, 1},
				{1, 2, 1, 1, 1, 1, 1, 1},
				{1, 1, 2, 2, 1, 1, 1, 1},
				{1, 1, 1, 2, 1, 1, 1, 1},
				{1, 1, 1, 2, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 2},
				{1, 1, 1, 1, 1, 1, 1, 1}
		};
		int[][] actual = gl.getBoard().getGrid();
		Assert.assertTrue(gl.isFinished());
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
	
	public void testPlayTurn_Invalid_Occupied() {
		GameLogic gl = new GameLogic();
		try {
			gl.playTurn(new Scanner("43"));
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
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}	
	}
	
	public void testPlayTurn_Invalid_OutOfBoundsX() {
		GameLogic gl = new GameLogic();
		try {
			gl.playTurn(new Scanner("85"));
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
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}	
	}
	
	public void testPlayTurn_Invalid_OutOfBoundsY() {
		GameLogic gl = new GameLogic();
		try {
			gl.playTurn(new Scanner("58"));
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
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}	
	}
	
	public void testPlayTurn_Invalid_NotConnected() {
		GameLogic gl = new GameLogic();
		try {
			gl.playTurn(new Scanner("00"));
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
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}	
	}
	
	public void testPlayTurn_Invalid_NoCapture() {
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
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}	
	}
}
