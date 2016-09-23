import java.util.ArrayList;

public class Invoice{
  
  private String code;
  private Customer buyer;
  private Employee seller;
  private ArrayList<Product> cart;
  
  public Invoice(String code, Customer buyer, Employee seller, ArrayList<Product> cart){
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
  
  public Employee getSeller(){
    return this.seller;
  }
  
  public ArrayList<Product> getCart(){
    return this.cart;
  }
  
}
