package structures;

import java.util.Iterator;

/**
 * A {@link ListInterface} is a container that supports insertion, removal, and
 * searching.
 *
 * @author jcollard
 *
 * @param <T>
 */
public class RecursiveList<T> implements ListInterface<T> {

    private Node<T> list;
    private int listSize;

    public RecursiveList(){
        list = null;
        listSize = 0;
    }

    public Iterator<T> iterator() {
        return new ListIterator<>(list);
    }

    /**
     * Returns the number of elements in this {@link ListInterface}. This method
     * runs in O(1) time.
     *
     * @return the number of elements in this {@link ListInterface}
     */

    @Override
    public int size(){
        return listSize;
    }

    /**
     * Adds an element to the front of this {@link ListInterface}. This method
     * runs in O(1) time. For convenience, this method returns the
     * {@link ListInterface} that was modified.
     *
     * @param elem
     *            the element to add
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return The modified {@link ListInterface}
     */
    @Override
    public ListInterface<T> insertFirst(T elem){
        if(elem == null){
            throw new NullPointerException();
        }
        return insertAt(0, elem);
    }

    /**
     * Adds an element to the end of this {@link ListInterface}. This method
     * runs in O(size) time. For convenience, this method returns the
     * {@link ListInterface} that was modified.
     *
     * @param elem
     *            the element to add
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return the modified {@link ListInterface}
     */
    @Override
    public ListInterface<T> insertLast(T elem){
        if(elem == null){
            throw new NullPointerException();
        }

        return insertAt(listSize, elem);
    }

