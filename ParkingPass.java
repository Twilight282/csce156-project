public class ParkingPass extends Service{

  private boolean discounted;


  public ParkingPass(String code, double price, boolean discounted){
    super(code, price);
    this.discounted = discounted;
  }
  
  public void discount(){
    this.discounted = True;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
