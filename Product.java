public class Product{

  protected double price;
  protected String code;

  public Product(String code, double price){
    this.price = price;
    this.code = code;
  }

  public double getPrice(){
    return this.price;
  }
  
  public String getCode(){
    return this.code;
  }

}
