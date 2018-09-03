package org.com.john.othello;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;

public class OthelloListener implements MouseListener {
	Socket socket;
	private boolean turn;

	public OthelloListener(Socket socket){
		this.socket = socket;
	}

	public void mousePressed(MouseEvent e) {
		if(turn) {
			String data = String.valueOf(e.getX()/93) + String.valueOf(e.getY()/93);
			System.out.println(data);
			try {
				UserInput.sendInformationToServer(socket, data);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			turn = false;
		}
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

}
