/* COMS 3134 Homework 3
 * @ Yiyang Zhao (yz3504)
 * */
public class ETTester{
	public static void main(String[] args){
		ExpressionTree myExp = new ExpressionTree("1 2 + 4 5 * 6 - 9 3 / + *");
		System.out.println("postfix: "+myExp.postfix());
		System.out.println("prefix: "+myExp.prefix());
		System.out.println("infix: "+myExp.infix());
		System.out.println("eval: "+myExp.eval());
	}
}