import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;


//our xml writer class. this writes out each List to its own xml file.

public class Writer {
	public static void personWriter(ArrayList<Person> person) {
		XStream xstream = new XStream();
	
		File xmlOut = new File("data/People.xml");
	
		PrintWriter xmlPrinter = null;
		try {
			xmlPrinter = new PrintWriter(xmlOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrinter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("person", Person.class);
		for (Person a : person) {
			String personOut = xstream.toXML(a);
			xmlPrinter.write(personOut + "\n");
		}
		
		xmlPrinter.close();
	}
	
	public static void productWriter(ArrayList<Product> product) {
		XStream xstream = new XStream();
	
		File xmlOut = new File("data/Products.xml");
	
		PrintWriter xmlPrinter = null;
		try {
			xmlPrinter = new PrintWriter(xmlOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrinter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("product", Product.class);
		for (Product a : product) {
			String productOut = xstream.toXML(a);
			xmlPrinter.write(productOut + "\n");
		}
		
		xmlPrinter.close();
	}
	
	public static void customerWriter(ArrayList<Customer> customer) {
		XStream xstream = new XStream();
	
		File xmlOut = new File("data/Customer.xml");
	
		PrintWriter xmlPrinter = null;
		try {
			xmlPrinter = new PrintWriter(xmlOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrinter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("customer", Customer.class);
		for (Customer a : customer) {
			String customerOut = xstream.toXML(a);
			xmlPrinter.write(customerOut + "\n");
		}
		
		xmlPrinter.close();
	}
	
	public static void invoiceWriter(ArrayList<Invoice> invoice){
		double subtotal, tax, total, fees = 0.0;
		double finalsub, finaltax, finaltotal = 0.0;
		double discount = 0.0;
		double sum = 0.0;
		String type;
		
		System.out.println("========================");
		System.out.println("Executive Summary Report");
		System.out.println("========================");
		System.out.printf("%s %s %s %s %s %s %s %s\n", "Invoice", "Customer", "Salesperon", "Subtotal", "Fees", "Taxes", "Discount", "Total");
		for (Invoice inv : invoice) {
			String code = inv.getCode();
			Customer buyer = inv.getBuyer();
			Person seller = inv.getSeller();
			ArrayList<Product> cart = inv.getCart();
			
			subtotal = 0.0;
			tax = 0.0;
			total = 0.0;
			finalsub = 0.0;
			finaltax = 0.0;
			finaltotal = 0.0;
			discount = 0.0;
			sum = 0.0;
			
			for (Product prods : cart) {
				if (prods instanceof ParkingPass) {
					subtotal = prods.getPrice();
					tax = subtotal * 0.04;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
				if (prods instanceof MovieTicket) {
					subtotal = prods.getPrice();
					tax = subtotal * 0.06;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
				if (prods instanceof SeasonPass) {
					subtotal = prods.getPrice();
					tax = subtotal * 0.06;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
				if (prods instanceof Refreshment) {
					subtotal = prods.getPrice();
					tax = subtotal * 0.04;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
		}

			
			if (buyer instanceof Student){
				type = "[Student]";
				discount = finaltax + (finalsub*0.08);
				fees = 6.75;
				sum = sum + 6.75 - discount;
			}
			else
				type = "[General]";
			System.out.printf("%s %s %s %s, %s  $%.2f $%.2f $%.2f $-%.2f $%.2f\n", code, buyer.getName(), type, seller.getName().getLast(), seller.getName().getFirst(), finalsub, fees, finaltax, discount, sum);
		}
		
		for (Invoice inv : invoice) {
			subtotal = 0.0;
			tax = 0.0;
			total = 0.0;
			finalsub = 0.0;
			finaltax = 0.0;
			finaltotal = 0.0;
			discount = 0.0;
			sum = 0.0;
			
			String code = inv.getCode();
			Customer buyer = inv.getBuyer();
			Person seller = inv.getSeller();
			Person contact = inv.getContact();
			ArrayList<Product> cart = inv.getCart();
				
			System.out.println("Individual Invoice Detail Reports");
			System.out.println("=================================");
			System.out.println(code);
			System.out.println("=================================");
			System.out.printf("Salesperson: %s, %s\n", seller.getName().getLast(), seller.getName().getFirst());
			System.out.println("Customer Info:");
			System.out.printf("  %s (%s)\n", buyer.getName(), inv.getBuyerCode());
			if (buyer instanceof Student)
				System.out.println("  [Student]");
			else
				System.out.println("  [General]");
			System.out.println("  " + contact.getName().getLast() + ", " + contact.getName().getFirst());
			System.out.println("  " + buyer.getAddress().getStreet());
			System.out.println("  " + buyer.getAddress().getCity()+", "+buyer.getAddress().getState()+" "+buyer.getAddress().getZip()+" "+buyer.getAddress().getCountry());
			System.out.println("=================================");
			System.out.printf("%-7s %-60s %-20s %-15s %s%n", "Code", "Item", "Subtotal", "Tax", "Total");
			for (Product prods : cart) {
					if (prods instanceof ParkingPass) {
						subtotal = prods.getPrice();
						tax = subtotal * 0.04;
						total = subtotal + tax;
						finaltax += tax;
						finalsub += subtotal;
						finaltotal += total;
						sum += total;
						System.out.printf("%-7s Parking Pass %40s%.2f  %s%.2f %s%.2f%n", prods.getCode(), "$", subtotal, "$", tax, "$",  total);
					}
					if (prods instanceof MovieTicket) {
						subtotal = prods.getPrice();
						tax = subtotal * 0.06;
						total = subtotal + tax;
						finaltax += tax;
						finalsub += subtotal;
						finaltotal += total;
						sum += total;
						System.out.printf("%-7s MovieTicket '%s' @ %s %40s%.2f  %s%.2f %s%.2f%n", prods.getCode(), ((MovieTicket) prods).getName(), ((MovieTicket) prods).getAddress().getStreet(), "$", subtotal, "$", tax, "$", total);
						System.out.printf("        %1s%n", ((MovieTicket) prods).getDateTime());
					}
					if (prods instanceof SeasonPass) {
						subtotal = prods.getPrice();
						tax = subtotal * 0.06;
						total = subtotal + tax;
						finaltax += tax;
						finalsub += subtotal;
						finaltotal += total;
						sum += total;
						System.out.printf("%-7s Season Pass %40s%.2f %s%.2f %s%.2f%n", prods.getCode(), "$", subtotal, "$", tax, "$", total);
					}
					if (prods instanceof Refreshment) {
						subtotal = prods.getPrice();
						tax = subtotal * 0.04;
						total = subtotal + tax;
						finaltax += tax;
						finalsub += subtotal;
						finaltotal += total;
						sum += total;
						System.out.printf("%-7s Refreshment %s %40s%.2f  %s%.2f  %s%.2f%n", prods.getCode(), ((Refreshment) prods).getName(), "$", subtotal, "$", tax, "$", total);
					}
			}
			System.out.println("                                                                      ------------------------------------------");
			System.out.printf("SUB-TOTALS %s%.2f %s%.2f %s%.2f\n", "$", finalsub, "$", finaltax, "$", finaltotal);
			
			
			if (buyer instanceof Student) {
				discount = (finaltax + (finalsub*0.08));
				System.out.printf("DISCOUNT (8%% Student Discount %% NO Tax)           %s-%.2f\n", "$", discount);
				System.out.println("Additional Fee (Validation)                        $ 6.75");
				System.out.printf("TOTAL      %.2f\n", sum-discount+6.75);
			}
			else
				System.out.printf("TOTAL      %.2f\n", sum);
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}
}
