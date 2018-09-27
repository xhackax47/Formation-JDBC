package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceBDD {
	
	// IDENTIFIANTS DB
	
	static final String USER = "Formation";
	static final String PASS = "N@dyalilou71300";
	
	// VARIABLES STRING JDBC

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost/Formation";
	
    // VARIABLES STRINGS SQL
	
	static String createTabBook = "CREATE TABLE book("
			+ "id_Book SERIAL PRIMARY KEY NOT NULL,"
			+ "title VARCHAR(255),"
			+ "author VARCHAR(100)"
			+ ")";
	static String enumClient = "CREATE TYPE gender AS ENUM ('M', 'F');";
	static String createTabClient = "CREATE TABLE client("
			+ "id_Client SERIAL PRIMARY KEY NOT NULL,"
			+ "lastname VARCHAR(100),"
			+ "firstname VARCHAR(100),"
			+ "gender VARCHAR(1)"
			+ ")";	
	
	static String createTabAchats = "CREATE TABLE achats("
			+ "id SERIAL PRIMARY KEY NOT NULL,"
			+ "idClient INT REFERENCES client(id_Client),"
			+ "idBook INT REFERENCES book(id_Book)"
			+ ")";
	
	static String dropEnum = "DROP TYPE IF EXISTS gender";
	static String dropTabBook = "DROP TABLE IF EXISTS book";		
	static String dropTabClient = "DROP TABLE IF EXISTS client";
	static String dropTabAchats = "DROP TABLE IF EXISTS achats";

	
	public static Connection connectDB() throws InterruptedException {	
		Connection conn = null;
		System.out.print("Connexion à la database");
		Thread.sleep(1 * 1000);
		System.out.print(".");
		Thread.sleep(1 * 1000);
		System.out.print(".");
		Thread.sleep(1 * 1000);
		System.out.print(".");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connexion EFFECTUEE");
	    System.out.println("");
		return conn;
	}
	
	public static void createEnumClient(Connection conn) {
		try {
			PreparedStatement stmtcEnum = conn.prepareStatement(enumClient);
			stmtcEnum.executeUpdate();
		    stmtcEnum.close();
		    System.out.println("CREATION TYPE enum EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTabBook(Connection conn) {
		
		try {
			PreparedStatement stmtcBook = conn.prepareStatement(createTabBook);
			stmtcBook.executeUpdate();
		    stmtcBook.close();
		    System.out.println("CREATION TABLE book EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTabClient(Connection conn) {
		try {
			PreparedStatement stmtcClient = conn.prepareStatement(createTabClient);
			stmtcClient.executeUpdate();
		    stmtcClient.close();
		    System.out.println("CREATION TABLE client EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void createTabAchats(Connection conn) {
		try {
			PreparedStatement stmtcAchats = conn.prepareStatement(createTabAchats);
			stmtcAchats.executeUpdate();
		    stmtcAchats.close();
			System.out.println("CREATION TABLE achats EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void dropEnumClient(Connection conn) {
		try {
			PreparedStatement stmtdEnum = conn.prepareStatement(dropEnum);
		    stmtdEnum.executeUpdate();
		    stmtdEnum.close();
		    System.out.println("DROP TYPE gender EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void dropTabBook(Connection conn) {
		try {
			PreparedStatement stmtdBook = conn.prepareStatement(dropTabBook);
		    stmtdBook.executeUpdate();
		    stmtdBook.close();
		    System.out.println("DROP TABLE book EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void dropTabClient(Connection conn) {
		try {
			PreparedStatement stmtdClient = conn.prepareStatement(dropTabClient);
		    stmtdClient.executeUpdate();
		    stmtdClient.close();
		    System.out.println("DROP TABLE client EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void dropTabAchats(Connection conn) {
		try {
			PreparedStatement stmtdAchats = conn.prepareStatement(dropTabAchats);
		    stmtdAchats.executeUpdate();
		    stmtdAchats.close();
		    System.out.println("DROP TABLE achats EFFECTUEE");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
