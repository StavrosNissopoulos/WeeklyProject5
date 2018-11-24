package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {

	Connection connection;
	Statement stmt;
	DriverManager dm;

	public java.sql.Connection connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "sudo");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem conecting with the database");
			return null;
		}
	}
	
	public java.sql.Connection connect(String database_name) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database_name, "root", "sudo");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem conecting with the database");
			return null;
		}
	}

	public void closeDbConnection() throws SQLException {
		connection.close();
	}

	
	public void createDatabase() {
		String createDb = "create schema IF NOT EXISTS weekly4;";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(createDb);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error while creating the database");
		}
	}

	public void createTables() {
		String createWorkerTable = "CREATE TABLE IF NOT EXISTS weekly4.worker (\r\n"
				+ "worker_id INT(11) NOT NULL AUTO_INCREMENT,\r\n" + "first_name varchar(45),\r\n"
				+ "last_name varchar(45),\r\n" + "weekly_salary decimal(8,2),\r\n" + "daily_hours decimal(4,2),\r\n"
				+ "salary_per_hour decimal(6,2),\r\n" + "primary key (worker_id),\r\n"
				+ "KEY idx_worker_id (worker_id)\r\n" + ")\r\n" + "ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		String createStudentTable = "create table IF NOT EXISTS `weekly4`.`student` (\r\n"
				+ "`student_id` int(11) not null AUTO_INCREMENT,\r\n" + "`first_name` varchar(45) not null,\r\n"
				+ "`last_name` varchar(45) not null,\r\n" + "`faculty_number` SMALLINT,\r\n"
				+ "PRIMARY KEY(`student_id`),\r\n" + "KEY `idx_stud_last_name` (`last_name`)\r\n" + "\r\n" + "\r\n"
				+ "\r\n" + ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		try {
			stmt = connection.createStatement();
			stmt.execute(createWorkerTable);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error while creating the database");
		}
		try {
			stmt = connection.createStatement();
			stmt.execute(createStudentTable);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error while creating the database");
		}
	}

	public int insertStudent() {
		boolean flag = true;
		String studFirstName = null;
		String studLastName = null;
		String facultyNmbr;
		int parsedFacultyNumber;
		@SuppressWarnings("resource")
		Scanner sca = new Scanner(System.in);
		
		do {
			flag = true;
			System.out.println("Enter the first name of the student");
			System.out.println("The first name should have more that 2 letters");
			try {
				studFirstName = sca.nextLine();
				studFirstName = studFirstName.substring(0, 1).toUpperCase() + studFirstName.substring(1);
				if (studFirstName.length() <= 2) {
					flag = false;
				}
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
				flag = false;
				System.out.println("Please enter a valid first name");
			}
		} while (!flag);
		do {
			flag = true;
			System.out.println("Enter the last name of the student");
			System.out.println("The last name should have more than 3 letters");
			try {
				studLastName = sca.nextLine();
				studLastName = studLastName.substring(0, 1).toUpperCase() + studLastName.substring(1);
				if (studLastName.length() <= 3) {
					flag = false;
					System.out.println("Please enter a valid last name");
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
				System.out.println("Please enter a valid last name");
			}
		} while (!flag);
		do {
			flag = true;
			System.out.println("Enter the faculty number");
			System.out.println("The faculty number should be from 5 to 10");
			facultyNmbr = sca.nextLine();
			if (isNumeric(facultyNmbr)) {
				parsedFacultyNumber = Integer.parseInt(facultyNmbr);
				if (parsedFacultyNumber < 5 || parsedFacultyNumber > 10) {
					flag = false;
					System.out.println("Please enter a valid number");
				}
			} else {
				flag = false;
				parsedFacultyNumber = 0;
				System.out.println("Please enter a number");
			}
		} while (!flag);
		String query = "INSERT INTO student VALUES (null,'" + studFirstName + "','" + studLastName + "',"
				+ parsedFacultyNumber + ");";
		try {
			stmt = connection.createStatement();
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("A problem accured and the student couldn't be stored in the database");
			return -22;
		}
	}

	public int insertWorker() {
		boolean flag = true;
		String workerFirstName = null;
		String workerLastName = null;
		String worker_weekly_salary;
		double parsed_weekly_salary;
		String worker_hours_day;
		double parsed_hours_day;
		double salary_per_hour;

		@SuppressWarnings("resource")
		Scanner sca = new Scanner(System.in);
		do {
			flag = true;
			System.out.println("Enter the first name of the worker");
			System.out.println("The first name should have more that 2 letters");
			try {
				workerFirstName = sca.nextLine();
				workerFirstName = workerFirstName.substring(0, 1).toUpperCase() + workerFirstName.substring(1);
				if (workerFirstName.length() <= 2) {
					flag = false;
				}
			} catch (StringIndexOutOfBoundsException e) {
				e.printStackTrace();
				flag = false;
				System.out.println("Please enter a valid first name");
			}
		} while (!flag);
		do {
			flag = true;
			System.out.println("Enter the last name of the worker");
			System.out.println("The last name should have more than 3 letters");
			try {
				workerLastName = sca.nextLine();
				workerLastName = workerLastName.substring(0, 1).toUpperCase() + workerLastName.substring(1);
				if (workerLastName.length() <= 3) {
					flag = false;
					System.out.println("Please enter a valid last name");
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
				System.out.println("Please enter a valid last name");
			}
		} while (!flag);
		do {
			flag = true;
			System.out.println("Enter the weekly salary of the worker");
			System.out.println("The weekly salary should be more than 10");
			worker_weekly_salary = sca.nextLine();
			if (isDouble(worker_weekly_salary)) {
				parsed_weekly_salary = Double.parseDouble(worker_weekly_salary);
				if (parsed_weekly_salary < 10) {
					flag = false;
					System.out.println("Please enter a valid number");
				}
			} else {
				flag = false;
				parsed_weekly_salary = 0;
				System.out.println("Please enter a number");
			}
		} while (!flag);
		do {
			flag = true;
			System.out.println("Enter the daily working hours of the worker");
			System.out.println("The daily hours should be between 1 and 12");
			worker_hours_day = sca.nextLine();
			if (isDouble(worker_hours_day)) {
				parsed_hours_day = Double.parseDouble(worker_hours_day);
				if (parsed_hours_day < 1 || parsed_hours_day > 12) {
					flag = false;
					System.out.println("Please enter a valid number");
				}
			} else {
				flag = false;
				parsed_hours_day = 0;
				System.out.println("Please enter a number");
			}
		} while (!flag);
		salary_per_hour = parsed_weekly_salary / (5 * parsed_hours_day);
		String query = "INSERT INTO worker VALUES (null,'" + workerFirstName + "','" + workerLastName + "',"
				+ parsed_weekly_salary + "," + parsed_hours_day + "," + salary_per_hour + ");";
		try {
			stmt = connection.createStatement();
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("A problem accured and the worker couldn't be stored in the database");
			return -22;
		}
	}

	public void randomDatabaseInsertion() {
		String queryStudents = "INSERT INTO weekly4.student(first_name,last_name,faculty_number)\r\n"
				+ "VALUES ('Kostas','Kostarikas',5),\r\n" + "('Michalis','Michalakis',6),\r\n"
				+ "('Makis','Macintosh',7),\r\n" + "('Vasilis','Pilis',8),\r\n" + "('Vladimiros','Markas',9),\r\n"
				+ "('Keftes','Keftedopoulos',10),\r\n" + "('Johny','Depp',8),\r\n" + "('Robert','De niro',5),\r\n"
				+ "('Frank','Sinatra',10),\r\n" + "('Jimmy','Hendrix',6),\r\n" + "('Ray','Charles',7),\r\n"
				+ "('James','Brown',7),\r\n" + "('Bob','Marley',8);";
		String queryWorkers = "INSERT INTO weekly4.worker(first_name,last_name,weekly_salary,daily_hours,salary_per_hour)\r\n"
				+ "VALUES('Napoleon','Megas',1150.5,3.5,65.74),\r\n" + "('Timoleon','Apiastos',1500,3,100),\r\n"
				+ "('Don','Nonos',12000,16,150),\r\n" + "('Rihardos','Tritos',20000,1,4000),\r\n"
				+ "('Paixths','Vasikos',50000,8,1250),\r\n" + "('Paixths','Pagitis',1000,8,25),\r\n"
				+ "('Michalis','Ergatikos',800,14,11.42),\r\n" + "('Dimitris','Tempelakos',121.3,5,4.85),\r\n"
				+ "('Vasilis','Trexantiris',300,10,6),\r\n" + "('Kostas','Kaimenos',101,9,2.24);";
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(queryStudents);
			stmt.executeUpdate(queryWorkers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printStudents() {
		String printAllStudents = "SELECT * FROM weekly4.student;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(printAllStudents);
			while (rs.next()) {
				String studFirstName = rs.getString("first_name");
				System.out.println("First Name :     {" + studFirstName + "}");
				String studLastName = rs.getString("last_name");
				System.out.println("Last Name :      {" + studLastName + "}");
				String studFaculty = rs.getString("faculty_number");
				System.out.println("Faculty Number : {" + studFaculty + "}");
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String numberOfStudents = "SELECT count(*) AS counts FROM weekly4.student;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(numberOfStudents);
			while (rs.next()) {
				String numOfStudents = rs.getString("counts");
				System.out.println("The student entries in the database is/are : " + numOfStudents + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printWorkers() {
		String printAllWorkers = "SELECT * FROM weekly4.worker;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(printAllWorkers);
			while (rs.next()) {
				String workerFirstName = rs.getString("first_name");
				System.out.println("First Name :     {" + workerFirstName + "}");
				String workerLastName = rs.getString("last_name");
				System.out.println("Last Name :      {" + workerLastName + "}");
				String workerWeekly = rs.getString("weekly_salary");
				System.out.println("Week Salary :    {" + workerWeekly + "}");
				String workerDailyHours = rs.getString("daily_hours");
				System.out.println("Hours Per Day :  {" + workerDailyHours + "}");
				String workerHourlySalary = rs.getString("salary_per_hour");
				System.out.println("Salary Per Hour :{" + workerHourlySalary + "}");
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String numberOfWorkers = "SELECT count(*) AS counts FROM weekly4.worker;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(numberOfWorkers);
			while (rs.next()) {
				String numOfWorkers = rs.getString("counts");
				System.out.println("The student entries in the database is/are : " + numOfWorkers + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printSomeStudents(int number) {
		String parsedInt = String.valueOf(number);
		String printSomeStudents = "SELECT * FROM weekly4.student WHERE student_id BETWEEN 1 AND " + parsedInt + ";";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(printSomeStudents);
			while (rs.next()) {
				String studFirstName = rs.getString("first_name");
				System.out.println("First Name :     {" + studFirstName + "}");
				String studLastName = rs.getString("last_name");
				System.out.println("Last Name :      {" + studLastName + "}");
				String studFaculty = rs.getString("faculty_number");
				System.out.println("Faculty Number : {" + studFaculty + "}");
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String numberOfStudents = "SELECT count(*) AS counts FROM weekly4.student;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(numberOfStudents);
			while (rs.next()) {
				String numOfStudents = rs.getString("counts");
				System.out.println("The student entries in the database is/are : " + numOfStudents + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printSomeWorkers(int number) {
		String parsedInt = String.valueOf(number);
		String printSomeWorkers = "SELECT * FROM weekly4.worker WHERE worker_id BETWEEN 1 AND " + parsedInt + ";";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(printSomeWorkers);
			while (rs.next()) {
				String workerFirstName = rs.getString("first_name");
				System.out.println("First Name :     {" + workerFirstName + "}");
				String workerLastName = rs.getString("last_name");
				System.out.println("Last Name :      {" + workerLastName + "}");
				String workerWeekly = rs.getString("weekly_salary");
				System.out.println("Week Salary :    {" + workerWeekly + "}");
				String workerDailyHours = rs.getString("daily_hours");
				System.out.println("Hours Per Day :  {" + workerDailyHours + "}");
				String workerHourlySalary = rs.getString("salary_per_hour");
				System.out.println("Salary Per Hour :{" + workerHourlySalary + "}");
				System.out.println("------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String numberOfWorkers = "SELECT count(*) AS counts FROM weekly4.worker;";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(numberOfWorkers);
			while (rs.next()) {
				String numOfWorkers = rs.getString("counts");
				System.out.println("The worker entries in the database is/are : " + numOfWorkers + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean isNumeric(String input) {
		try {
			int num = Integer.parseInt(input);
			num = num + 1;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(String input) {
		try {
			double num = Double.parseDouble(input);
			num = num + 1;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
