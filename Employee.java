public class Employee extends People{
  
  private String email;
  
  public Employee(String code, String name, Address address, String email){
    this.email = email;
    super(code, name, address);
  }
  
  public String getEmail(){
    return this.email;
  }
}
