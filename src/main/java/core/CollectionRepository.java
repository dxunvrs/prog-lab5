package core;

import exceptions.IdNotFoundException;
import models.Product;

import java.time.LocalDateTime;
import java.util.Iterator;

public interface CollectionRepository {
    void sort();
    void randomSort();
    void addProduct(Product product);
    void removeProduct(int index);
    void updateProduct(int index, Product product);
    void clearCollection();
    int findIndexById(int id) throws IdNotFoundException;
    Product getProduct(int index);
    Iterator<Product> getIterator();
    int getCollectionSize();
    LocalDateTime getDateOfInit();
    void setDateOfInit(LocalDateTime dateOfInit);
}
