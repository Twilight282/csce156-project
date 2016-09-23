public class Employee extends People{
  
  private String[] emails;
  private String name;
  
  public Employee(String code, String name, Address address, String[] emails){
    super(code, address);
    this.emails = emails;
    this.name = name;
  }
  
  public String[] getEmails(){
    return this.emails;
  }
  
  public Name getName(){
	  return this.name;
  }
}
