import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class InvoiceReport {

	private static ArrayList<Product> products;
	private static ArrayList<Customer> customers;
	private static ArrayList<Person> persons;
	
	public static void main(String[] args) {
		FlatConverter fc = new FlatConverter();        //creates converter
		products = fc.loadProducts();   //creates Product list
		customers = fc.loadCustomers(); //creates Customer list
		persons = fc.loadPersons(); //creates Employee list
		ArrayList<Invoice> invoices = loadInvoices();
		InvoiceWriter.invoiceWriter(invoices);
		
	}

	public static ArrayList<Invoice> loadInvoices(){
		Scanner reads = null;
		try{
			reads = new Scanner(new File("data/Invoices.dat"));
		}
		catch (FileNotFoundException e){
			System.out.println("Well this is a disaster:  ");
			e.printStackTrace();
		}
		int n = Integer.parseInt(reads.nextLine().trim());
		
		ArrayList<Invoice> invs = new ArrayList<Invoice>();
		
		while (reads.hasNextLine()){
			String line = reads.nextLine();
			String[] parts = line.split(";");
			String code = parts[0].trim();
			String buyerCode = parts[1].trim();

			Customer buyer = null;
			String contactCode = null;
			Person contact = null;
			for (Customer i : customers){
				if (i.getCode().equals(buyerCode)){
					buyer = i;
					contactCode = i.getContact();
				}
			}

			Person seller = null;
			String sellerCode = parts[2].trim();

			for (Person i : persons){
				if (i.getCode().equals(sellerCode)){
					seller = i;
				}
				if (i.getCode().equals(contactCode)){
					contact = i;
				}
			}
			String date = parts[3];
		
			
			ArrayList<Product> cart = new ArrayList<Product>();
			for (String i : parts[4].split(",")){
				String[] itemParts = i.split(":");
				for (Product j : products){
					if (itemParts[0].equals(j.getCode())){			
						if (j instanceof ParkingPass){
							ParkingPass p = new ParkingPass((ParkingPass)(j));
							if (itemParts.length == 3) p.discount(itemParts[2]);
							p.setNum(Integer.parseInt(itemParts[1]));
							cart.add(p);
						}
						else if (j instanceof MovieTicket){
							MovieTicket m = new MovieTicket((MovieTicket)(j));
							m.setNum(Integer.parseInt(itemParts[1]));
							cart.add(m);
						}
						else if (j instanceof SeasonPass){
							SeasonPass s = new SeasonPass((SeasonPass)(j));
							s.setNum(Integer.parseInt(itemParts[1]));
							s.setEffectiveDate(date);
							cart.add(s);
						}
						else if (j instanceof Refreshment){
							Refreshment r = new Refreshment((Refreshment)(j));
							r.setNum(Integer.parseInt(itemParts[1]));
							cart.add(r);
						}
						else System.out.println("ITEM NOT FOUND");
					}
				}
			}
			

			Invoice inv = new Invoice(code, buyer, seller, date, cart, contact, buyerCode);
			invs.add(inv);
		}
		reads.close();
		return invs;
	}
}
