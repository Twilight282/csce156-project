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
  
  public SeasonPass(SeasonPass s){
	  super(s.getCode(), s.price);
	  this.startDate = s.startDate;
	  this.endDate = s.endDate;
	  this.effectiveDate = s.effectiveDate;
	  this.name = s.name;
  }
  
  public void setEffectiveDate(String date){
    if (date > this.effectiveDate){ //checks to see if new date is partly through season.
      this.effectiveDate = date;
    }
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
