public class ParkingPass extends Service{

  private boolean discounted;

  //parking passes for our services
  
  public ParkingPass(String code, double price){
    super(code, price);
    this.discounted = false;
  }
  
  public void discount(){
    this.discounted = true;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
