public class Student extends Customer{

  private String contact;

  //our student class, under customer.
  
  public Student(String code, String name, Address address, String contact){
    super(code, name, address);
    this.contact = contact;
  }

  public String getContact(){
    return this.contact;
  }

}
