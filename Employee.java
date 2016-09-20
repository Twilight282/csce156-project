public class Employee extends People{
  
  private String[] emails;
  
  public Employee(String code, String name, Address address, String[] emails){
    super(code, name, address);
    this.emails = emails;
  }
  
  public String[] getEmails(){
    return this.emails;
  }
}
