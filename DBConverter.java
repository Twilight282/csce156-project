import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ceg.ext.InvoiceData;

//our data converter class. this reads the data from the database, and stores them based on fields.

public class DBConverter {

	public ArrayList<Product> products;
	public ArrayList<Customer> customers;
	public ArrayList<Person> persons;
	public LinkedList<Invoice> invoices;
	
	public ArrayList<Person> loadPersons(){
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps, ps2;
		ResultSet rs, rs2;

		persons = new ArrayList<Person>();
		
		String getPeople = "select * from Persons;";
		String emailQuery = "select * from Emails where PersonID = ?;";
		
		try {
			ps = conn.prepareStatement(getPeople);
			rs = ps.executeQuery();
			
		    while (rs.next()) {
		        String personID = rs.getString("PersonID");
		        String personCode = rs.getString("PersonCode");
		        String[] nameParts = rs.getString("PersonName").split(" ");
		        Name personName;
				String[] emails;
		        if (nameParts.length > 1) personName = new Name(nameParts[1].trim(), nameParts[0].trim());
		        else personName = new Name(nameParts[0].trim(), "");
		        
		        Address add = new Address(rs.getString("Street"), rs.getString("City"), rs.getString("State"), rs.getString("Zip"), rs.getString("Country"));
		        
		        ps2 = conn.prepareStatement(emailQuery);
		        ps2.setString(1, personID);
		        rs2 = ps2.executeQuery();
		        ArrayList<String> emailTemp = new ArrayList<String>();
		        while (rs2.next()){
		        	emailTemp.add(rs2.getString("Email"));
		        }
		        emails = new String[emailTemp.size()];
		        for (int i = 0; i < emails.length; i++){
		        	emails[i] = emailTemp.get(i);
		        }
		        Person emp = new Person(personCode, personName, add, emails);
		        persons.add(emp);
		    }
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return persons;
	}
	
	public ArrayList<Customer> loadCustomers(){
		
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps, ps2;
		ResultSet rs, rs2;

		customers = new ArrayList<Customer>();
		
		String getPeople = "select * from Customers;";
		
		try {
			ps = conn.prepareStatement(getPeople);
			rs = ps.executeQuery();
			
		    while (rs.next()) {
		        String customerID = rs.getString("CustomerID");
		        String customerCode = rs.getString("CustomerCode");
		        String name = rs.getString("CustomerName");
		        
		        Address add = new Address(rs.getString("Street"), rs.getString("City"), rs.getString("State"), rs.getString("Zip"), rs.getString("Country"));
		        String contactID = rs.getString("ContactID");
		        String contactCode = rs.getString("ContactCode");
		        
		        Customer c;
		        if (rs.getInt("CustomerType") == 2) {c = new Student(customerCode, name, add, contactCode);}
		        else {c = new General(customerCode, name, add, contactCode);}
		        customers.add(c);
		    }
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return customers;
	}
	
	public ArrayList<Product> loadProducts(){
		
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps, ps2;
		ResultSet rs, rs2;
		
		products = new ArrayList<Product>();
		
		String getTickets = "select * from MovieTickets;";
		String getRefreshments = "select * from Refreshments;";
		String getParkingPasses = "select * from ParkingPasses;";
		String getSeasonPasses = "select * from SeasonPasses;";
		
		try {
			ps = conn.prepareStatement(getTickets); 
			rs = ps.executeQuery();
			
		    while (rs.next()) {

		        String productID = rs.getString("ProductID");
		        String productCode = rs.getString("ProductCode");
		        String movieName = rs.getString("MovieName");
		        double price = rs.getDouble("Price");
		        String date = rs.getString("Date");
		        String screen = rs.getString("ScreenNumber");
		        
		        Address add = new Address(rs.getString("Street"), rs.getString("City"), rs.getString("State"), rs.getString("Zip"), rs.getString("Country"));
		        
		        Product p = new MovieTicket(productCode,price, date, movieName, screen, add);
		        
		        products.add(p);
		    }
		    ps = conn.prepareStatement(getRefreshments);
			rs = ps.executeQuery();
			
		    while (rs.next()) {

		        String productID = rs.getString("ProductID");
		        String productCode = rs.getString("ProductCode");
		        String name = rs.getString("RefreshmentName");
		        double price = rs.getDouble("Price");

		        Product p = new Refreshment(productCode,price, name);
		        
		        products.add(p);
		    }
		    ps = conn.prepareStatement(getParkingPasses);
			rs = ps.executeQuery();
			
		    while (rs.next()) {

		        String productID = rs.getString("ProductID");
		        String productCode = rs.getString("ProductCode");
		        double price = rs.getDouble("Price");

		        Product p = new ParkingPass(productCode,price);
		        
		        products.add(p);
		    }
		    ps = conn.prepareStatement(getSeasonPasses);
			rs = ps.executeQuery();
			
		    while (rs.next()) {

		        String productID = rs.getString("ProductID");
		        String productCode = rs.getString("ProductCode");
		        String passName = rs.getString("PassName");
		        double price = rs.getDouble("Price");
		        String sdate = rs.getString("StartDate");
		        String edate = rs.getString("EndDate");
		        
		        Product p = new SeasonPass(productCode,price,sdate,edate,passName);
		        
		        products.add(p);
		    }
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return products;
	}
	
	public LinkedList<Invoice> loadInvoices(){
		
		LinkedList<Invoice> invoices = new LinkedList<Invoice>();
		
		Connection conn = InvoiceData.getConnection();
		PreparedStatement ps, ps2;
		ResultSet rs, rs2;
		
		String getInvoices = "select * from Invoices;";
		String getProducts = "select * from InvoiceProducts where InvoiceID = ?;";
		
		try {
			ps = conn.prepareStatement(getInvoices);
			rs = ps.executeQuery();
			
			System.out.println(rs);
			
		    while (rs.next()) {
		        String invoiceID = rs.getString("InvoiceID");
		        String invoiceCode = rs.getString("InvoiceCode");
		        String customerCode = rs.getString("CustomerCode");
		        String personCode = rs.getString("PersonCode");
		        String date = rs.getString("Date");
		        
		        Customer buyer = null;
		        Person seller = null;
		        Person contact = null;
		        
		        for (Customer i : customers){
		        	if (i.getCode().equals(customerCode)){
		        		buyer = i;
		        	}
		        }
		        System.out.println("looking for " + buyer.getContact());
		        for (Person i : persons){
		        	if (i.getCode().equals(personCode)){
		        		seller = i;
		        	}
		        	if (i.getCode().equals(buyer.getContact())){
		        		contact = i;
		        	}
		        	System.out.println(i.getCode());
		        }
		        
		        ps2 = conn.prepareStatement(getProducts);
		        ps2.setString(1, invoiceID);
		        rs2 = ps2.executeQuery();
		        
		        ArrayList<Product> cart = new ArrayList<Product>();
		        String productCode;
		        
		        while(rs2.next()){
		        	productCode = rs2.getString("ProductCode");
		        	for (Product i : products){
		        		if (i.getCode().equals(productCode)){		
							if (i instanceof ParkingPass){
								ParkingPass p = new ParkingPass((ParkingPass)(i));
								p.setNum(rs2.getInt("Quantity"));
								cart.add(p);
							}
							else if (i instanceof MovieTicket){
								MovieTicket m = new MovieTicket((MovieTicket)(i));
								m.setNum(rs2.getInt("Quantity"));
								cart.add(m);
							}
							else if (i instanceof SeasonPass){
								SeasonPass s = new SeasonPass((SeasonPass)(i));
								s.setNum(rs2.getInt("Quantity"));
								s.setEffectiveDate(date);
								cart.add(s);
							}
							else if (i instanceof Refreshment){
								Refreshment r = new Refreshment((Refreshment)(i));
								r.setNum(rs2.getInt("Quantity"));
								cart.add(r);
							}
		        			System.out.println("Adding " + i);
		        		}
		        	}
		        }
		        
		        Invoice inv;
		        inv = new Invoice(invoiceCode, buyer, seller, date, cart, contact, buyer.getCode());
		        invoices.add(inv);
		    }
		} 
		catch (SQLException e) 
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return invoices;
	}
}
