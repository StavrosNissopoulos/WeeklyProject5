public class Main {

	public static void main(String[] args) {

		Database weekly4 = new Database();
		Menu myMenu = new Menu();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		weekly4.connect();
		// I wasn't able to create the db through jdbc
		// weekly4.createDatabase();
		weekly4.createTables();
		myMenu.setMyDb(weekly4);
		myMenu.runMenu();

	}

}
