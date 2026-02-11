package core;

import models.Person;

import java.util.LinkedList;

public class CollectionManager {
    private final LinkedList<Person> collection;

    public CollectionManager(LinkedList<Person> collection) {
        this.collection = collection;
    }

    public void addElement(Person person) {
        collection.add(person);
    }

//    public void removeElement(int id) {
//        for (Person person : collection) {
//            if person.
//        }
//    }
}
