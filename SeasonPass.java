public SeasonPass extends Ticket{

  private DateTime startDate;
  private DateTime endDate;
  private DateTime effectiveDate;


  public SeasonPass(String code, double price, DateTime startDate,DateTime endDate,DateTime effectiveDate){
    this.startDate = startDate;
    this.endDate = endDate;
    this.effectiveDate = effectiveDate;
    super(code, price);
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
