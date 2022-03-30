import java.util.Map;

public class InnerTreeNode implements TreeNode {

	TreeNode left;
	TreeNode right;
	
	public InnerTreeNode(TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
	}
	
	public int symbolCount() {
		return left.symbolCount() + right.symbolCount();
	}

	public void getSymbolCodes(String prefix, Map<Character, String> symbolCodes) {
		left.getSymbolCodes(prefix + "0", symbolCodes);
		right.getSymbolCodes(prefix + "1", symbolCodes);
	}
}
