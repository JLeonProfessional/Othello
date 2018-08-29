package org.com.john.othello;

import java.util.Arrays;

public class Board {
	private int grid[][];
	
	public Board(int[][] grid) {
		this.grid = grid;
	}
	
	public static Board getInstance() {
		int[][] grid = {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
		};
		return new Board(grid);
	}
	
	public void printBoard() {
		for(int row = 0; row < BoardSpecs.NUMBER_OF_ROWS; row++) {
			System.out.println(Arrays.toString(grid[row]));
		}	
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public void playPiece(int color, int x, int y) {
		grid[y][x] = color;
		capture(color, x, y, -1, 0);  //Left
		capture(color, x, y, 1, 0);   //Right
		capture(color, x, y, 0, -1);  //Down
		capture(color, x, y, 0, 1);   //Up
		capture(color, x, y, -1, -1); //DownLeft
		capture(color, x, y, 1, -1);  //DownRight
		capture(color, x, y, -1, 1);  //UpLeft
		capture(color, x, y, 1, 1);   //UpRight
	}

	private void capture(int color, int x, int y, int changeX, int changeY) {
		for(int possiblePieceX = x + changeX, possiblePieceY = y + changeY; checkValid(possiblePieceX, possiblePieceY); possiblePieceX += changeX, possiblePieceY += changeY) {
			if(grid[possiblePieceY][possiblePieceX] == color) {
				for(int finalPositionX = possiblePieceX, finalPositionY = possiblePieceY; finalPositionX != x || finalPositionY != y; finalPositionX -= changeX, finalPositionY -= changeY) {
					grid[finalPositionY][finalPositionX] = color;
				}
				break;
			}
		}
	}

	private boolean checkValid(int possiblePieceX, int possiblePieceY) {
		return (possiblePieceX >= 0 && possiblePieceX <= 7) && (possiblePieceY >= 0 && possiblePieceY <= 7);
	}
	
}
