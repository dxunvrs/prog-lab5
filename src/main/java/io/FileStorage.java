package io;

import core.CollectionRepository;
import models.Product;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Интерфейс для взаимодействия с {@link FileManager}
 */
public interface FileStorage {
    void save(Iterator<Product> iterator, LocalDateTime dateOfInit);
    void load(CollectionRepository collectionManager);
    String getFileName();
    void setFileName(String fileName);
}
