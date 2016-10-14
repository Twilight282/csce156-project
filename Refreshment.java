public class Refreshment extends Service{

  private boolean discounted;
  private String name;

  //our refreshment class.
  
  public Refreshment(String code, double price, String name){
    super(code, price);
    this.discounted = false;
    this.name = name;
  }
  
  public Refreshment(Refreshment r){
	super(r.getCode(), r.price);
	this.discounted = false;
	this.name = r.getName();
  }
  
  public void discount(){
    this.discounted = true;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }
  
  public String getName(){
    return this.name;
  }

  public double getPrice(){
	  return this.price;
  }
}
