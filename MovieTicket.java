public class MovieTicket extends Ticket{

  private String dateTime;
  private String name;
  private String screen;
  private Address address;

  //our movie ticket class. creates movie tickets

  public MovieTicket(String code, double price, String dateTime, String name, String screen, Address address){
    super(code, price);
    this.dateTime = dateTime;
    this.name = name;
    this.screen = screen;
    this.address = address;
  }
  
  public MovieTicket(MovieTicket m){
	    super(m.code, m.price);
	    this.dateTime = m.dateTime;
	    this.name = m.name;
	    this.screen = m.screen;
	    this.address = m.address;
  }

  public String getDateTime(){
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
