
public class LinkedNode<T> {
	
	private T item;
	private LinkedNode<T> next;
	
	public LinkedNode(T item){
		this.item = item;
		this.next = null;
	}
	
	public LinkedNode(T item, LinkedNode<T> next){
		this.item = item;
		this.next = next;
	}
	
	public void setItem(T item){
		this.item = item;
	}
	
	public T getItem(){
		return this.item;
	}
	
	public void setNext(LinkedNode<T> n){
		this.next = n;
	}
	
	public LinkedNode<T> getNext(){
		return this.next;
	}
}
