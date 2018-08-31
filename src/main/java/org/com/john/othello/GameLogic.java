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
		int[] input;
		do {
			input = board.getInput().promptUser(scanner);
		} while(!checkValidMove(input, color));
		this.board.playPiece(color, input);
		checkGameOver();
	}
	
	private boolean checkValidMove(int[] move, int color) {
		boolean valid = true;
		int x = move[0];
		int y = move[1];
		valid = checkForCapture(color, x, y);
		if(valid && x > 7 || x < 0 || y > 7 || y < 0) {
			valid = false;
		}
		if(valid && board.getGrid()[y][x] != BoardSpecs.EMPTY_SQUARE) {
			valid = false;
		} 
		return valid;
	}
	
	private boolean checkForCapture(int color, int x, int y) {
		boolean valid = false;
		valid = checkForCapture(color, x, y, -1, 0);  //Left
		if(!valid) {
			valid = checkForCapture(color, x, y, 1, 0);   //Right
		}
		if(!valid) {
			valid = checkForCapture(color, x, y, 0, -1);  //Down
		}
		if(!valid) {
			valid = checkForCapture(color, x, y, 0, 1);   //Up
		}
		if(!valid) {
			valid = checkForCapture(color, x, y, -1, -1); //DownLeft
		}
		if(!valid) {
			valid = checkForCapture(color, x, y, 1, -1);  //DownRight
		}
		if(!valid) {
			valid = checkForCapture(color, x, y, -1, 1);  //UpLeft
		}
		if(!valid) {
			valid = checkForCapture(color, x, y, 1, 1);   //UpRight
		}
		return valid;
	}

	private boolean checkForCapture(int color, int x, int y, int changeX, int changeY) {
		boolean valid = false;
		boolean opponentColorFound = false;
		int opponentColor = (color == BoardSpecs.WHITE_COLOR) ? BoardSpecs.BLACK_COLOR : BoardSpecs.WHITE_COLOR;
		int[][] grid = board.getGrid();
		for(int possiblePieceX = x + changeX, possiblePieceY = y + changeY; checkValid(possiblePieceX, possiblePieceY); possiblePieceX += changeX, possiblePieceY += changeY) {
			valid = true;
			int possiblePiece = grid[possiblePieceY][possiblePieceX];
			if(possiblePiece == BoardSpecs.EMPTY_SQUARE) {
				valid = false;
				break;
			}
			if(possiblePiece == opponentColor && !opponentColorFound) {
				opponentColorFound = true;
			}
			if(possiblePiece == color) {
				if(!opponentColorFound) {
					valid = false;
				}
				break;
			}
		}
		return valid;
	}

	public static boolean checkValid(int possiblePieceX, int possiblePieceY) {
		return (possiblePieceX >= 0 && possiblePieceX <= 7) && (possiblePieceY >= 0 && possiblePieceY <= 7);
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
