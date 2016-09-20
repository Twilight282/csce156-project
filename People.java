public class People{

  protected Address address;
  protected String code;

  public People(String code, Address address){
    this.address = address;
    this.code = code;
  }


  public Address getAddress(){
    return this.address;
  }
  
  public String getCode(){
    return this.code;
  }

}
