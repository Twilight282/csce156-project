import java.util.ArrayList;

public class Invoice implements Comparable<Invoice>{
  
  private String code;
  private Customer buyer;
  private Person seller;
  private ArrayList<Product> cart;
  private Person contact;
  private String buyerCode;
  private String date;
  private double subtotal = 0.0;
  private double tax = 0.0;
  private double total = 0.0;
  private double fees = 0.0;
  private double discount = 0.0;
  
  //our class for all invoices for products
  
  public Invoice(String code, Customer buyer, Person seller, String date, ArrayList<Product> cart, Person contact, String buyerCode){
    this.code = code;
    this.buyer = buyer;
    this.date = date;
    this.seller = seller;
    this.cart = cart;
    this.buyerCode = buyerCode;
    this.contact = contact;
    
    this.genPriceData();
  }
  
  public String getCode(){
    return this.code;
  }
  
  public Customer getBuyer(){
    return this.buyer;
  }
  
  public Person getSeller(){
    return this.seller;
  }
  
  public ArrayList<Product> getCart(){
    return this.cart;
  }
  
  public Person getContact(){
	    return this.contact;
  }
  
  public String getBuyerCode(){
	    return this.buyerCode;
}
  public String getDate(){
	  return this.date;
  }
  
  public double genPriceData(){
  	ArrayList<Product> cart = this.getCart();
	
  	double itemSubtotal = 0.0;
  	double itemTotal = 0.0;
  	double itemTax = 0.0;
	subtotal = 0.0;
	tax = 0.0;
	total = 0.0;
	fees = 0.0;
	discount = 0.0;
	
	for (Product prods : cart){
		itemSubtotal = 0.0;
		itemTotal = 0.0;
		itemTax = 0.0;
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
			itemSubtotal = prods.getPrice()*(prods.getNum()-free);
			itemTax = itemSubtotal * 0.04;
			itemTotal = itemSubtotal + itemTax;
			subtotal += itemSubtotal;
			tax += itemTax;
			total += itemTotal;
		}
		if (prods instanceof MovieTicket) {
			itemSubtotal = prods.getPrice()*prods.getNum();
			boolean cheaper = DateData.tuesOrThurs(((MovieTicket)prods).getDateTime().split(" ")[0]);
			if (cheaper == true){
				itemSubtotal *= .93;
			}
			itemTax = itemSubtotal * 0.06;
			itemTotal = itemSubtotal + itemTax;
			subtotal += itemSubtotal;
			tax += itemTax;
			total += itemTotal;
		}
		if (prods instanceof SeasonPass) {
			itemSubtotal = prods.getPrice()*prods.getNum();
			int time1 = DateData.elapsedDays(this.getDate(), ((SeasonPass)prods).getEndDate());
			int time2 = DateData.elapsedDays(((SeasonPass)prods).getStartDate(), ((SeasonPass)prods).getEndDate());
			if (time1 < time2) //if it's midway through the season
				itemSubtotal *= ((double)time1)/time2; //prorate cost
			itemSubtotal += 8*prods.getNum(); //add convenience fee
			itemTax = itemSubtotal * 0.06;
			itemTotal = itemSubtotal + itemTax;
			subtotal += itemSubtotal;
			tax += itemTax;
			total += itemTotal;
		}
		if (prods instanceof Refreshment) {
			itemSubtotal = prods.getPrice()*prods.getNum();
			for (Product p : cart){
				if (p instanceof Ticket) {
					itemSubtotal *= .95;
					break;
				}
			}
			itemTax = itemSubtotal * 0.04;
			itemTotal = itemSubtotal + itemTax;
			subtotal += itemSubtotal;
			tax += itemTax;
			total += itemTotal;
		}
	}
	
	
	if (buyer instanceof Student){
		discount += tax + (subtotal*0.08);
		fees = 6.75;
	}
	else {
		fees = 0.0;
	}
	
	total += fees-discount;
	
	return total;
  }
  
  public double getTotal(){
	  return this.total;
  }
  
  public double getSubtotal() {
	return subtotal;
	}
	
  public double getTax() {
	return tax;
	}
	
  public double getDiscount() {
	return discount;
	}
  
  public double getFees(){
	  return fees;
  }

public int compareTo(Invoice i){
	  if (this.getTotal() < i.getTotal()) return -1;
	  if (this.getTotal() > i.getTotal()) return 1;
	  return 0;
  }
  
}
