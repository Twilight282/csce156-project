public class MovieTicket extends Ticket{

  private DateTime dateTime;
  private String name;
  private int screen;


  public MovieTicket(String code, double price, DateTime dateTime,String name,int screen){
    this.dateTime = dateTime;
    this.name = name;
    this.screen = screen;
    super(code, price);
  }

  public DateTime getDateTime(){
    return this.dateTime;
  }

  public String getName(){
    return this.name;
  }

  public int getScreen(){
    return this.screen;
  }

}
