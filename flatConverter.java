
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class flatConverter {
	public ArrayList<Employee> loadEmployees(){
		Scanner reads;
		try{
			reads = new Scanner(new FileReader("data/persons.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = reads.nextInt();
		
		ArrayList<Employee> emps = new ArrayList<Employee>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0];
			String[] nameParts = parts[1].split(",");
			Name name = new Name(nameParts[1], nameParts[0]);
			String[] adParts = parts[2].split(",");
			Address add = new Address(adParts[0], adParts[1], adParts[2], Integer.parseInt(adParts[3]), adParts[4]);
			String[] emails = parts[3].split(",");
			Employee emp = new Employee(code, name, add, emails);
			emps.add(emp);
		}
		reads.close();
		return emps;
	}
	
	public ArrayList<Customer> loadCustomers(){
		Scanner reads;
		try{
			reads = new Scanner(new FileReader("data/customers.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = reads.nextInt();
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0];
			String contact = parts[2];
			Name name = new Name(parts[3]);
			String[] adParts = parts[4].split(",");
			Address add = new Address(adParts[0], adParts[1], adParts[2], Integer.parseInt(adParts[3]), adParts[4]);
			String[] emails = parts[3].split(",");
			Customer c;
			if (parts[1].equals("G")) c = new General(code, name, add, contact);
			else c = new Student(code, name, add, contact);
			customers.add(c);
		}
		reads.close();
		return customers;
	}
	
	public ArrayList<Product> getProducts(){
		Scanner reads;
		try{
			reads = new Scanner(new FileReader("data/customers.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = reads.nextInt();
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0];
			Product p;
			if (parts[1].equals("M")){
				String dateTime = parts[2];
				String name = parts[3];
				String[] adParts = parts[4].split(",");
				Address add = new Address(adParts[0], adParts[1], adParts[2], Integer.parseInt(adParts[3]), adParts[4]);
				String screen = parts[5];
				double price = Double.parseDouble(parts[6]);
				p = new MovieTicket(code, price, dateTime, name, screen, add);
			}
			else if (parts[1].equals("S")){
				String name = parts[2];
				String startDate = parts[3];
				String endDate = parts[4];
				double price = Double.parseDouble(parts[5]);
				p = new SeasonPass(code, price, startDate, endDate, name);
			}
			else if (parts[1].equals("P")){
				double price = Double.parseDouble(parts[2]);
				p = new ParkingPass(code, price);
			}
			else if (parts[1].equals("R")){
				String name = parts[2];
				double price = Double.parseDouble(parts[3]);
				p = new Refreshment(code, price, name);
			}
			
			products.add(p);
		}
		reads.close();
		return products;
	}
}
