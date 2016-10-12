
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//our data converter class. this reads the data from each data file, and stores them based
//on field

public class FlatConverter {
	public ArrayList<Employee> loadEmployees(){
		Scanner reads = null;
		try{
			reads = new Scanner(new File("data/Persons.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = Integer.parseInt(reads.nextLine().trim());
		
		ArrayList<Employee> emps = new ArrayList<Employee>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0].trim();
			String[] nameParts = parts[1].split(",");
			Name name = new Name(nameParts[1].trim(), nameParts[0].trim());
			String[] adParts = parts[2].split(",");
			Address add = new Address(adParts[0].trim(), adParts[1].trim(), adParts[2].trim(), adParts[3].trim(), adParts[4].trim());
			String[] emails = {};
			if (parts.length > 3) emails = parts[3].split(",");
			
			Employee emp = new Employee(code, name, add, emails);
			emps.add(emp);
		}
		reads.close();
		return emps;
	}
	
	public ArrayList<Customer> loadCustomers(){
		Scanner reads = null;
		try{
			reads = new Scanner(new File("data/Customers.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = Integer.parseInt(reads.nextLine().trim());
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0].trim();
			String contact = parts[2].trim();
			String name = parts[3].trim();
			String[] adParts = parts[4].split(",");
			Address add = new Address(adParts[0].trim(), adParts[1].trim(), adParts[2].trim(), adParts[3].trim(), adParts[4].trim());
			Customer c;
			if (parts[1].equals("G")) c = new General(code, name, add, contact);
			else c = new Student(code, name, add, contact);
			customers.add(c);
		}
		reads.close();
		return customers;
	}
	
	public ArrayList<Product> loadProducts(){
		Scanner reads = null;
		try{
			reads = new Scanner(new File("data/Products.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = Integer.parseInt(reads.nextLine().trim());
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0].trim();
			Product p = null;
			if (parts[1].equals("M")){
				String dateTime = parts[2].trim();
				String name = parts[3].trim();
				String[] adParts = parts[4].split(",");
				Address add = new Address(adParts[0].trim(), adParts[1].trim(), adParts[2].trim(), adParts[3].trim(), adParts[4].trim());
				String screen = parts[5].trim();
				double price = Double.parseDouble(parts[6].trim());
				p = new MovieTicket(code, price, dateTime, name, screen, add);
			}
			else if (parts[1].equals("S")){
				String name = parts[2].trim();
				String startDate = parts[3].trim();
				String endDate = parts[4].trim();
				double price = Double.parseDouble(parts[5].trim());
				p = new SeasonPass(code, price, startDate, endDate, name);
			}
			else if (parts[1].equals("P")){
				double price = Double.parseDouble(parts[2].trim());
				p = new ParkingPass(code, price);
			}
			else if (parts[1].equals("R")){
				String name = parts[2];
				double price = Double.parseDouble(parts[3].trim());
				p = new Refreshment(code, price, name);
			}
			
			products.add(p);
		}
		reads.close();
		return products;
	}
}
