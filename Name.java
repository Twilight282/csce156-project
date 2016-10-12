public class Name{
  
  private String first;
  private String last;
  
  //creates names for our persons.
  
  public Name(String first, String last){
    this.first = first;
    this.last = last;
  }

  public String getFirst(){
    return this.first;
  }
  
  public String getLast(){
    return this.last;
  }
  
}
