package org.com.john.othello;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiverThread implements Runnable {
	private Socket socket;

	public ReceiverThread(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			while(true) {
				String messageFromServer = in.readUTF();
				System.out.println(messageFromServer);
				if(messageFromServer.equals(GameLogic.YOUR_TURN)) {
					String move = UserInput.promptUser(new Scanner(System.in));
					UserInput.sendInformationToServer(socket, move);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
