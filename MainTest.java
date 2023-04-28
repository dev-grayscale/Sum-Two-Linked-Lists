import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void sumV1() {
    Assertions.assertNull(Main.sumV1(null, build(1,2)));
    Assertions.assertNull(Main.sumV1(null, null));
    Assertions.assertNull(Main.sumV1(build(1,2), null));
    Assertions.assertEquals("2->2->2", Main.sumV1(build(1,1,1), build(1,1,1)).toString());
    Assertions.assertEquals("2", Main.sumV1(build(1), build(1)).toString());
    Assertions.assertEquals("0->1", Main.sumV1(build(9), build(1)).toString());
    Assertions.assertEquals("2->0->0", Main.sumV1(build(1,0,0), build(1,0,0)).toString());
    Assertions.assertEquals("2->0->2", Main.sumV1(build(1,0,1), build(1,0,1)).toString());
    Assertions.assertEquals("2->4->3->4", Main.sumV1(build(1,2), build(1,2,3,4)).toString());
    Assertions.assertEquals("2->1->9", Main.sumV1(build(7,1,6), build(5,9,2)).toString());
  }

  @Test
  void sumV2() {
    Assertions.assertNull(Main.sumV2(null, build(1,2)));
    Assertions.assertNull(Main.sumV2(null, null));
    Assertions.assertNull(Main.sumV2(build(1,2), null));
    Assertions.assertEquals("2->2->2", Main.sumV2(build(1,1,1), build(1,1,1)).toString());
    Assertions.assertEquals("2", Main.sumV2(build(1), build(1)).toString());
    Assertions.assertEquals("0->1", Main.sumV2(build(9), build(1)).toString());
    Assertions.assertEquals("2", Main.sumV2(build(1,0,0), build(1,0,0)).toString());
    Assertions.assertEquals("2->0->2", Main.sumV2(build(1,0,1), build(1,0,1)).toString());
    Assertions.assertEquals("2->4->3->4", Main.sumV2(build(1,2), build(1,2,3,4)).toString());
    Assertions.assertEquals("2->1->9", Main.sumV2(build(7,1,6), build(5,9,2)).toString());
  }

  @Test
  void sumV3() {
    Assertions.assertNull(Main.sumV3(null, build(1,2)));
    Assertions.assertNull(Main.sumV3(null, null));
    Assertions.assertNull(Main.sumV3(build(1,2), null));
    Assertions.assertEquals("2->2->2", Main.sumV3(build(1,1,1), build(1,1,1)).toString());
    Assertions.assertEquals("2", Main.sumV3(build(1), build(1)).toString());
    Assertions.assertEquals("1->0", Main.sumV3(build(9), build(1)).toString());
    Assertions.assertEquals("2->0->0", Main.sumV3(build(1,0,0), build(1,0,0)).toString());
    Assertions.assertEquals("2", Main.sumV3(build(0,0,1), build(0,0,1)).toString());
    Assertions.assertEquals("2->0->2", Main.sumV3(build(1,0,1), build(1,0,1)).toString());
    Assertions.assertEquals("4->3->4->2", Main.sumV3(build(2,1), build(4,3,2,1)).toString());
    Assertions.assertEquals("9->1->2", Main.sumV3(build(6,1,7), build(2,9,5)).toString());
  }

  public static Node build(Integer... values) {
    Node prev = null;
    Node head = null;

    for (Integer value : values) {
      Node n = new Node(value);

      if (prev == null) {
        head = n;
      } else {
        prev.next = n;
      }

      prev = n;
    }

    return head;
  }
}
