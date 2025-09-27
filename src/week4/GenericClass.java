/*
 * Course: CSC-1120
 * Generics
 * GenericClass
 * Name: Sean Jones
 * Last Updated: 09-26-25
 */
package week4;

/**
 * A class using generics
 * @param <E> the type of element used in the class
 */
public class GenericClass<E> {
    private E myGenericVariable;

    /**
     * 1 param Constructor that assigns a value to myGenericVariable
     * @param myGenericVariable the element to assign
     */
    public GenericClass(E myGenericVariable) {
        this.myGenericVariable = myGenericVariable;
    }

    public E getMyGenericVariable() {
        return myGenericVariable;
    }

    public void setMyGenericVariable(E myGenericVariable) {
        this.myGenericVariable = myGenericVariable;
    }
}
