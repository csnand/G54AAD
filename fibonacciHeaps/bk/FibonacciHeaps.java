
public class FibonacciHeaps {
	int num;		//number of nodes in the heap
	Node min;		//the minimum node
	
	class Node{
		int key;		//the value stored in the node
		int degree;		//the number of children 
		Node parent;
		Node child;
		Node left;
		Node right; 		//pointers left and right are used to form a circular double linked list.
		
		public Node (int value) {		//constructor for Fibonacci heap node
			this.key = value;
			this.left = this;
			this.right = this;
		}
	}
	
	
	public void emptyhH() {		//make heap empty
		min=null;
		num=0;
	}
	
	public boolean isEmptyH() {		//check if the heap is empty 
		return num == 0;
	} 
	
	public void insertH(int value) {		//add one element to an existing heap
		Node temp = new Node (value);
		if(num == 0)
			min =temp;
		else {
		addNode(temp,min);
		if(temp.key<min.key)
			min = temp;
		}
		num++;
	}
	
	public void minimumH() {		//reading the minimum element from a heap
		System.out.println("minimum: "+min.key);
	}
	
	public int extractH() {		//delete and return the minimum element of a heap
		if(min ==null) return 0;
		int minimum = min.key;
		
		while (min.child != null) {
			removeNode(min.child);
			Node child =min.child; 
			if(min.child.right==min.child) 
				min.child = null;
			else 
				min.child = min.child.right;
			addNode(child,min);	//concatenate the sub-heap of the extracted element with the remaining root wheel 
			child.parent =null;
		}
		removeNode(min);		//remove the root containing the minimum element
		
		if(min.right == min) min =null;
		else{
			min=min.right; 
			consolidate();		//to reorganise the structure of the heap
			}

		num--;
		return minimum;
	}
	

	
	public void consolidate() {
		int maxDegree = (int)Math.floor(Math.log(num)/Math.log(2));  
		Node[] cons =new Node[maxDegree+1];		//create an array of sub-heap with different degrees
		for(int i = 0; i<=maxDegree; i++)
			cons[i]=null;
				
		while (min !=null) {
			Node x = extractMinTree();
			int d =x.degree;
			while(cons[d]!=null) {		//If degrees are same then merge the trees by using link(), until there are no two trees of the same degree.
				if(cons[d].key<x.key) {
					Node temp=x;
					x=cons[d];
					cons[d]=temp;				
				}
				
				link(cons[d],x);  
				cons[d]=null;
				d++;  
			}
			cons[d]=x;
		}
		//min = null;
		
		for(int i=0 ; i<=maxDegree; i++) {
		 if (cons[i] !=null) {  
			if(min == null)
				min = cons[i];
			else {
				addNode(cons[i],min);
				if(cons[i].key < min.key)
					min = cons[i];
			}
					
			}
		}
		
	}
	
	public void link(Node node1,Node node2) {		//insert the larger one "node1" as a child of the smaller "node2"
		removeNode(node1);
		if(node2.child == null)
			node2.child =node1;
		else addNode(node1,node2.child);
		node1.parent = node2;
		node2.degree++;
	}
	public void addNode(Node node,Node min) {
		node.left=min.left;
		node.right=min;
		min.left.right=node;
		min.left=node;
	}
	public void removeNode(Node node) {  
		node.left.right=node.right;
		node.right.left=node.left;
	}
	
	public Node extractMinTree() {
		Node p =min;
		if (p == p.right)
			min = null;
		else {
			removeNode(p);
			min = p.right;
		}
		p.left=p.right=p;
		return p;
	}

}
