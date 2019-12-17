public class FibonacciHeaps {
	private int count;
	private Node<Integer> head;

	public FibonacciHeaps(){
	    head = null;
	    count = 0;
    }
	public FibonacciHeaps emptyH() {
		return new FibonacciHeaps();
	}

	public boolean isEmptyH() {	return head == null; }

	public void insertH(int value) {
		Node<Integer> node = new Node (value);
		if(isEmptyH()) {
            head = node;
            count++;
            return;
        }
		head.insert(node);
		if(node.getData() < head.getData())
			head = node;
		count++;
	}

	public int minimumH() { return head == null ? 0 : head.getData(); }

	public int extractH() {
		if(isEmptyH()) return 0;

		int min = head.getData();
		while (head.child != null) {
			Node child = head.child;
            head.child.delete();
			if(head.child == head.child.right) head.child = null;
			else head.child = head.child.right;
			head.insert(child);
			child.parent = null;
		}

		head.delete();
		count--;

		if (head == head.right) {
            head = null;
            return min;
		}

		head = head.right;
		consolidate();
		return min;
	}

	public void consolidate() {
		Node<Integer> NArray[] = new Node[ (int)(Math.log(count) / Math.log(2)) + 1];
		while (head != null) {
			Node<Integer> x = extract();
			int d = x.degree;
			for (; NArray[d] != null; d++) {
				if(NArray[d].getData() < x.getData()) {
					Node temp = x;
					x = NArray[d];
					NArray[d] = temp;
				}
				x.link(NArray[d]);
				NArray[d] = null;
			}
			NArray[d] = x;
		}

		for(Node<Integer> n : NArray) {
		 	if (n == null) continue;
		 	if(head == null) {
				head = n;
				continue;
			}
		 	head.insert(n);
		 	if(n.getData() < head.getData())
		 		head = n;
		}
	}

	public Node extract() {
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
