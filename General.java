public class General extends Customer{

  private String contact;

  public General(String code, String name, Address address, String contact){
    super(code, name, address);
    this.contact = contact;
  }

  public String getContact(){
    return this.contact;
  }

}
