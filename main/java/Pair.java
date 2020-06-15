package main.java;




public class Pair<T> implements Comparable<Pair<T>>{
	private String k;
	private T v;
	
	public Pair(String k,T v) {
		this.k=k;
		this.v=v;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public T getV() {
		return v;
	}

	public void setV(T v) {
		this.v = v;
	}

	@Override
	public int compareTo(Pair<T> o) {
		// TODO Auto-generated method stub
		return this.k.compareTo((String) o.getK());
	}

	

}
