import java.util.*;

class HuffmanCoder {
  HashMap<Character, String> encoder;
  HashMap<String, Character> decoder;

  private class Node implements Comparable<Node> {
    Character data;
    int cost; // frequency
    Node left;
    Node right;

    public Node(Character data, int cost) {
      this.data = data;
      this.cost = cost;
      this.left = null;
      this.right = null;
    }

    @Override
    public int compareTo(Node other) {
      return this.cost - other.cost;
    }
  }

  public HuffmanCoder(String feeder) {
    if (feeder == null || feeder.isEmpty()) {
      throw new IllegalArgumentException("Input string cannot be empty");
    }

    // Step 1: count frequency of each character
    HashMap<Character, Integer> fmap = new HashMap<>();
    for (int i = 0; i < feeder.length(); i++) {
      char cc = feeder.charAt(i);
      fmap.put(cc, fmap.getOrDefault(cc, 0) + 1);
    }

    // Step 2: put every character in a min-heap ordered by frequency
    PriorityQueue<Node> minHeap = new PriorityQueue<>();
    for (Map.Entry<Character, Integer> entry : fmap.entrySet()) {
      minHeap.add(new Node(entry.getKey(), entry.getValue()));
    }

    // Step 3 (greedy): repeatedly merge the two least frequent nodes
    while (minHeap.size() != 1) {
      Node first = minHeap.poll();
      Node second = minHeap.poll();

      Node newNode = new Node('\0', first.cost + second.cost);
      newNode.left = first;
      newNode.right = second;

      minHeap.add(newNode);
    }

    Node ft = minHeap.poll();

    this.encoder = new HashMap<>();
    this.decoder = new HashMap<>();

    // Edge case: only one distinct character -> give it code "0"
    if (ft.left == null && ft.right == null) {
      encoder.put(ft.data, "0");
      decoder.put("0", ft.data);
    } else {
      this.initEncoderDecoder(ft, "");
    }
  }

  // Step 4: walk the tree, left = 0, right = 1
  private void initEncoderDecoder(Node node, String osf) {
    if (node == null) {
      return;
    }
    if (node.left == null && node.right == null) {
      this.encoder.put(node.data, osf);
      this.decoder.put(osf, node.data);
      return;
    }
    initEncoderDecoder(node.left, osf + "0");
    initEncoderDecoder(node.right, osf + "1");
  }

  public String encode(String source) {
    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < source.length(); i++) {
      ans.append(encoder.get(source.charAt(i)));
    }
    return ans.toString();
  }

  public String decode(String codedString) {
    StringBuilder key = new StringBuilder();
    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < codedString.length(); i++) {
      key.append(codedString.charAt(i));
      if (decoder.containsKey(key.toString())) {
        ans.append(decoder.get(key.toString()));
        key.setLength(0);
      }
    }
    return ans.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter string: ");
    String input = sc.nextLine();
    sc.close();

    HuffmanCoder hc = new HuffmanCoder(input);

    System.out.println("\nCharacter : Code");
    for (Map.Entry<Character, String> e : hc.encoder.entrySet()) {
      System.out.println("   '" + e.getKey() + "'    : " + e.getValue());
    }

    String encoded = hc.encode(input);
    String decoded = hc.decode(encoded);

    System.out.println("\nEncoded : " + encoded);
    System.out.println("Decoded : " + decoded);

    int originalBits = input.length() * 8;
    int encodedBits = encoded.length();
    System.out.println("\nOriginal size : " + originalBits + " bits");
    System.out.println("Encoded size  : " + encodedBits + " bits");
  }
}