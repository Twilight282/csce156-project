public class SeasonPass extends Ticket{

  private String startDate;
  private String endDate;
  private DateTime effectiveDate;
  private String name;


  public SeasonPass(String code, double price, String startDate, String endDate, String name){
    super(code, price);
    this.startDate = startDate;
    this.endDate = endDate;
    this.effectiveDate = startDate;
    this.name = name;
  }
  
  public void setEffectiveDate(DateTime date){
    this.effectiveDate = date;
  }

  public DateTime getStartDate(){
    return this.startDate;
  }

  public DateTime getEndDate(){
    return this.endDate;
  }

  public DateTime getEffectiveDate(){
    return this.effectiveDate;
  }

}
