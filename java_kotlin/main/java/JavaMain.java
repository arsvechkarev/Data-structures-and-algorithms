import java.util.ArrayList;
import java.util.List;

public class JavaMain {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    List list2 = list;
    list2.add("");
    Integer integer = list.get(0);
    System.out.println("Int = " + integer);
  }
}
