package outer;

/**
 * Create by 163 on 2018/12/17
 */
public class OuterClass {

    private int outData = 10;

    static class Inner {
        private int data;

        private void showOut() {

        }
    }

    public void showInner() {
        System.out.println(new Inner().data);
    }

    public static void main(String[] args) {
//        new OuterClass().showInner();
        Inner in = new Inner();

    }
}
