package core;

import exceptions.IdNotFoundException;
import models.Product;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionManager implements CollectionRepository {
    private final List<Product> collection;
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
    public Iterator<Product> getIterator() {
        return Collections.unmodifiableCollection(collection).iterator();
    }

    @Override
    public int findIndexById(int id) throws IdNotFoundException {
        int index = 0;
        for (Product product: collection) {
            if (id == product.getId()) {
                return index;
            }
            index++;
        }
        throw new IdNotFoundException("Такого id нет");
    }

    @Override
    public void sort() {
        Collections.sort(collection);
    }

    @Override
    public void randomSort() {
        Collections.shuffle(collection);
    }

    @Override
    public LocalDate getDateOfInit() {
        return dateOfInit;
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
