package list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 单链表实现
 */
public class SLinkedList<T> implements Iterable<T> {

    private Node<T> head;   //哨兵节点
    private int size;   //链表长度
    private int modCount = 0;   //辅助iterator实现修改

    public SLinkedList() {
        head = new Node<T>(null);
        head.next = null;
        size = 0;
    }

    public int size() { //单链表尺寸
        return size;
    }

    public void insert(T data) {   //链表尾部添加一个元素
        if (size() == 0)
            addAfter(head, data);
        else
            add(size() - 1, data);
    }

    public void add(int idx, T data) {  //在指定位置插入元素
        addAfter(getNode(idx), data);
    }


    private void addAfter(Node<T> p, T data) {
        Node<T> newT = new Node<>(data);
        newT.next = p.next;
        p.next = newT;

        size++;
        modCount++;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        if (lower < 0 || upper > size() - 1)
            throw new IndexOutOfBoundsException();
        Node<T> p = head.next;
        for (int i = 0; i < idx; i++) {
            p = p.next;
        }
        return p;
    }

    //反转链表
    public void reverse() {
        Node<T> rev = null;
        Node<T> curr = head.next;
        while (curr != null) {
            Node<T> temp = curr.next;
            curr.next = rev;
            rev = curr;
            curr = temp;
        }
        head.next = rev;
    }

    public T remove() {
        return remove(size() - 1);
    }

    public T remove(int idx) {  //删除一个元素
        return remove(getNode(idx));
    }

    private T remove(Node<T> p) {   //删除后一个结点
        if (head.next == null)
            throw new NoSuchElementException();
        Node temp = head;
        T old = p.data;
        while (temp.next != p) {
            temp = temp.next;
        }
        temp.next = p.next;
        size--;
        modCount++;
        return old;
    }

    @Override
    public Iterator iterator() {
        return new SLinkedListIterator();
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private class SLinkedListIterator implements Iterator<T> {
        private Node<T> curr = head;
        private int exceptedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return curr.next != null;
        }

        @Override
        public T next() {
            if (exceptedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            curr = curr.next;
            T nextItem = curr.data;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (exceptedModCount != modCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            SLinkedList.this.remove();
            exceptedModCount++;
            okToRemove = false;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            sb.append("" + it.next());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
