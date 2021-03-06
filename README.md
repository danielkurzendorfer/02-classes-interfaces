_This is an assignment to the [Software Architecture](https://ohm-softa.github.io) class at the [Technische Hochschule Nürnberg](http://www.th-nuernberg.de)._

# Assignment 2: Classes and interfaces revisited

In this assignment we'll be looking at 
- static and regular inner classes
- anonymous classes
- lambda expressions and `@FunctionalInterface`
by implementing a basic linked list.

A linked list consists of a _controller_ (the _list_ class) and elements that contain the actual payload (the _container_ or _element_ class).
The controller class maintains a reference to the first list element (`head`), and every element points to its successor (or to a `null` value, if it's the last).
The following UML shows all classes of this assignment (including the interfaces `java.util.Iterator<T>` and `java.lang.Iterable<T>`; we'll cover ).

_Note: the concept of an iterator will be discussed in detail in a few weeks._

![Classes](assets/class-spec.svg)


## Setup

1. Create a fork of this repository.
2. Clone your fork to get a local working copy.
3. Import the project to your IDE (choose _Gradle project_)


## Static and regular inner classes

1. [What is a static class?](http://lmgtfy.com/?q=java+static+inner+class)
	- Refresh your knowledge on UML (_e.g._ [here](http://plantuml.com/class-diagram))
	- What's the difference between a regular inner and static inner class?
	    - Von einer statischen Klasse muss ich kein Objekt erzeugen, bei einer normalen Klasse muss man ein Objekt erzeugen, um auf die Variablen darin zuzugreifen
	- Can you think of some use cases for both?
	    - (Static) Inner class: Element einer Liste
2. Implement `Element` as static inner class of `SimpleListImpl`.
	- Why is this class static, and ideally `private`?
	    - static: Elemente stehen alleine da, weil sie keine Informationen außen rum benötigen
	    - private: Elemente einer Liste sind nur über die Liste interessant, d.h. ohne Liste bestehen diese nicht. Daher können diese `private` sein, da nur die Liste die Verwaltung übernimmt.
3. Implement the `Iterator` interface as inner class of `SimpleListImpl`.
	- Why is it helpful to make this class non-static?
	    - er muss auf die nächsten Elemente zugreifen können und muss merken, wann er am Ende steht
4. Add the `Iterable` interface to your `SimpleListImpl`, and implement the required methods.
	- Why is implementing the `Iterable` interface essential for a (good) list implementation? (Hint: Check the test cases!)
	    - Möglichkeit, mittels foreach durch die eigen implementierte Liste zu iterieren
	- Are there any language definition constraints to this?
	    - keine Ahnung


## Anonymous (inner) classes and lambda expressions

1. Implement the `filter` method for your `SimpleListImpl` class (see `SimpleFilter` interface). (DK: functionalInterface ?)
2. Check the given test suite for an example on 
	- how to use an anonymous class with an interface.
	- how an anonymous class can be replaced by a lambda expression.
3. Add some test methods and implement another filter logic (_e.g._ every third number, or any number smaller than a certain value).
5. Review anonymous classes and lambdas.
	- Lambda expressions look very convenient; can you think of a scenario where they should not be used?
	- Recall how scoping works for anonymous (inner or local) classes; can you think of a scenario where to avoid them?
	    - bei jedem dritten Element geht das nicht, da der Lambda-Ausdruck keine Variable haben kann, die hochzählt... da hilft nur eine anonyme Klasse


Vorgehen:
- geht zuerst nicht zum kompilieren -> IntelliJ zu implementierende Methoden geben lassen
- private (muss keiner Wissen) statische Klasse "Element" - was braucht es? Zeiger auf nächstes Element und Inhalt
- Kontruktor um Inhalt zu setzen
- Liste braucht Basiselement ("head"), anfangs null (Element head = null;)
- Add-Methode: 2 Fälle --> Ist 1. Element null oder nicht?
    - if(head == null) { head = new Element(o); return; }
    - Element tmp = head; while(tmp.next != null) { tmp = tmp.Next; } tmp.next = new Element(o);
    - Möglich: Schlusselement merken, dann spart man sich die Schleife
- Wie bekommt man size raus? size++; in der add()-Methode

Einschub:
- Initiale Werte, die schon eingefügt werden sollen: in der Listen Klasse...
    - public SimpleListImpl(Object... inits) {
        for(Object o : inits) {
            add(o);
        }
      }
Ende Einschub
 
- In die Filter-Methode einfügen:
    - SimpleListImpl sl = new SimpleListImpl();
      for (Object o : this)
        if(filter.include(o)) sl.add(o);
      return sl;
    - benötige einen Iterator für die foreach Schleife, daher "Iterable" an SimpleListImpl-Klasse anfügen und
        public Iterator iterator() {
            return new Iterator() {
                Element cur = head;
                
                @override
                public boolean hasNext() {
                    return cur != null;
                }
                @override
                public Object next() {
                    Object o = cur.o;
                    cur = cur.next;
                    return o;
                }
            }
        }
        -> Das ist eine anonyme Klasse
                    