public class General extends Customer{

  private String contact;
  
  //our class for general customers

  public General(String code, String name, Address address, String contact){
    super(code, name, address, contact);
    this.contact = contact;
  }

  public String getContact(){
    return this.contact;
  }

}
