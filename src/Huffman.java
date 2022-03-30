// Moritz Eckardt 22878595

import java.util.HashMap;
import java.util.Map;

public class Huffman {

	public int[] characterHistogram(String s) {
		// Only ASCII
		int[] histogram = new int[256];

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < 256) {
				histogram[c]++;
			}
		}

		return histogram;
	}

	public void printHuffmanCoding(Map<Character, String> map) {
		for (Character c : map.keySet()) {
			System.out.println("'" + c + "': " + map.get(c));
		}
	}

	/**
	 * Compute the Huffman coding map for s
	 *
	 * @param s String to encode
	 * @return Huffman coding map
	 */
	public Map<Character, String> huffmanCode(String s) {
		// TODO 3.
		int[] histogram = characterHistogram(s);

		// Copy histogram into a priority queue with inverse priority
		PQInterface<TreeNode> priorityQueue = new PriorityQueue<>();
		for (int i = 0; i < histogram.length; i++) {
			if (histogram[i] > 0) {
				TreeNode node = new LeafTreeNode((char) i, histogram[i]);
				priorityQueue.add(node, s.length() - histogram[i]);
			}
		}

		// Return an empty map if the queue is empty.
		if (priorityQueue.isEmpty()) {
			return new HashMap<>();
		}

		// Soll, falls PriorityQueue nur ein Element enth?lt, z.B der Buchsttabe "a" so -> 'a': oder so -> 'a': 0 dargestellt werden?
		// Hierbei war ich mir unsicher. Deswegen habe ich diese extra Abfrage miteingebaut. Ich hoffe, dass das in Ordnung ist.
		if (priorityQueue.size() == 1) {
			Map<Character, String> output = new HashMap<>();
			priorityQueue.remove().getSymbolCodes("0", output);
			return output;
		}

		// Put characters into a tree (left < right) sorted by lowest frequency (= highest priority)
		TreeNode root = priorityQueue.remove();
		int size = priorityQueue.size();
		for (int i = 0; i < size; i++) {
			TreeNode oldNode = root;
			TreeNode newNode = priorityQueue.remove();

			if (oldNode.symbolCount() < newNode.symbolCount()) {
				root = new InnerTreeNode(oldNode, newNode);
			} else {
				root = new InnerTreeNode(newNode, oldNode);
			}
		}

		// Read result into a map and return it
		Map<Character, String> output = new HashMap<>();
		root.getSymbolCodes("", output);
		return output;
	}

	/**
	 * @param s
	 *            String to encode
	 * @param map
	 *            Huffman coding map
	 * @return the length of the Huffman coding of s
	 */
	public long lengthHuffmanCoding(String s, Map<Character, String> map) {
		// TODO 4.
		StringBuilder output = new StringBuilder();
		char[] arr = s.toCharArray();

		for (Character c : arr) {
			if (map.containsKey(c)) {
				output.append(map.get(c));
			}
		}

		return output.length();
	}

	/**
	 * @param s
	 *            String to encode
	 * @return the length of the 8-bit coding of s
	 */
	public long length8BitCoding(String s) {
		// TODO 4.
		return s.length() * 8L;
	}

	/**
	 * Compute the compression ratio we can achieve for s given the Huffman
	 * encoding map as compared to 8-bit coding of the symbols.
	 *
	 * @param s
	 *            String to encode
	 * @param map
	 *            Huffman coding map
	 * @return compression ratio
	 */
	public float computeCompressionRatio(String s, Map<Character, String> map) {
		// TODO 4.
		return 1 - ((float) lengthHuffmanCoding(s, map) / length8BitCoding(s));
	}

	public static void main(String[] args) {
		Huffman h = new Huffman();
		String s = "adaaaaaaadwwwwwwdsddddddddassstthzhzjjjjjjjj";
		Map<Character, String> map = h.huffmanCode(s);
		h.printHuffmanCoding(map);
		float cr = h.computeCompressionRatio(s, map);

		System.out.println("Compression ratio: " + (cr * 100) + "%");
	}
}
