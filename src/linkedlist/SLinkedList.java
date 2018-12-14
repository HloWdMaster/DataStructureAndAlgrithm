package linkedlist;

/**
 * Create by 163 on 2018/12/14
 */
public class SLinkedList<T> {

    private Node<T> head;   //头结点
    private int size = 0;

    public SLinkedList() {
        head = new Node<>();
    }

    public void add(T data) {   //添加一个元素
        head.data = data;
        head.next = new Node<>();
        size++;
    }

    public Node<T> remove(T data){  //删除一个元素
        
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }

}
