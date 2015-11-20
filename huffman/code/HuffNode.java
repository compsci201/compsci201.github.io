
public class HuffNode implements Comparable<HuffNode> {
	
	private int value, weight;
	private HuffNode left, right;
	
	public HuffNode(int value, int weight) {
		this(value, weight, null, null);
	}
	
	public HuffNode(int value, int weight, HuffNode left, HuffNode right) {
		this.value = value;
		this.weight = weight;
		this.left = left;
		this.right= right;
	}
	
	public int compareTo(HuffNode other) {
		return weight - other.weight;
	}
	
	public int value() {
		return value;
	}
	
	public int weight() {
		return weight;
	}
	
	public HuffNode left() {
		return left;
	}
	
	public HuffNode right() {
		return right;
	}
	
	public String toString() {
		return value + "";
	}
}