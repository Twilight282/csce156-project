public People{

  private String name;
  private Address address;
  private String code;

  public People(String code, String name, Address address){
    this.name = name;
    this.address = address;
    this.code = code;
  }

  public String getName(){
    return this.name;
  }

  public Address getAddress(){
    return this.address;
  }
  
  public String getCode(){
    return this.code;
  }

}
