package org.com.john.othello;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class UserInput {
	Socket socket;
	Thread thread;
	
	public UserInput() {
		this(null, null);
	}
	
	public UserInput(Socket socket, Thread thread) {
		this.socket = socket;
		this.thread = thread;
	}
	
	public String promptUser() {
		return promptUser(new Scanner(System.in));
	}
	
	public static String promptUser(Scanner scanner) {
		String move;
		do {
			System.out.println("Play your piece \"xy\"");
			move = scanner.nextLine();
		} while(move.length() != 2 || !move.matches("[0-9]+"));
		
		return move;
	}

	public String promptUserForName() {
		return promptUserForName(new Scanner(System.in));
	}
	
	public static String promptUserForName(Scanner scanner) {
		String name;
		System.out.println("What is your name?");
		name = scanner.nextLine();
		return name;
	}
	
	public static void sendInformationToServer(Socket socket, String data) throws IOException {
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(data);
	}
	
	public static String recieveInformationFromClient(Socket socket) {
		String data = null;
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			data = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void sendInformationToClient(Socket currentSocket, String data) throws IOException {
		DataOutputStream output = new DataOutputStream(currentSocket.getOutputStream());
		output.writeUTF(data);
	}
}
