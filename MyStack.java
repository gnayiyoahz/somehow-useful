/* COMS 3134 Homework 2
 * @ Yiyang Zhao (yz3504)
 * */

class MyStack<T> implements MyStackInterface<T>{
	
	private T[] stack;
	private int capacity = 10;
	private int top = 0;
	
	@SuppressWarnings("unchecked")
	public MyStack(){
		stack =(T[]) new Object[capacity];	
	}
	
	public void push(T x){
		if (top == capacity){
			resize();
		}
		stack[top] = x;
		top++;
	}
	
	public T pop(){
		if (top == 0){
//			System.out.println("Don't pop me when I'm empty.");
			return null;
		} else {
			top--;
			return stack[top];
		}
	}
	
	public T peek(){
		if (top == 0){
//			System.out.println("Don't peek me when I'm naked.");
			return null;
		} else {
			return stack[top-1];
		}	
	}
	
	public boolean isEmpty(){
		return top == 0;
	}
	
	public int size(){
		return top;
	}
	
	@SuppressWarnings("unchecked")
	public void clear(){
		capacity = 10;
		top = 0;
		stack =(T[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	// expand the capacity
	public void resize(){
		capacity = capacity*2;
		T[] newStack =(T[]) new Object[capacity];
		for (int i = 0; i < top; i++){
			newStack[i] = stack[i];
		}
		stack = newStack;
	}
	
	// this part is for testing purposes
	public String toString(){
		String s = new String();
		for (int i = 0; i<top; i++){
			if (top != 0){
				s += (String) stack[top-i-1];	
			}
		}
		return s;
	}
	
}