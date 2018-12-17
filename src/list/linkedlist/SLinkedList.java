package list.linkedlist;

/**
 * 单链表实现
 */
public class SLinkedList<T> {

    private Node<T> head;   //头结点
    private int size = 0;   //链表长度
    private int modCount;   //

    public SLinkedList() {
        head = new Node<>(null);
    }

    public void insert(T data) {   //链表尾部添加一个元素
        Node<T> p = new Node<>(data);
        head.next = p;
        size++;
    }


    public Node<T> remove(T data){  //删除一个元素
        // TODO: 2018/12/17
        return new Node<T>(data);
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

}