    /**
     * Adds an element at the specified index such that a subsequent call to
     * {@link ListInterface#get(int)} at {@code index} will return the inserted
     * value. This method runs in O(index) time. For convenience, this method
     * returns the {@link ListInterface} that was modified.
     *
     * @param index
     *            the index to add the element at
     * @param elem
     *            the element to add
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @throws IndexOutOfBoundsException
     *             if {@code index} is less than 0 or greater than
     *             {@link ListInterface#size()}
     * @return The modified {@link ListInterface}
     */
    @Override
    public ListInterface<T> insertAt(int index, T elem){
        if(elem == null){
            throw new NullPointerException();
        }
        if((index < 0) || (index > listSize)){
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<T>(elem);
        if(index == 0){
            newNode.setNext(list);
            list = newNode;
        }
        else{
            insertHelper(list, index, newNode);
        }
        listSize++;
        return this;
    }

    /**
     * Helper method for insert.
     * @param currentNode
     * @param index
     * @param newNode
     */
   private void insertHelper(Node<T> currentNode, int index, Node<T> newNode){
        if(index == 1){
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        else{
            insertHelper(currentNode.getNext(), index - 1, newNode);
        }
   }


    /**
     * Removes the first element from this {@link ListInterface} and returns it.
     * This method runs in O(1) time.
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the removed element
     */
    @Override
    public T removeFirst(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return removeAt(0);
    }


    /**
     * <p>
     * Removes the last element from this {@link ListInterface} and returns it.
     * This method runs in O(size) time.
     *</p>
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the removed element
     */
    @Override
    public T removeLast(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return removeAt(listSize - 1);
    }


    /**
     * Removes the ith element in this {@link ListInterface} and returns it.
     * This method runs in O(i) time.
     *
     * @param i
     *            the index of the element to remove
     * @throws IndexOutOfBoundsException
     *             if {@code i} is less than 0 or {@code i} is greater than or
     *             equal to {@link ListInterface#size()}
     * @return The removed element
     */

    @Override
    public T removeAt(int i){
        if((i < 0) || (i >= listSize)){
            throw new IndexOutOfBoundsException();
        }
        T listElement;
        //base case
        if(i == 0){
            listElement = list.getData();
            list = list.getNext();
        }

        else{
            listElement = removeHelper(list, i);
        }
        listSize--;
        return listElement;
    }

    /**
     * Helper method for remove.
     * @param toCheck
     * @param i
     * @return
     */

    private T removeHelper(Node<T> toCheck, int i) {
        T temp = null;
        //base case
        if (i == 1) {
            if (toCheck.getNext().getNext() == null) {
                temp = toCheck.getNext().getData();
                Node<T> newNode = toCheck.getNext();
                newNode.setNext(null);
                return temp;
            }
            else {
                Node<T> newNode = toCheck.getNext();
                temp = newNode.getData();
                toCheck.setNext(toCheck.getNext().getNext());
                newNode.setData(null);
                return temp;
            }
        }
        return removeHelper(toCheck.getNext(), i -1);
    }

    /**
     * Returns the first element in this {@link ListInterface}. This method runs
     * in O(1) time.
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the first element in this {@link ListInterface}.
     */
    @Override
    public T getFirst(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return get(0);
    }

    /**
     * Returns the last element in this {@link ListInterface}. This method runs
     * in O(size) time.
     *
     * @throws IllegalStateException
     *             if the {@link ListInterface} is empty.
     * @return the last element in this {@link ListInterface}.
     */
    @Override
    public T getLast(){
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return get(listSize - 1);
    }

    /**
     * Returns the ith element in this {@link ListInterface}. This method runs
     * in O(i) time.
     *
     * @param i
     *            the index to lookup
     * @throws IndexOutOfBoundsException
     *             if {@code i} is less than 0 or {@code i} is greater than or
     *             equal to {@link ListInterface#size()}
     * @return the ith element in this {@link ListInterface}.
     */
    @Override
    public T get(int i){
        if((i < 0) || (i >= listSize)){
            throw new IndexOutOfBoundsException();
        }
        return getHelper(list, i);
    }

    /**
     * Helper method for get.
     * @param i
     * @param toCheck
     * @return
     */
    //TODO
    private T getHelper(Node<T> toCheck, int i){
        if(i == 0){
            return toCheck.getData();
        }
        else{
            return getHelper(toCheck.getNext(), i -1);
        }
    }

    /**
     * Removes {@code elem} from this {@link ListInterface} if it exists. If
     * multiple instances of {@code elem} exist in this {@link ListInterface}
     * the one associated with the smallest index is removed. This method runs
     * in O(size) time.
     *
     * @param elem
     *            the element to remove
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return {@code true} if this {@link ListInterface} was altered and
     *         {@code false} otherwise.
     */
    // TODO
    @Override
    public boolean remove(T elem){
        if(elem == null){
            throw new NullPointerException();
        }
        int indexElem = indexOf(elem);
        if(indexElem == -1){
            return false;
        }
        else{
            removeAt(indexElem);
            return true;
        }
    }

    /**
     * Returns the smallest index which contains {@code elem}. If there is no
     * instance of {@code elem} in this {@link ListInterface} then -1 is
     * returned. This method runs in O(size) time.
     *
     * @param elem
     *            the element to search for
     * @throws NullPointerException
     *             if {@code elem} is {@code null}
     * @return the smallest index which contains {@code elem} or -1 if
     *         {@code elem} is not in this {@link ListInterface}
     */
    @Override
    public int indexOf(T elem){
        if(elem == null){
            throw new NullPointerException();
        }
        return indexOfHelper(elem, list, 0);
    }

    /**
     * Helper method for indexOf.
     * @param toFind
     * @param toCheck
     * @param currentIndex
     * @return
     */

    public int indexOfHelper(T toFind, Node<T> toCheck, int currentIndex){
        if(toCheck == null){
            return -1;
        }
        if(currentIndex >= size()){
            return -1;
        }
        if(toCheck.getData().equals(toFind)){
            return currentIndex;
        }
        else{
            return indexOfHelper(toFind, toCheck.getNext(), currentIndex+1);
        }
    }

    /**
     * Returns {@code true} if this {@link ListInterface} contains no elements
     * and {@code false} otherwise. This method runs in O(1) time.
     *
     * @return {@code true} if this {@link ListInterface} contains no elements
     *         and {@code false} otherwise.
     */
    @Override
    public boolean isEmpty(){
        if(listSize == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
