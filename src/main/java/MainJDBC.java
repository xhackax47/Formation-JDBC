import java.sql.Connection;
import java.sql.SQLException;

import model.Book;
import model.Client;
import model.Gender;
import services.ServiceBDD;
import services.ServiceSQL;

public class MainJDBC {

	public static void main(String[] args) throws InterruptedException {
		Connection conn = null;
		try {

			conn = ServiceBDD.connectDB();
			Thread.sleep(1 * 1000);

			// METHODES DE DROP/CREATION TABLES

			ServiceBDD.dropTabAchats(conn);
			ServiceBDD.dropTabBook(conn);
			ServiceBDD.dropTabClient(conn);
			ServiceBDD.dropEnumClient(conn);
			Thread.sleep(1 * 1000);
			System.out.println("");

			Thread.sleep(1 * 1000);

			ServiceBDD.createTabBook(conn);
			ServiceBDD.createTabClient(conn);
			ServiceBDD.createTabAchats(conn);

			// CREATION OBJETS CLIENT
			String lastname = "Uzumaki";
			String firstname = "Naruto";
			Gender gender = Gender.M;

			String lastname2 = "Haruno";
			String firstname2 = "Sakura";
			Gender gender2 = Gender.F;

			Client c = new Client(lastname, firstname, gender);
			Client c2 = new Client(lastname2, firstname2, gender2);

			ServiceSQL.createClient(conn, c);
			ServiceSQL.createClient(conn, c2);

			// CREATION OBJETS BOOK
			String title = "Boruto";
			String author = "Ukyo Kodachi";
			Book b = new Book(title, author);

			String title2 = "Naruto";
			String author2 = "Masashi Kishimoto";
			Book b2 = new Book(title2, author2);
			
			//long idTest = ServiceSQL.createBook(conn, b2)

			ServiceSQL.createBook(conn, b);
			ServiceSQL.createBook(conn, b2);

			Thread.sleep(1 * 1000);
			System.out.println("");

			ServiceSQL.buyBook(conn, 1, 2);
			
			ServiceSQL.displayCustomerbyBook(conn, b2.getTitle(), b2.getAuthor());
			ServiceSQL.displayBookByCustomer(conn, c.getLastname(), c.getFirstname(), c.getGender());
			
			System.out.println("Fermeture de la connexion BDD");
			Thread.sleep(3 * 1000);
			conn.close();
			System.out.println("Connexion à la BDD FERMEE");
			System.out.println("");

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
}
