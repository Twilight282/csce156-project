public class Refreshment{

  private boolean discounted;

  public Refreshment(String code, double price, boolean discounted){
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
