import java.util.ArrayList;

public class Invoice{
  
  private String code;
  private Customer buyer;
  private Person seller;
  private ArrayList<Product> cart;
  
  //our class for all invoices for products
  
  public Invoice(String code, Customer buyer, Person seller, String date, ArrayList<Product> cart){
    this.code = code;
    this.buyer = buyer;
    this.seller = seller;
    this.cart = cart;
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
  
}
