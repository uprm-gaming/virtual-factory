package edu.uprm.gaming.utils;

public class Pair<P, Q> {
	private P first;
	private Q second;
	
	public Pair(P first, Q second){
		this.first = first;
		this.second = second;
	}
        
        public Pair(){

	}

	/**
	 * @return the first
	 */
	public P getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(P first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public Q getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(Q second) {
		this.second = second;
	}
	
	
}
