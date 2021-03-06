import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//import com.thoughtworks.xstream.XStream;


//our xml writer class. this writes out each List to its own xml file.

public class Writer {
	/*public static void personWriter(ArrayList<Person> person) {
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
	*/
	
	public static void invoiceWriter(ArrayList<Invoice> invoice){
		double subtotal, tax, total, fees = 0.0;
		double finalsub, finaltax, finaltotal = 0.0;
		double totalsub = 0, totaltax = 0, totaltotal = 0, totaldiscount = 0.0, totalsum = 0.0;
		double discount = 0.0;
		double sum = 0.0;
		int freePasses = 0;
		Customer buyer = null;
		String type;
		
		System.out.println("========================");
		System.out.println("Executive Summary Report");
		System.out.println("========================");
		System.out.printf("%-15s %-35s %-20s %11s %11s %11s %11s %11s\n", "Invoice", "Customer", "Salesperon", "Subtotal", "Fees", "Taxes", "Discount", "Total");
		for (Invoice inv : invoice) {
			String code = inv.getCode();
			buyer = inv.getBuyer();
			Person seller = inv.getSeller();
			ArrayList<Product> cart = inv.getCart();
			
			subtotal = 0.0;
			tax = 0.0;
			total = 0.0;
			fees = 0.0;
			finalsub = 0.0;
			finaltax = 0.0;
			finaltotal = 0.0;
			discount = 0.0;
			sum = 0.0;
			
			for (Product prods : cart){
				if (prods instanceof ParkingPass) {
					int free = 0;
					for (Product p : cart){
						if (p.getCode().equals(((ParkingPass)prods).getDiscountCode())){
							free += p.getNum();
						}
					}
					if (free > prods.getNum()){
						free = prods.getNum();
					}
					subtotal = prods.getPrice()*(prods.getNum()-free);
					tax = subtotal * 0.04;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
				if (prods instanceof MovieTicket) {
					subtotal = prods.getPrice()*prods.getNum();
					boolean cheaper = DateData.tuesOrThurs(((MovieTicket)prods).getDateTime().split(" ")[0]);
					if (cheaper == true){
						subtotal *= .93;
					}
					tax = subtotal * 0.06;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
				if (prods instanceof SeasonPass) {
					subtotal = prods.getPrice()*prods.getNum();
					int time1 = DateData.elapsedDays(inv.getDate(), ((SeasonPass)prods).getEndDate());
					int time2 = DateData.elapsedDays(((SeasonPass)prods).getStartDate(), ((SeasonPass)prods).getEndDate());
					if (time1 < time2) //if it's midway through the season
						subtotal *= ((double)time1)/time2; //prorate cost
					subtotal += 8*prods.getNum(); //add convenience fee
					tax = subtotal * 0.06;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
				}
				if (prods instanceof Refreshment) {
					subtotal = prods.getPrice()*prods.getNum();
					for (Product p : cart){
						if (p instanceof Ticket) {
							subtotal *= .95;
							break;
						}
					}
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
				discount += finaltax + (finalsub*0.08);
				fees = 6.75;
				sum = sum + 6.75 - discount;
			}
			else {
				type = "[General]";
				fees = 0.0;
			}
			System.out.printf("%-15s %-35s %-20s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f\n", code, buyer.getName() + type, seller.getName().getLast() + ", " + seller.getName().getFirst(), finalsub, fees, finaltax, -discount, sum);
			totalsub += finalsub;
			totaltax += finaltax;
			totaltotal += finaltotal;
			totaldiscount += discount;
			totalsum += sum;
		}

		System.out.println("===========================================================================================================================================================================");
		System.out.printf("%-72s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f\n", "TOTAL", totalsub, totaltax, totaltotal, totaldiscount, totalsum);
		System.out.println();
		
		String code2;
		Customer buyer2;
		Person seller2;
		Person contact2;
		
		for (Invoice inv2 : invoice) {
			subtotal = 0.0;
			tax = 0.0;
			total = 0.0;
			finalsub = 0.0;
			finaltax = 0.0;
			finaltotal = 0.0;
			discount = 0.0;
			sum = 0.0;
			
			code2 = inv2.getCode();
			buyer2 = inv2.getBuyer();
			seller2 = inv2.getSeller();
			contact2 = inv2.getContact();
			ArrayList<Product> cart2 = inv2.getCart();
				
			System.out.println("Individual Invoice Detail Reports");
			System.out.println("=================================");
			System.out.println(code2);
			System.out.println("=================================");
			System.out.printf("Salesperson: %s, %s\n", seller2.getName().getLast(), seller2.getName().getFirst());
			System.out.println("Customer Info:");
			System.out.printf("  %s (%s)\n", buyer2.getName(), inv2.getBuyerCode());
			if (buyer2 instanceof Student)
				System.out.println("  [Student]");
			else
				System.out.println("  [General]");
			System.out.println("  " + contact2.getName().getLast() + ", " + contact2.getName().getFirst());
			System.out.println("  " + buyer2.getAddress().getStreet());
			System.out.println("  " + buyer2.getAddress().getCity()+", "+buyer2.getAddress().getState()+" "+buyer2.getAddress().getZip()+" "+buyer2.getAddress().getCountry());
			System.out.println("=================================");
			System.out.printf("%-7s %-58s %10s %11s %11s%n", "Code", "Item", "Subtotal", "Tax", "Total");
			
			
			for (Product prods : cart2) {
				if (prods instanceof ParkingPass) {
					int free = 0;
					for (Product p : cart2){
						if (p.getCode().equals(((ParkingPass)prods).getDiscountCode())){
							free += p.getNum();
						}
					}
					if (free > prods.getNum()){
						free = prods.getNum();
					}
					subtotal = prods.getPrice()*(prods.getNum()-free);
					tax = subtotal * 0.04;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
					System.out.printf("%-7s %-57s $%10.2f $%10.2f $%10.2f%n", prods.getCode(), "Parking Pass (" + prods.getNum() +" units @ $"+ prods.getPrice()+"/unit with " + free + " free.", subtotal, tax, total);
						
				}
				if (prods instanceof MovieTicket) {
					subtotal = prods.getPrice()*prods.getNum();
					boolean cheaper = DateData.tuesOrThurs(((MovieTicket)prods).getDateTime().split(" ")[0]);
					if (cheaper == true){
						subtotal *= .93;
					}
					tax = subtotal * 0.06;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
					System.out.printf("%-7s %-57s $%10.2f $%10.2f $%10.2f%n", prods.getCode(), "Movie Ticket '" + ((MovieTicket) prods).getName() + "' @ " + ((MovieTicket) prods).getAddress().getStreet(), subtotal, tax, total);
					if (cheaper == true)
						System.out.printf("        %1s (%d units @ $%.2f/unit - Tue/Thu 7%% Off)%n", ((MovieTicket) prods).getDateTime(), prods.getNum(), prods.getPrice());
					else
						System.out.printf("        %1s (%d units @ $%.2f/unit)%n", ((MovieTicket) prods).getDateTime(), prods.getNum(), prods.getPrice());
				}
				if (prods instanceof SeasonPass) {
					subtotal = prods.getPrice()*prods.getNum();
					int time1 = DateData.elapsedDays(inv2.getDate(), ((SeasonPass)prods).getEndDate());
					int time2 = DateData.elapsedDays(((SeasonPass)prods).getStartDate(), ((SeasonPass)prods).getEndDate());
					if (time1 < time2) //if it's midway through the season
						subtotal *= ((double)time1)/time2; //prorate cost
					subtotal += 8*prods.getNum(); //add convenience fee
					tax = subtotal * 0.06;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
					System.out.printf("%-7s %-57s $%10.2f $%10.2f $%10.2f%n", prods.getCode(), "Season Pass - " + ((SeasonPass)prods).getName(), subtotal, tax , total);
					System.out.printf("        (%d units @ $%.2f/unit + $8 fee/unit)%n", prods.getNum(), prods.getPrice());
				}
				if (prods instanceof Refreshment) {
					subtotal = prods.getPrice()*prods.getNum();
					boolean discounted = true;
					for (Product p : cart2){
						if (p instanceof Ticket) {
							subtotal *= .95;
							break;
						}
					}
					tax = subtotal * 0.04;
					total = subtotal + tax;
					finaltax += tax;
					finalsub += subtotal;
					finaltotal += total;
					sum += total;
					if (discounted == true){
						System.out.printf("%-7s %-57s $%10.2f $%10.2f $%10.2f%n", prods.getCode(), "Refreshment " + ((Refreshment) prods).getName() + " (" + prods.getNum() + " @ $" + prods.getPrice() + "/unit with 5% off)", subtotal, tax, total);
					}
					else{
						System.out.printf("%-7s %-57s $%10.2f $%10.2f $%10.2f%n", prods.getCode(), "Refreshment " + ((Refreshment) prods).getName() + " (" + prods.getNum() + " @ $" + prods.getPrice() + "/unit)", subtotal, tax, total);
					}
				}
			}
			System.out.println("                                                                      ------------------------------------------");
			System.out.printf("%-65s $%10.2f $%10.2f $%10.2f\n", "SUB-TOTALS", finalsub, finaltax, finaltotal);
			
			
			if (buyer2 instanceof Student) {
				discount = (finaltax + (finalsub*0.08));
				System.out.printf("%-89s $%10.2f\n", "DISCOUNT (8% Student Discount & NO Tax)", -discount);
				System.out.println("Additional Fee (Validation)                                                               $      6.75");
				System.out.printf("%-89s $%10.2f\n", "TOTAL", sum-discount+6.75);
			}
			else
				System.out.printf("%-89s $%10.2f\n", "TOTAL", sum);
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println();
			}
		}
	}


