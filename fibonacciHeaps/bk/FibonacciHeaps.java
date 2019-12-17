public class FibonacciHeaps {
	private int count;
	private Node head;

	public FibonacciHeaps(){
	    head = null;
	    count = 0;
    }
	public FibonacciHeaps emptyH() {
		return new FibonacciHeaps();
	}

	public boolean isEmptyH() {	return head == null; }

	public void insertH(int value) {
		Node node = new Node (value);
		if(isEmptyH()) {
            head = node;
            count++;
            return;
        }
		head.insert(node);
		if(node.val< head.val)
			head = node;
		count++;
	}

	public int minimumH() { return head.val; }

	public int extractH() {
		if(isEmptyH()) return 0;

		int minimum = head.val;
		while (head.child != null) {
			Node child = head.child;
            head.child.delete();
			if(head.child.right == head.child)
				head.child = null;
			else
				head.child = head.child.right;
			head.insert(child);
			child.parent = null;
		}
		head.delete();
		
		if (head.right == head) {
            head = null;
        } else {
			head = head.right;
			consolidate();
		}
		count--;
		return minimum;
	}

	public void consolidate() {
		Node[] NArray = new Node[ (int)(Math.log(count) / Math.log(2)) + 1];
		while (head != null) {
			Node x = extract();
			int d = x.degree;
			while(NArray[d] != null) {
				if(NArray[d].val < x.val) {
					Node temp = x;
					x = NArray[d];
					NArray[d] = temp;
				}
				x.link(NArray[d]);
				NArray[d] = null;
				d++;  
			}
			NArray[d]=x;
		}

		for(int i = 0 ; i < NArray.length; i++) {
		 	if (NArray[i] == null) continue;

		 	if(head == null) {
				head = NArray[i];
				continue;
			}

		 	head.insert(NArray[i]);
		 	if(NArray[i].val < head.val)
		 		head = NArray[i];
		}
	}

	public Node extractMinTree() {
		Node temp = head;
		if (temp == temp.right) {
			head = null;
			temp.left = temp.right = temp;
			return temp;
		}

		temp.delete();
		head = head.right;
		temp.left = temp.right = temp;
		return temp;
	}

}
