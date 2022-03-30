import java.util.Map;

public class LeafTreeNode implements TreeNode {
	private char symbol;
	private int symbolCount;
	
	public LeafTreeNode(char symbol, int symbolCount) {
		this.symbol = symbol;
		this.symbolCount = symbolCount;
	}

	public int symbolCount() {
		return symbolCount;
	}

	public void getSymbolCodes(String prefix, Map<Character, String> symbolCodes) {
		symbolCodes.put(symbol, prefix);
	}
}
