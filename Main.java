import java.util.Stack;

/**
 * You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a
 * function that adds the two numbers and returns the sum as a linked list.
 *
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 *
 * For this challenge, we'll assume the following singly linked list:
 *
 * public class Node {
 *   public Node next;
 *   public int data;
 *
 *   public Node(int data) {
 *     this.data = data;
 *   }
 *
 *   @Override
 *   public String toString() {
 *     StringBuilder sb = new StringBuilder();
 *
 *     Node nextNode = next;
 *
 *     sb.append(data);
 *
 *     while (nextNode != null) {
 *       sb.append("->");
 *
 *       sb.append(nextNode.data);
 *
 *       nextNode = nextNode.next;
 *     }
 *
 *     return sb.toString();
 *   }
 * }
 */
public class Main {

  /**
   * For each pair that has no null elements - find the sum of the values and if it's >= 10 -> carry the 1 forward and put 0 in the stack,
   * otherwise put the value as it is, then proceed to the next pair and keep in mind to add the 1 and repeat the same process.
   * If only 1 linked list has remaining nodes and the other one doesn't -> traverse the rest nodes and put the values as they are.
   * Then we pop the elements from the stack until it's empty and put them in a linked list in the exact same order.
   *
   * <info>With that approach we'll also include 0s if the result is 002 or similar, which could be removed very easily, if required.</info>
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   */
  public static Node sumV1(Node a, Node b) {
    if (a == null || b == null) {
      return null;
    }

    Stack<Integer> stack = new Stack<>();
    int carry = 0;

    while (a != null && b != null) {
      int value = a.data + b.data + carry;

      if (value >= 10) {
        carry = 1;
        value = value % 10;
      } else {
        carry = 0;
      }

      stack.push(value);

      a = a.next;
      b = b.next;
    }

    while (a != null) {
      stack.push(carry > 0 ? a.data + 1 : a.data);

      a = a.next;
    }

    while (b != null) {
      stack.push(carry > 0 ? b.data + 1 : b.data);

      b = b.next;
    }

    if (carry > 0) {
      stack.push(1);
    }

    Node head = null;

    while (!stack.empty()) {
      Node node = new Node(stack.pop());

      if (head != null) {
        node.next = head;
      }

      head = node;
    }

    return head;
  }

  /**
   * In this approach we're making use of number places to come up with more optimal solution within the given constraints. It uses a modified version of <link>ReversedNumber</link>
   * approach to construct ready to calculate numbers. Once the sum is found -> it's all about creating a linked list while continuously getting the last digit, until none are left.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   */
  public static Node sumV2(Node a, Node b) {
    if (a == null || b == null) {
      return null;
    }

    int left = 0;
    int right = 0;
    int places = 1;

    while (a != null) {
      left += (places * a.data);
      places *= 10;
      a = a.next;
    }

    places = 1;

    while (b != null) {
      right += (places * b.data);
      places *= 10;
      b = b.next;
    }

    int sum = left + right;

    Node head = null;
    Node tail = null;

    while (sum != 0) {
      Node node = new Node(sum % 10);

      if (head == null) {
        head = tail = node;
      } else {
        tail.next = node;
        tail = node;
      }

      sum /= 10;
    }

    return head;
  }

  /**
   * If we suppose that the digits are stored in forward order, we could use an approach similar to <link>ReverseNumber</link>.
   * Initialize 2 int variables: left & right, then traverse through both lists simultaneously and convert to int values.
   * Find their sum and create a linked list in the exact same order.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   */
  public static Node sumV3(Node a, Node b) {
    if (a == null || b == null) {
      return null;
    }

    int left = 0;
    int right = 0;

    while (a != null) {
      left = left * 10 + a.data;
      a = a.next;
    }

    while (b != null) {
      right = right * 10 + b.data;
      b = b.next;
    }

    int sum = left + right;

    Node head = null;

    while (sum != 0) {
      // For each digit -> replace the next with the current value
      // to preserve the forward order
      Node node = new Node(sum % 10);
      node.next = head;
      head = node;

      sum /= 10;
    }

    return head;
  }
}
