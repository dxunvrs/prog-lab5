package core;

import exceptions.IdNotFoundException;
import models.Product;

import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager implements CollectionRepository {
    private final List<Product> collection;
    private LocalDateTime dateOfInit;

    public CollectionManager() {
        collection = new LinkedList<>();
        dateOfInit = LocalDateTime.now();
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
    public void addProduct(Product product) {
        collection.add(product);
    }

    @Override
    public void removeProduct(int index) {
        collection.remove(index);
    }

    @Override
    public void updateProduct(int index, Product product) {
        collection.set(index, product);
    }

    @Override
    public void clearCollection() {
        collection.clear();
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
    public Product getProduct(int index) {
        return collection.get(index);
    }

    @Override
    public Iterator<Product> getIterator() {
        return Collections.unmodifiableCollection(collection).iterator();
    }

    @Override
    public int getCollectionSize() {
        return collection.size();
    }

    @Override
    public LocalDateTime getDateOfInit() {
        return dateOfInit;
    }

    @Override
    public void setDateOfInit(LocalDateTime dateOfInit) {
        this.dateOfInit = dateOfInit;
    }
}
