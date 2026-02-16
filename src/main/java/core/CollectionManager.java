package core;

import models.Product;

import java.time.LocalDate;
import java.util.LinkedList;

public class CollectionManager implements CollectionRepository {
    private final LinkedList<Product> collection;
    private final LocalDate dateOfInit;

    public CollectionManager(LinkedList<Product> collection, LocalDate dateOfInit) {
        this.collection = collection;
        this.dateOfInit = dateOfInit;
    }

    public CollectionManager() {
        collection = new LinkedList<Product>();
        dateOfInit = LocalDate.now();
    }

    @Override
    public LocalDate getDateOfInit() {
        return dateOfInit;
    }

    @Override
    public LinkedList<Product> getCollection() {
        return collection;
    }

    @Override
    public void addElement(Product product) {
        collection.add(product);
    }

    @Override
    public void clearCollection() {
        collection.clear();
    }

    @Override
    public void updateProduct(int index, Product product) {
        collection.set(index, product);
    }

    @Override
    public int getCollectionSize() {
        return collection.size();
    }

    @Override
    public void removeById(int index) {
        collection.remove(index);
    }
}
