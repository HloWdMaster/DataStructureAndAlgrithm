package list.linkedlist.test;

import list.linkedlist.SLinkedList;

/**
 * Create by 163 on 2018/12/17
 */
public class Test {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();

        list.insert(4);
        list.insert(2);
        list.insert(3);
//        list.remove(2);
        list.reverse();
        System.out.println(list);

//        List<Integer> list= new ArrayList<>();
//        list.add(1);
//        System.out.println(list);
//        List ref = list;
//        ref.remove(0);
//        System.out.println(list);

    }
}
