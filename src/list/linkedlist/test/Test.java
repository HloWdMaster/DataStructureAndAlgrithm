package list.linkedlist.test;

import list.linkedlist.SLinkedList;

/**
 * Create by 163 on 2018/12/17
 */
public class Test {
    public static void main(String[] args) {
        SLinkedList<Integer> list = new SLinkedList<>();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        System.out.println(list);
    }
}
