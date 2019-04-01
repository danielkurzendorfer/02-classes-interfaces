package de.thro.inf.prg3.a02;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList {

	// TODO: Implement the required methods.
    class Element {
        Element next;
        Object obj;

        public Element(Object input, Element elem) {
            obj = input;
            next = elem;
        }

    }

    public void add(Object obj) {

    }

    public int size() {
        return 0;
    }

    public SimpleList filter(SimpleFilter sl) {
        return this;
    }

}
