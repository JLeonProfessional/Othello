package org.com.john.othello;

import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		try {
			Socket socket = new Socket(BoardSpecs.IP, BoardSpecs.PORT_NUMBER);
			Thread receiverThread = new Thread(new ReceiverThread(socket));
			String name = UserInput.promptUserForName(new Scanner(System.in));
			UserInput.sendInformationToServer(socket, name);
			receiverThread.start();
		} catch(SocketException e) {
			e.printStackTrace();
			System.out.println("Connection to server lost");
		}
	}
}
