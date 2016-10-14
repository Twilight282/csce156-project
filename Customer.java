public class Customer extends People{

  protected String name;
  protected String contact;
  
  //our customer class. Creates customers

  public Customer(String code, String name, Address address, String contact){
    super(code, address);
    this.name = name;
    this.contact = contact;
  }
  

  public String getName(){
    return this.name;
  }
  
  public String getContact(){
	    return this.contact;
	  }
}
