package io;

import core.CollectionRepository;
import models.Product;

import java.time.LocalDateTime;
import java.util.Iterator;

public interface FileStorage {
    void save(Iterator<Product> iterator, LocalDateTime dateOfInit);
    void load(CollectionRepository collectionManager);
    void setFileName(String fileName);
}
