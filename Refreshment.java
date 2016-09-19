public Refreshment{

  private boolean discounted;

  public Refreshment(boolean discounted = False){
    this.discounted = discounted;
  }
  
  public void discount(){
    this.discounted = True;
  }

  public boolean getDiscounted(){
    return this.discounted;
  }

}
