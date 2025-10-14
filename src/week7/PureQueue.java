package week7;

public interface PureQueue<E> {
    boolean add(E e);
    boolean offer(E e);
    E remove();
    E poll();
    E element();
    E peek();
    boolean empty();
}
