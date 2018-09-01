package org.com.john.othello;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException  {
		User user = null;
		user = User.lookupUserByName("John");
		Integer id = user.getID();
		System.out.println(id);
		user = User.lookupUserByName("Bob");
		id = user.getID();
		System.out.println(id);
		
	}
}
