package com.ceg.ext;

import java.sql.*;

/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Donot change any method signatures or the package name.
 * 
 */

public class InvoiceData {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/nesser";
	
	static final String USER = "nesser";
	static final String PASS = "Dh97eQ";

	static public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(InvoiceData.DB_URL, InvoiceData.USER, InvoiceData.PASS);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return conn;
	}

	
	public static void test() {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
		
		String test = "INSERT INTO Persons values (1234,1234,1234,1234,1234,1231,123,1234);";
		
		try {
			ps = conn.prepareStatement(test);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
		
		String removePerson = "DELETE FROM Persons;";
		
		try {
			ps = conn.prepareStatement(removePerson);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addPerson = "INSERT INTO Persons (PersonCode, PersonName, Street, City, State, Zip, Country) values (?, ?, ?, ?, ?, ?, ?);";
			
			String name = lastName + ", " + firstName;  
			
			ps = conn.prepareStatement(addPerson);
			ps.setString(1, personCode);
			ps.setString(2, name);
			ps.setString(3, street);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, zip);
			ps.setString(7, country);
			ps.executeQuery();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addEmail = "INSERT INTO Emails (Email, PersonCode) values (?, ?);";
			
			int personID = Integer.parseInt(personCode);
			
			ps = conn.prepareStatement(addEmail);
			ps.setString(1, email);
			ps.setString(2, personCode);
			ps.executeQuery();
			ps.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
		
		String removeCustomers = "DELETE * FROM Customers;";
		
		try {
			ps = conn.prepareStatement(removeCustomers);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		try {
			Connection conn = InvoiceData.getConnection();
			PreparedStatement ps;
			
			String addCustomer = "INSERT INTO Persons (CustomerCode, CustomerName, ContactID, CustomerType, Street, City, State, Zip, Country) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			int custType = Integer.parseInt(customerType);
			
			ps = conn.prepareStatement(addCustomer);
			ps.setString(1, customerCode);
			ps.setString(2, name);
			ps.setString(3, primaryContactPersonCode);
			ps.setInt(4, custType);
			ps.setString(5, street);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, zip);
			ps.setString(9, country);
			ps.executeQuery();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 5. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
		
		String removeProducts = "DELETE * FROM Products;";
		
		try {
			ps = conn.prepareStatement(removeProducts);
			ps.executeQuery();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addTicket = "INSERT INTO MovieTickets (ProductCode, MovieName, Price, Date, Street, City, State, Zip, Country, ScreenNumber) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			ps = conn.prepareStatement(addTicket);
			ps.setString(1, productCode);
			ps.setString(2, movieName);
			ps.setDouble(3, pricePerUnit);
			ps.setString(4, dateTime);
			ps.setString(6, street);
			ps.setString(6, city);
			ps.setString(7, state);
			ps.setString(8, zip);
			ps.setString(9, country);
			ps.setString(10, screenNo);
			ps.executeQuery();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addPass = "INSERT INTO SeasonPasses (ProductCode, PassName, StartDate, EndDate, Price) values (?, ?, ?, ?, ?);";
						
			ps = conn.prepareStatement(addPass);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setString(3, seasonStartDate);
			ps.setString(4, seasonEndDate);
			ps.setDouble(5, cost);
			ps.executeQuery();
			ps.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addPass = "INSERT INTO ParkingPasses (ProductCode, Price) values (?, ?);";
						
			ps = conn.prepareStatement(addPass);
			ps.setString(1, productCode);
			ps.setDouble(2, parkingFee);
			ps.executeQuery();
			ps.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name, double cost) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addRefresh = "INSERT INTO Refreshemnts (ProductCode, RefreshmentName, Price) values (?, ?, ?);";
						
			ps = conn.prepareStatement(addRefresh);
			ps.setString(1, productCode);
			ps.setString(2, name);
			ps.setDouble(3, cost);
			ps.executeQuery();
			ps.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	/**
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
		
		String removeInvoices = "DELETE * FROM Invoices;";
		
		try {
			ps = conn.prepareStatement(removeInvoices);
			ps.executeQuery();
			ps.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps;
						
		try {
			String addEmail = "INSERT INTO Invoices (InvoiceCode, CustomerCode, PersonCode, Date) values (?, ?, ?, ?);";
			
			ps = conn.prepareStatement(addEmail);
			ps.setString(1, invoiceCode);
			ps.setString(2, customerCode);
			ps.setString(3, salesPersonCode);
			ps.setString(4, invoiceDate);
			ps.executeQuery();
			ps.close();
			
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) {
		
	}

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) {}

     /**
     * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: ticketCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) {}
	
    /**
     * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {}

}
