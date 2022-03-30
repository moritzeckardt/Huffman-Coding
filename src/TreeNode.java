import java.util.Map;

public interface TreeNode {

	public int symbolCount();

	public void getSymbolCodes(String prefix, Map<Character, String> symbolCodes);

}
