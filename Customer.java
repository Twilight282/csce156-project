public class Customer extends People{

  protected String name;

  public Customer(String code, String name, Address address){
    super(code, address);
    this.name = name;
  }
  
  public String getName(){
    return this.name;
  }
  
}
