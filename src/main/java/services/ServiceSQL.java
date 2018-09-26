package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Book;
import model.Client;
import model.Gender;

public class ServiceSQL {
	
	public static void createClient(Connection conn, Client c){	
		
		String createClient = "INSERT INTO client(lastname, firstname, gender) VALUES (?,?,?)";

		try {
			PreparedStatement stmtCreateClient = conn.prepareStatement(createClient, Statement.RETURN_GENERATED_KEYS);
			
		    stmtCreateClient.setString(1, c.getLastname());
		    stmtCreateClient.setString(2, c.getFirstname());
		    stmtCreateClient.setString(3, c.getGender().name());
		    stmtCreateClient.executeUpdate();
		    
			ResultSet generatedKey = stmtCreateClient.getGeneratedKeys();
			generatedKey.next();
			
			long id = generatedKey.getLong("id_client");
			
		    System.out.println("AJOUT D'UN CLIENT ID " + id + " EFFECTUEE : " + c.getFirstname() + " / " + c.getLastname());
		    
		    stmtCreateClient.close();
			
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	public static Long createBook(Connection conn, Book b) {
		
		String createBook = "INSERT INTO book(title, author) VALUES (?,?)";

		try {
			PreparedStatement stmtCreateBook = conn.prepareStatement(createBook, Statement.RETURN_GENERATED_KEYS);
			
			stmtCreateBook.setString(1, b.getTitle());
		    stmtCreateBook.setString(2, b.getAuthor());
		    stmtCreateBook.executeUpdate();
		    
			ResultSet generatedKey = stmtCreateBook.getGeneratedKeys();
			generatedKey.next();
			
			long id = generatedKey.getLong("id_book");
			
		    System.out.println("AJOUT D'UN BOOK EFFECTUEE : " + b.getTitle() + " de " + b.getAuthor() + " avec le numéro ID " + id);
		    stmtCreateBook.close();
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


	}
	
	public static void buyBook(Connection conn, int idclient, int idbook) {
		String buyBook = "INSERT INTO achats(idclient, idbook) VALUES (?,?)" ;
		try {
			PreparedStatement stmtBuyBook = conn.prepareStatement(buyBook);
			stmtBuyBook.setInt(1, idclient);
			stmtBuyBook.setInt(2, idbook);
		    stmtBuyBook.executeUpdate();
			stmtBuyBook.close();
			System.out.println("Achat d'un livre");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayCustomerbyBook(Connection conn, String title, String author) {
		String displayCustomer = "SELECT title, firstname, lastname FROM book RIGHT JOIN achats ON achats.idbook = book.id_book LEFT JOIN client on achats.idclient = client.id_client WHERE book.title=? AND book.author=?";
		try {
			PreparedStatement stmtDisplayCustomer = conn.prepareStatement(displayCustomer);
			
			stmtDisplayCustomer.setString(1, title);
			stmtDisplayCustomer.setString(2, author);
			
			ResultSet rs = stmtDisplayCustomer.executeQuery();
			System.out.println("");
			System.out.println("Clients du livre " + title + " :");
			while(rs.next()) {
				String lastname = rs.getString("lastname");
				String firstname = rs.getString("firstname");
				System.out.println("");
				System.out.println("Nom : " + lastname + " / Prénom : " + firstname);

			}
			System.out.println("");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void displayBookByCustomer(Connection conn, String lastname, String firstname, Gender g) {
		String displayBook = "SELECT title, author, lastname FROM book RIGHT JOIN achats on achats.idbook = book.id_book LEFT JOIN client on achats.idclient = client.id_client WHERE client.lastname=? AND client.firstname=? AND client.gender=?";
		
		try {
			PreparedStatement stmtDisplayBook = conn.prepareStatement(displayBook);
			
			stmtDisplayBook.setString(1,  lastname);
			stmtDisplayBook.setString(2,  firstname);
			stmtDisplayBook.setString(3,  g.name());
			
			ResultSet rs = stmtDisplayBook.executeQuery();
			
			System.out.println("");
			System.out.println("Livre achetés par le client : " + lastname + "" + firstname +  " :");
			while(rs.next()) {
				String title = rs.getString("title");
				String author = rs.getString("author");
				System.out.println("");
				System.out.println("Titre : " + title + " / Auteur : " + author);
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
