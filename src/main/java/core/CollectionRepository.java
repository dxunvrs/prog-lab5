package core;

import models.Product;

import java.time.LocalDate;
import java.util.LinkedList;

public interface CollectionRepository {
    LinkedList<Product> getCollection();
    int getCollectionSize();
    void addElement(Product product);
    LocalDate getDateOfInit();
    void clearCollection();
    void updateProduct(int index, Product product);
    void removeById(int index);
}
