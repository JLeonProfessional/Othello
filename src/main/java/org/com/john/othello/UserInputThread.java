package org.com.john.othello;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class UserInputThread implements Runnable{
	private DataOutputStream output;
	private Scanner scanner;
	
	public UserInputThread(Socket socket, Scanner scanner) throws IOException{
		this.output = new DataOutputStream(socket.getOutputStream());
		this.scanner = scanner;
	}

	public void run() {
		String name = UserInput.promptUserForName(scanner);
		System.out.println(name);
		try {
			output.writeUTF(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
