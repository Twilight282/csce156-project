
public class LinkedList<T> {
	
	private LinkedNode<T> start;
	private int length;

	public LinkedList(){
		start = null;
		length = 0;
	}
	
	public void add(T item){
		LinkedNode<T> prev = null;
		LinkedNode<T> newest = new LinkedNode<T>(item);
		if (this.length == 0){
			this.start = newest;
			length++;
			return;
		}
		LinkedNode<T> spot = start;
		if (start.getItem() instanceof Comparable && ((Comparable) spot.getItem()).compareTo(item) < 0){ //if item is larger than start
			newest.setNext(start);
			start = newest;
			length++;
			return;
		}
		else{
			while (true){
				if (spot == null){ //if we have reached the end of the list, insert
					prev.setNext(newest);
					length++;
					return;
				}
				else if (spot.getItem() instanceof Comparable && ((Comparable) spot.getItem()).compareTo(item) > 0){ //if the current is larger than item, cont
					prev = spot;
					spot = spot.getNext();
				}
				else if (spot.getItem() instanceof Comparable && ((Comparable) spot.getItem()).compareTo(item) < 0){ //if the current is smaller than item
					prev.setNext(newest);
					newest.setNext(spot);
					length++;
					return;
				}
				else{ 						//if it can't be compared or is equal, insert.
					prev.setNext(newest);   //functionally the same as previous else if, but
					newest.setNext(spot);   //separated for readability and potential future changes.
					length++;
					return;
				}
			}
		}
	}
	
	public boolean remove(int index){
		if (index == 0){
			start = start.getNext();
			length--;
			return true;
		}
		LinkedNode<T> spot = start.getNext();
		LinkedNode<T> prev = start;
		for (int i = 1; i < length; i++){
			if (spot == null) break;
			if (i == index){
				prev.setNext(spot.getNext());
				length--;
				return true;
			}
			prev = spot;
			spot = spot.getNext();
		}
			
		return false;
	}
	
	public int getLength(){
		return length;
	}
	
	public String toString(){
		LinkedNode<T> spot = start;
		String s = "List size " + this.getLength() + ": ";
		while (spot != null){
			s += spot.getItem().toString();
			spot = spot.getNext();
		}
		return s;
	}
}
