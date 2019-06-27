/* COMS 3134 Homework 1
 * @ Yiyang Zhao (yz3504)
 * */

class MyStackTester{
    
    public static void main(String args[]) {
		
        MyStack<String> s1 = new MyStack<String>();

		System.out.println("empty? "+s1.isEmpty());

		s1.push("hello ");
		s1.push("world ");		
		s1.push("this ");
		s1.push("is ");
		s1.push("my ");
		s1.push("code ");
		s1.push("for ");
		s1.push("homework 2 ");
		s1.push("programming ");
		s1.push("3134 ");	
		System.out.println("size? "+s1.size());
		s1.push("ok ");			
		System.out.println("size? "+s1.size());
		
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		
		System.out.println("empty? "+s1.isEmpty());
		System.out.println("size? "+s1.size());

		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());
		System.out.println("peek! "+s1.peek());
		System.out.println("pop! "+s1.pop());		
		
		System.out.println("empty? "+s1.isEmpty());
		System.out.println("size? "+s1.size());

		s1.clear();
		System.out.println("size? "+s1.size());		
		System.out.println("peek! "+s1.peek());	
		
	}
    
}
