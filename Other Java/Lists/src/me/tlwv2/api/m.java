package me.tlwv2.api;

public abstract class m<E> {

	public m() {
		// TODO Auto-generated constructor stub
	}
	
	public E run(Object... params){
		return method(params);
	}
	
	public abstract E method(Object... params);
}
