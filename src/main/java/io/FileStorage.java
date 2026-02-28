package io;

import core.CollectionRepository;

/**
 * Интерфейс для взаимодействия с {@link FileManager}
 */
public interface FileStorage {
    void save(CollectionRepository collectionRepository);
    void load(CollectionRepository collectionRepository);
    void setFileName(String fileName);
}
