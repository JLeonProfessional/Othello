package org.com.john.othello;

import java.net.Socket;

public class ServerThread implements Runnable{
	private Socket socket;
	private String name;

	public ServerThread(Socket socket){
		this.socket = socket;
	}

	public void run() {
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
