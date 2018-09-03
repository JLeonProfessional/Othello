package org.com.john.othello;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiverThread implements Runnable {
	private Socket socket;
	private int[][] grid;
	private OthelloBoard clientWindow;
	private boolean turn = false;

	public ReceiverThread(Socket socket, int[][] grid, OthelloBoard clientWindow) {
		this.socket = socket;
		this.grid = grid;
		this.clientWindow = clientWindow;
	}

	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			while(true) {
				String messageFromServer = in.readUTF();
				System.out.println(messageFromServer);
				if(messageFromServer.equals(GameLogic.YOUR_TURN)) {
					clientWindow.getOthelloListener().setTurn(true);
				}
				if(Character.isDigit(messageFromServer.charAt(0))){
					for(int row = 0; row < 8; row++) {
						for(int column = 0; column < 8; column++) {
							Character currentChar = messageFromServer.charAt(row*8 + column);
							if(Character.isDigit(currentChar)) {
								this.grid[row][column] = Character.getNumericValue(currentChar);
							}
						}
					}
					clientWindow.getOthelloPanel().paintComponent(grid);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
