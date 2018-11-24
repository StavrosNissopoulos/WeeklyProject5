package main;
import database.Database;
import menu.Menu;

public class Main {

	public static void main(String[] args) {

		Database weekly4 = new Database();
		Menu myMenu = new Menu();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Make first connection to mysql
		weekly4.connect();
		weekly4.createDatabase();
		weekly4.createTables();
		//Connect again to created database with tables created
		weekly4.connect("weekly4");
		myMenu.setMyDb(weekly4);
		myMenu.runMenu();

		
	}

}
