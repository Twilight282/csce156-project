public class Refreshment extends Service{

  private boolean discounted;
  private String name;

  public Refreshment(String code, double price, String name){
    super(code, price);
    this.discounted = false;
    this.name = name;
  }
  
  public void discount(){
    this.discounted = true;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }
  
  public boolean getName(){
    return this.name;
  }

}
