public class Product{

  protected double price;
  protected String code;
  protected int num;

//our product class. creates our products.
  public Product(String code, double price, int num){
    this.price = price;
    this.code = code;
    this.num = num;
  }
  
  public Product(String code, double price){
	    this.price = price;
	    this.code = code;
	    this.num = 1;
	  }

  public double getPrice(){
    return this.price * this.num;
  }
  
  public String getCode(){
    return this.code;
  }

  public int getNum() {
	return num;
  }

  public void setNum(int num) {
	this.num = num;
  }

}
