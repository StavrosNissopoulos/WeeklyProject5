package menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.Database;

public class Menu {

	boolean exit = false;
	Database myDb;

	public Database getMyDb() {
		return myDb;
	}

	public void setMyDb(Database myDb) {
		this.myDb = myDb;
	}

	public void runMenu() {
		System.out.println("Welcome to Weekly Project 5");
		System.out.println("You will be able to interact with a mySQL database");
		while (!exit) {
			printMenu();
			int choice = getInputFirstMenu();
			firstMenu(choice);
		}
	}

	private void printMenu() {
		System.out.println("\nPlease enter your choice");
		System.out.println("Enter 1 to insert data in the database");
		System.out.println("Enter 2 to print data from the database");
		System.out.println("Enter 0 to exit the program");
	}

	// Main menu selections
	private void firstMenu(int choice) {
		switch (choice) {
		case 0:
			System.out.println("Thank you and have a good day!");
			exit = true;
			break;
		case 1:
			System.out.println("Enter 1 to make a new student entry");
			System.out.println("Enter 2 to make a new worker entry");
			System.out.println("Enter 3 to have the program make some default worker and student entries");
			subMenuOne(getInputSubMenuOne());
			break;
		case 2:
			System.out.println("Enter 1 to print all students in the database");
			System.out.println("Enter 2 to print a specific number of students in the database");
			System.out.println("Enter 3 to print all workers in the database");
			System.out.println("Enter 4 to print a specific number of workers in the database");
			subMenuTwo(getInputSubMenuTwo());
			break;
		}
	}

	// Menu for inserting data to the database
	private void subMenuOne(int choice) {
		switch (choice) {
		case 1:
			myDb.insertStudent();
			break;
		case 2:
			myDb.insertWorker();
			break;
		case 3:
			myDb.randomDatabaseInsertion();
			break;
		}
	}

	// Menu for printing data from the database
	private void subMenuTwo(int choice) {
		switch (choice) {
		case 1:
			myDb.printStudents();
			break;
		case 2:
			System.out.println("Please enter the number of students you want to print");
			myDb.printSomeStudents(getInputUnrestricted());
			break;
		case 3:
			myDb.printWorkers();
			break;
		case 4:
			System.out.println("Please enter the number of workers you want to print");
			myDb.printSomeWorkers(getInputUnrestricted());
			break;
		}
	}

	// Restricts input returns integer between 1 and 2
	private int getInputFirstMenu() {
		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				choice = Integer.parseInt(br.readLine());
				if (choice < 0 || choice > 2) {
					System.out.println("Please enter one number between 0 and 2");
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
				System.out.println("Please enter a valid input. A number between 0 and 2");
			}
		} while (choice < 0 || choice > 2);
		return choice;
	}

	// Restricts input returns integer between 1 and 3
	private int getInputSubMenuOne() {
		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (choice < 1 || choice > 3) {
			try {
				choice = Integer.parseInt(br.readLine());
				if (choice < 1 || choice > 3) {
					System.out.println("Please enter one number between 0 and 3");
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Please enter a valid input. A number between 0 and 3");
			}
		}
		return choice;
	}

	// Restricts input returns integer between 1 and 4
	private int getInputSubMenuTwo() {
		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (choice < 1 || choice > 4) {
			try {
				choice = Integer.parseInt(br.readLine());
				if (choice < 1 || choice > 4) {
					System.out.println("Please enter one number between 0 and 4");
				}
			} catch (NumberFormatException | IOException e) {
				System.out.println("Please enter a valid input. A number between 0 and 4");
			}
		}
		return choice;
	}

	// Input method without restrictions
	private int getInputUnrestricted() {
		int choice = -1;
		boolean flag;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			flag = true;
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Please enter a valid input. A number between 0 and 4");
				flag = false;
			}
		} while (!flag);
		return choice;
	}
}
