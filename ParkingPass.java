public ParkingPass extends Service{

  private boolean discounted;


  public ParkingPass(boolean discounted){
    this.discounted = discounted;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
