package org.com.john.othello.helpers;

import java.util.Arrays;

import org.com.john.othello.BoardSpecs;
import org.junit.Assert;

public class BoardTestHelper {
	public static void validateBoard(int[][] expected, int[][] actual) {
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			Assert.assertTrue(Arrays.toString(actual[row]).equals(Arrays.toString(expected[row])));
		}
	}
}
