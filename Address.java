public class Address{

  private String street;
  private String city;
  private String state;
  private String zip;
  private String country;

  //this will set the address for each entry

  public Address(String street,String city,String state,String zip,String country){
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
  }

  public String getStreet(){
    return this.street;}
  public String getCity(){
    return this.city;}
  public String getState(){
    return this.state;}
  public String getZip(){
    return this.zip;}
  public String getCountry(){
    return this.country;}
  
}
