package core;

import exceptions.IdNotFoundException;
import models.Product;

import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager implements CollectionRepository {
    private final List<Product> collection;
    private LocalDateTime dateOfInit;

    public CollectionManager() {
        collection = new LinkedList<Product>();
        dateOfInit = LocalDateTime.now();
    }

    @Override
    public void setDateOfInit(LocalDateTime dateOfInit) {
        this.dateOfInit = dateOfInit;
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
    public LocalDateTime getDateOfInit() {
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
