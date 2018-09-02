package org.com.john.othello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	private Integer id;
	private String name;
	private int wins;

	public User(String name) {
		new User(null, name);
	}
	
	public User(Integer id, String name) {
		this(id, name, 0);
	}

	public User(Integer id, String name, int wins) {
		this.id = id;
		this.name = name;
		this.wins = wins;
	}

	public static User lookupUserByName(String name) throws SQLException {
		Integer id = null;
		int wins = 0;
		ResultSet rs = null;
		if(existsInDatabase(name)) {
			rs = findUserByName(name);
		} else {
			insertIntoDatabase(name);
			rs = findUserByName(name);
		}
		if(rs.next()) {
			id = rs.getInt("userID");
			name = rs.getString("userName");
			wins = rs.getInt("wins");
		}
		return new User(id, name, wins);
	}

	private static ResultSet findUserByName(String name) throws SQLException {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/othello", "jleon", "jleon");
		statement = conn.createStatement();
		rs = statement.executeQuery("SELECT * FROM users WHERE users.userName = '" + name + "'");
		return rs;
	}

	private static boolean existsInDatabase(String name) throws SQLException {
		ResultSet rs = findUserByName(name);
		return rs.next();
	}

	// TODO UNTESTED
	private static void insertIntoDatabase(String name) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/othello", "jleon", "jleon");
		String query = " insert into users (userName)"
				+ " values (?)";

		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString (1, name);

		preparedStmt.execute();

		conn.close();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getID() {
		return id;
	}
	
	public void setID(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

}
