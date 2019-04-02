package de.thro.inf.prg3.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {

    Element head;
    int size = 0;

    public void add(Object obj) {
        if (head == null)
            head = new Element(obj);
        else {
            Element elem = head;
            while (elem.getNext() != null) {
                elem = elem.getNext();
            }
            elem.setNext(new Element(obj));
        }
        size++;
    }

    public int size() {
        return size;
    }

    public SimpleList filter(SimpleFilter sl) {
        SimpleList results = new SimpleListImpl();
        for (Object o : this) {
            if (sl.include(o))
                results.add(o);
        }
        return results;
    }


    @Override
    public Iterator<Object> iterator() {
        return new SimpleIteratorImpl();
    }

    // TODO: Implement the required methods.
    static class Element {
        Element next;
        Object item;

        public Element(Object item) {
            this.item = item;
            this.next = null;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

    }

    private class SimpleIteratorImpl implements Iterator<Object> {
        Element curr = head;

        // Der Zeiger zeigt am Anfang auf das erste Element der Liste, daher zeigt er quasi auf das nächste Element
        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Object next() {
            Object o = curr.getItem();
            curr = curr.getNext();

            return o;
        }
    }

}
