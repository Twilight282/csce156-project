public class Person extends People{
  
  private String[] emails;
  private Name name;
  
  //our employee class. Creates employees.
  
  public Person(String code, Name name, Address address, String[] emails){
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
