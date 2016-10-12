public class ParkingPass extends Service{

  private boolean discounted;

  //parking passes for our services
  
  public ParkingPass(String code, double price){
    super(code, price);
    this.discounted = false;
  }
  
  public ParkingPass(ParkingPass p){
	super(p.getCode(), p.price);
	this.discounted = false;
  }
  
  public double getPrice(){
	  if (this.discounted == true){
		  return this.price*this.num;
	  }
	  else return 0;
  }
  
  public void discount(){
    this.discounted = true;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
