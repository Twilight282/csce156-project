public People{

  private String name;
  private Address address;
  private String code;

  public People(String name,Address address,String code){
    this.name = name;
    this.address = address;
    this.code;
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
