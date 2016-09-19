public ParkingPass extends Service{

  private boolean discounted;


  public ParkingPass(boolean discounted){
    this.discounted = discounted;
  }
  
  public void discount(){
    this.discounted = True;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
