package org.com.john.othello;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(9090);
		Socket playerOneSocket = listener.accept();
		String playerOneName = UserInput.recieveInformationFromClient(playerOneSocket);

		Socket playerTwoSocket = listener.accept();
		String playerTwoName = UserInput.recieveInformationFromClient(playerTwoSocket);

		System.out.println(playerOneName + " vs " + playerTwoName);
		ArrayList<Socket> socketList = new ArrayList<Socket>();
		socketList.add(playerOneSocket);
		socketList.add(playerTwoSocket);
		User playerOne = User.lookupUserByName(playerOneName);
		User playerTwo = User.lookupUserByName(playerTwoName);
		GameLogic gl = new GameLogic(playerOne, playerTwo, socketList);
		gl.playGame();
		listener.close();
	}
}
