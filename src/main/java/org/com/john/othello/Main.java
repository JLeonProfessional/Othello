package org.com.john.othello;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		GameLogic gl = new GameLogic();
		gl.playGame();
	}
}
