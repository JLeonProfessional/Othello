package org.com.john.othello;

import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		try {
			Socket socket = new Socket(BoardSpecs.IP, BoardSpecs.PORT_NUMBER);
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
			OthelloBoard clientWindow = new OthelloBoard("Othello", grid, socket);
			Thread receiverThread = new Thread(new ReceiverThread(socket, grid, clientWindow));
			String name = UserInput.promptUserForName(new Scanner(System.in));
			UserInput.sendInformationToServer(socket, name);
			receiverThread.start();
		} catch(SocketException e) {
			e.printStackTrace();
			System.out.println("Connection to server lost");
		}
	}
}
