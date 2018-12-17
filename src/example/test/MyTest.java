package example.test;

import example.MylinkedList;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Create by 163 on 2018/12/17
 */
public class MyTest {
    public static void main(String[] args) {
        MylinkedList<Integer> list = new MylinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);
        System.out.println(list);
    }
}
