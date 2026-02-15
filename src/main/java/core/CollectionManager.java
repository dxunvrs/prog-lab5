package core;

import models.Product;

import java.util.LinkedList;

public class CollectionManager implements CollectionRepository {
    private final LinkedList<Product> collection;

    public CollectionManager(LinkedList<Product> collection) {
        this.collection = collection;
    }

    @Override
    public LinkedList<Product> getCollection() {
        return collection;
    }

//    public void addElement(Person person) {
//        collection.add(person);
//    }

//    public void removeElement(int id) {
//        for (Person person : collection) {
//            if person.
//        }
//    }
}
