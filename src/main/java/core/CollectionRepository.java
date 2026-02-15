package core;

import models.Product;

import java.util.LinkedList;

public interface CollectionRepository {
    LinkedList<Product> getCollection();
}
