public class Link <E>{
	// ---------------------- fields ---------------------- 
	private E data;
	private Link<E> next;
	private Link<E> par;
	private Link<E> prev;
	
	// ---------------------- constructors ----------------------
	public Link(E data, Link<E> next) {
		this.data = data;
		this.next = next;
		this.par = null;
	}
	
	public Link(E data) {
		this(data, null);
	}

	// ---------------------- Methods ----------------------
	public Link<E> getNext() { 
		return next;
	}
	
	public void setNext(Link<E> next){
		this.next = next;
	}
	
	public E getData() {
	    return data;
	}
	
	public E setData(E data) {
	    E tmp = this.data;
	    this.data = data;
		return tmp;
	}

	public void setPar(Link<E> par) {
		this.par = par;
	}

	public Link<E> getPar() {
		return par;
	}

	public Link<E> getPrev() {
		return prev;
	}

	public void setPrev(Link<E> prev) {
		this.prev = prev;
	}

	public String toString() {
	    return data.toString();
	}

}