package outer;

/**
 * Create by 163 on 2018/12/17
 */
public class Test {
    public static void main(String[] args) {
//        StaOut.staInner in = new StaOut.staInner();
        new MemOuter().new MemInner();
    }
}
