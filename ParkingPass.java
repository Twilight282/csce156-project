public class ParkingPass extends Service{

  private boolean discounted;


  public ParkingPass(String code, double price, boolean discounted){
    this.discounted = discounted;
    super(code, price);
  }
  
  public void discount(){
    this.discounted = True;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
