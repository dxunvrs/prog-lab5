package core;

import exceptions.IdNotFoundException;
import models.Product;

import java.time.LocalDate;
import java.util.Iterator;

public interface CollectionRepository {
    Iterator<Product> getIterator();
    int getCollectionSize();
    void addElement(Product product);
    LocalDate getDateOfInit();
    void clearCollection();
    void updateProduct(int index, Product product);
    void removeById(int index);
    int findIndexById(int id) throws IdNotFoundException;
    void sort();
    void randomSort();
}
