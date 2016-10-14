import java.util.ArrayList;

public class Invoice{
  
  private String code;
  private Customer buyer;
  private Person seller;
  private ArrayList<Product> cart;
  private Person contact;
  private String buyerCode;
  
  //our class for all invoices for products
  
  public Invoice(String code, Customer buyer, Person seller, String date, ArrayList<Product> cart, Person contact, String buyerCode){
    this.code = code;
    this.buyer = buyer;
    this.seller = seller;
    this.cart = cart;
    this.buyerCode = buyerCode;
    this.contact = contact;
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
  
}
