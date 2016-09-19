public class Employee extends People{
  
  private String email;
  
  public Employee(String code, String name, Address address, String email){
    super(code, name, address);
    this.email = email;
  }
  
  public String getEmail(){
    return this.email;
  }
}
