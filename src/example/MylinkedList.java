package example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双链表实现
 */
public class MylinkedList<T> implements Iterable<T> {

    private int size;   //链表尺寸
    private int modCount = 0;   //帮助迭代器检测链表变化
    private Node<T> head;   //头指针
    private Node<T> end;    //尾指针


    private static class Node<T> {
        T data;
        Node<T> pre;
        Node<T> next;

        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        public String toString() {
            return data.toString();
        }

    }

    public MylinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() { //初始化链表
        head = new Node<>(null, null, null);  //初始化头指针
        end = new Node<>(null, head, null);  //尾指针
        head.next = end;

        size = 0;
        modCount++;
    }

    public int size() { //获取链表尺寸
        return size;
    }

    public boolean isEmpty() {  //判断链表是否为空
        return size == 0;
    }

    public boolean add(T t) {
        add(size(), t);
        return true;
    }

    public void add(int idx, T t) {
        addBefore(getNode(idx, 0, size()), t);
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set(int idx, T newT) {
        Node<T> p = getNode(idx);
        T oldT = p.data;
        p.data = newT;
        return oldT;
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private void addBefore(Node<T> p, T t) { //链表中添加一个结点
        Node<T> newNode = new Node<>(t, p.pre, p);
        newNode.pre.next = newNode;
        p.pre = newNode;

        size++;
        modCount++;
    }

    private T remove(Node<T> p) {   //删除指定结点 返回删除结点的信息
        p.next.pre = p.pre;
        p.pre.next = p.next;
        size--;
        modCount++;
        return p.data;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        Node<T> p;
        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (idx < size() / 2) {
            p = head.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = end;
            for (int i = size(); i > idx; i--) {
                p = p.pre;
            }
        }
        return p;
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> curr = head.next;
        private int exceptedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return curr != end;
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
            if (modCount != exceptedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove)
                throw new IllegalStateException();
            MylinkedList.this.remove(curr.pre);
            exceptedModCount++;
            okToRemove = false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            sb.append("" + it.next() );
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
