public class General extends Customer{

  private String contact;

  public General(String code, String name, Address address, String contact){
    this.contact = contact;
    super(code, name, address);
  }

  public String getContact(){
    return this.contact;
  }

}
