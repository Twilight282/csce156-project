public Product{

  private double price;
  private String code;

  public Product(double price, String code){
    this.price = price;
    this.code = code;
  }

  public double getPrice(){
    return this.price;
  }
  
  public String getCode(){
    return this.code;
  }

}
