
package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<T> implements Iterator<T> {
    private Node<T> top;

    public ListIterator(Node<T> top){
        this.top = top;
    }

    @Override
    public boolean hasNext() {
        if(top == null){
            return false;
        }
        else{
            return true;
        }
    }
    @Override
    public T next(){
        if(hasNext() == false){
            throw new NoSuchElementException();
        }
        else{
            T tempVar = top.getData();
            top = top.getNext();
            return tempVar;
        }

    }
}