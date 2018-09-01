package org.com.john.othello;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.com.john.othello.helpers.BoardTestHelper;
import org.junit.Assert;
import org.junit.Test;

public class GameLogicTest {
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
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
		BoardTestHelper.validateBoard(expected, actual);
	}
	
	@Test
	public void testUsersAddedCorrectly() {
		User userOne = new User(1, "One");
		User userTwo = new User(2, "Two");
		GameLogic gl = new GameLogic(userOne, userTwo);
		
		Assert.assertTrue(gl.getPlayerOne().getID() == userOne.getID() && gl.getPlayerOne().getName().equals(userOne.getName()));
		Assert.assertTrue(gl.getPlayerTwo().getID() == userTwo.getID() && gl.getPlayerTwo().getName().equals(userTwo.getName()));
	}
	
	
}
