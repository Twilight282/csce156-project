import java.util.ArrayList;


public class InvoiceWriter {
	
	public static void invoiceWriter(ArrayList<Invoice> invoice){
		double totalSub = 0;
		double totalFees = 0;
		double totalTax = 0;
		double totalDiscount = 0;
		double totalTotal = 0;
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
			
			if (buyer instanceof Student) type = "[Student]";
			else type = "[General]";
			
			System.out.printf("%-15s %-35s %-20s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f\n", code, buyer.getName() + type, seller.getName().getLast() + ", " + seller.getName().getFirst(), inv.getSubtotal(), inv.getFees(), inv.getTax(), -inv.getDiscount(), inv.getTotal());
			totalSub += inv.getSubtotal();
			totalFees += inv.getFees();
			totalTax += inv.getTax();
			totalDiscount -= inv.getDiscount();
			totalTotal += inv.getTotal();
		}

		System.out.println("===========================================================================================================================================================================");
		System.out.printf("%-72s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f\n", "TOTAL", totalSub, totalFees, totalTax, totalDiscount, totalTotal);
		System.out.println();
		
		String code2;
		Customer buyer2;
		Person seller2;
		Person contact2;
		
		double subtotal,tax,total,finalsub,finaltax,finaltotal,discount,sum;
		
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


