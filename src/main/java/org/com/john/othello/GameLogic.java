package org.com.john.othello;

import java.util.Arrays;
import java.util.Scanner;

public class GameLogic {
	private Board board;
	
	private boolean whiteTurn;
	private boolean finished;
	
	public GameLogic() {
		this.board = Board.getInstance();
		whiteTurn = true;
		finished = false;
	}
	
	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		while(!finished) {
			playTurn();
		}
		scanner.close();
	}
	
	public void playTurn() {
		playTurn(new Scanner(System.in));
		for(int row = 0; row < 8; row++) {
			for(int column = 0; column < 8; column++) {
				System.out.print(board.getGrid()[row][column] + ", ");
			}
			System.out.print("\n");
		}
	}
	
	public void playTurn(Scanner scanner) {
		int color;
		if(whiteTurn) {
			color = BoardSpecs.WHITE_COLOR;
			whiteTurn = false;
		} else {
			color = BoardSpecs.BLACK_COLOR;
			whiteTurn = true;
		}
		this.board.playPiece(color, board.getInput().promptUser(scanner));
		checkGameOver();
	}
	
	private void checkGameOver() {
		int[][] grid = board.getGrid();
		finished = true; // Assume game is over-- will find out if not
		for(int row = 0; row < 8 && finished; row++) {
			for(int column = 0; column < 8 && finished; column++) {
				if(grid[row][column] == 0) {
					finished = false;
				}
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isWhiteTurn() {
		return whiteTurn;
	}

	public void setWhiteTurn(boolean whiteTurn) {
		this.whiteTurn = whiteTurn;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}


}
