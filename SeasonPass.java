public class SeasonPass extends Ticket{

  private String startDate;
  private String endDate;
  private String effectiveDate;
  private String name;

  //our season pass class.

  public SeasonPass(String code, double price, String startDate, String endDate, String name){
    super(code, price);
    this.startDate = startDate;
    this.endDate = endDate;
    this.effectiveDate = startDate;
    this.name = name;
  }
  
  public void setEffectiveDate(String date){
    this.effectiveDate = date;
  }

  public String getStartDate(){
    return this.startDate;
  }

  public String getEndDate(){
    return this.endDate;
  }

  public String getEffectiveDate(){
    return this.effectiveDate;
  }
  
  public String getName(){
    return this.name;
  }

}
