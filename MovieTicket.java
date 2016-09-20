public class MovieTicket extends Ticket{

  private DateTime dateTime;
  private String name;
  private String screen;
  private Address address;


  public MovieTicket(String code, double price, DateTime dateTime, String name, String screen, Address address){
    super(code, price);
    this.dateTime = dateTime;
    this.name = name;
    this.screen = screen;
    this.address = address;
  }

  public DateTime getDateTime(){
    return this.dateTime;
  }

  public String getName(){
    return this.name;
  }

  public String getScreen(){
    return this.screen;
  }
  
  public Address getAddress(){
    return this.address;
  }

}
