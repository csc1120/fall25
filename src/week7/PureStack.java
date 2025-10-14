package week7;

public interface PureStack<E> {
    boolean empty();
    E push(E e);
    E pop();
    E peek();
}
