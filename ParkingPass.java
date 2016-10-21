public class ParkingPass extends Service{

  private String discountCode;

  //parking passes for our services
  
  public ParkingPass(String code, double price){
    super(code, price);
    this.discountCode = null;
  }
  
  public ParkingPass(ParkingPass p){
	super(p.getCode(), p.price);
	this.discountCode = p.getDiscountCode();
  }
  
  public double getPrice(){
	  return this.price;
  }
  
  public void discount(String code){
    this.discountCode = code;
  }

  public String getDiscountCode(){
    return this.discountCode;
  }

}
