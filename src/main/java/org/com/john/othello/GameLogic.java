package org.com.john.othello;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GameLogic {
	public static final String YOUR_TURN = "It is your turn.";

	private Board board;
	
	private User playerOne;
	private User playerTwo;
	private User winner;
	
	private boolean whiteTurn;
	private boolean finished;

	private ArrayList<Socket> socketList;
	
	public GameLogic() {
		this(null, null);
	}
	
	public GameLogic(User playerOne, User playerTwo) {
		this(playerOne, playerTwo, null);
	}
	
	public GameLogic(User playerOne, User playerTwo, ArrayList<Socket> socketList) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.socketList = socketList;
		this.board = Board.getInstance();
		whiteTurn = true;
		finished = false;
	}

	public void playGame() throws Exception {
		while(!finished) {
			playTurn();
		}
		winner = findWinner();
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/othello", "jleon", "jleon");
		String query = "UPDATE users SET users.wins = ?"  
				+ " WHERE users.userName = ?";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, winner.getWins() + 1);
		preparedStmt.setString(2, winner.getName());
		preparedStmt.execute();
		conn.close();
		System.out.println(winner.getName() + " is the winner!");
	}

	private User findWinner() {
		int whiteNumber = 0;
		int blackNumber = 0;
		User winner = null;
		for(int row = 0; row < 8; row++) {
			for(int column = 0; column < 8; column++) {
				if(board.getGrid()[row][column] == BoardSpecs.WHITE_COLOR) {
					whiteNumber++;
				} else {
					blackNumber++;
				}
			}
		}
		if(whiteNumber > blackNumber) {
			winner = playerOne;
		} else {
			winner = playerTwo;
		}
		return winner;
	}
	
	public void playTurn() throws Exception {
		playTurn(new Scanner(System.in));
		String text = "";
		for(int row = 0; row < 8; row++) {
			for(int column = 0; column < 8; column++) {
				text += board.getGrid()[row][column] + ", ";
			}
			text += "\n";
		}
		text += "--------------------------";
		System.out.println(text);
		if(!socketList.isEmpty()) {
			Iterator<Socket> iterator = socketList.iterator();
			while(iterator.hasNext()) {
				Socket currentSocket = iterator.next();
				UserInput.sendInformationToClient(currentSocket, text);
			}
		}
	}
	
	public void playTurn(Scanner scanner) throws Exception {
		int color;
		int currentPlayer = whiteTurn ? 0 : 1;
		if(whiteTurn) {
			color = BoardSpecs.WHITE_COLOR;
			whiteTurn = false;
		} else {
			color = BoardSpecs.BLACK_COLOR;
			whiteTurn = true;
		}
		String input = null;
		int[] coords;
		Socket socket = null;
		do {
			if(socketList != null) {
				socket = socketList.get(currentPlayer);
			}
			if(socket != null) {
				UserInput.sendInformationToClient(socket, YOUR_TURN);
				input = UserInput.recieveInformationFromClient(socket);
			}
			int x = Character.getNumericValue(input.charAt(0));
			int y = Character.getNumericValue(input.charAt(1));
			coords = new int[] {x, y};
		} while(!checkValidMove(coords, color));
		this.board.playPiece(color, coords);
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

	public User getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(User playerOne) {
		this.playerOne = playerOne;
	}

	public User getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(User playerTwo) {
		this.playerTwo = playerTwo;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}


}
