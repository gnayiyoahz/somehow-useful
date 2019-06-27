/* COMS 3134 Homework 3
 * @ Yiyang Zhao (yz3504)
 * */
import java.util.*;
import java.lang.*;

public class ExpressionTree implements ExpressionTreeInterface{
	
	ArrayList<ExpressionNode> allNodes;
	LinkedList<ExpressionNode> tokenStack;
	ExpressionNode root;
	int l;
	
	//constructor
	public ExpressionTree(String str){
		String[] exp = str.split("\\s+");//postfix expression
		l = exp.length;
		allNodes = new ArrayList<ExpressionNode>();
		tokenStack = new LinkedList<ExpressionNode>();
		
		for (int i = 0; i < l; i++){
			allNodes.add(new ExpressionNode(exp[i].toString(), null, null));
		}
		root = allNodes.get(l-1);
		plantTree();
		System.out.println("root is: "+root.toString());
	}
	
	
	//build the expression tree
	public void plantTree(){
		for (int i = 0; i < l ; i++){
			ExpressionNode currentNode = allNodes.get(i);

			if (currentNode.isOperand()){
				tokenStack.push(currentNode);
			} else if (currentNode.isOperator() && currentNode.token.length() == 1){
				currentNode.right = tokenStack.pop();
				currentNode.left = tokenStack.pop();
				tokenStack.push(currentNode);
			} else {
				throw new IllegalArgumentException("Invalid expression.");
				//neither operand nor operator
			}
		}
		//tokenStack should only have 1 node (the root, an operator)
		if (tokenStack.size() != 1)
			throw new IllegalArgumentException("Invalid expression.");
		int val = eval();
	}
	
	
	//stack helper methods
	public ExpressionNode pop(){
		if (tokenStack.size() == 0){
			throw new EmptyStackException();
		}
		else 
			return tokenStack.remove(0);
	}
	
	public void push(ExpressionNode node){
		tokenStack.add(0,node);
	}
	
	
	//nested ExpressionNode class 
	public static class ExpressionNode{
		String token;
		ExpressionNode left;
		ExpressionNode right;
		
		ExpressionNode(String theToken){
			this(theToken, null, null);	
		}
		ExpressionNode(String theToken, ExpressionNode lt, ExpressionNode rt){
			token = theToken;
			left = lt;
			right = rt;
		}
		public boolean isOperator(){
			String operators = "+-*/";
			return (operators.contains(token));
		}
		public boolean isOperand(){
			return token.matches("-?\\d+(\\.\\d+)?");
		}
		public String toString(){
			return "["+token+" (L: "+left+" R: "+right+")]";
		} 
	}
	
	
	//methods
	public int eval(){
		return evalExp(root);
	}
	
	public String postfix(){
		return printPost(root);
	}
	
	
	public String prefix(){
		return printPre(root);
	}

	public String infix(){
		return printIn(root);
	}
	
	
	//recursive helper methods
	private int evalExp(ExpressionNode node){
		if (node.left == null && node.right == null){
			if (node.isOperand())
				return (int)Integer.parseInt(node.token);
			else 
				throw new IllegalArgumentException("Invalid expression.");
		} else if (node.left != null && node.right != null) {
			return (int)opr(node.token, evalExp(node.left), evalExp(node.right));
		} else {
			throw new NullPointerException("Invalid expression.");
		}
	}
	
	private Integer opr(String operator, int l, int r){
		if (operator.equals("+")){
			return l+r;
		} else if (operator.equals("-")){
			return l-r;
		} else if (operator.equals("*")){
			return l*r;
		} else if (operator.equals("/")){
			return l/r;
		} else {
			return null;
		}
	}
	
	private String printPost(ExpressionNode node){
		if (node == null){
			return "";
		} else if (node.left == null && node.right == null){
			return node.token;
		} else {
			return printPost(node.left)+" "+printPost(node.right)+" "+node.token;
		}
	}
	
	public String printPre(ExpressionNode node){
		if (node == null){
			return "";
		} else if (node.left == null && node.right == null){
			return node.token;
		} else {
			return node.token+" "+printPre(node.left)+" "+printPre(node.right);
		}
	}
	
	public String printIn(ExpressionNode node){
		if (node == null){
			return "";
		} else if (node.left == null && node.right == null){
			return node.token;
		} else {
			return "("+printIn(node.left)+" "+node.token+" "+printIn(node.right)+")";
		}
	}	
}
