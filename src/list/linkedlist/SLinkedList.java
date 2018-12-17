package list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 单链表实现
 */
public class SLinkedList<T> implements Iterable<T> {

    private Node<T> head;   //头指针
    private Node<T> end;    //尾指针
    private int size;   //链表长度
    private int modCount = 0;   //辅助iterator实现修改

    public SLinkedList() {
        head = new Node<T>(null);
        end = new Node<>(null);
        head.next = end;
        size = 0;
    }

    public int size() { //单链表尺寸
        return size;
    }

    public void insert(T data) {   //链表尾部添加一个元素
        add(size(), data);
    }

    public void add(int idx, T data) {  //在指定位置插入元素
        addAfter(getNode(idx), data);
    }

    private void addAfter(Node<T> p, T data) {
        Node<T> newT = new Node<>(data);
        newT.next = end;
        p.next = newT;

        size++;
        modCount++;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size());
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        Node<T> p = head;
        for (int i = 0; i < idx; i++) {
            p = p.next;
        }
        return p;
    }


    public T remove(int idx) {  //删除一个元素
        return remove(getNode(idx - 1));
    }

    private T remove(Node<T> p) {   //删除后一个结点
        T old = p.next.data;
        p.next = p.next.next;
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
        private Node<T> curr = head.next;
        private int exceptedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return curr.next != end;
        }

        @Override
        public T next() {
            if (exceptedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = curr.data;
            curr = curr.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (exceptedModCount != modCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            SLinkedList.this.remove(size());
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
