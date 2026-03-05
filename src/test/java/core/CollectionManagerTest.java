package core;

import exceptions.IdNotFoundException;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionManagerTest {
    private CollectionManager collectionManager;

    @BeforeEach
    void setUp() {
        collectionManager = new CollectionManager();
    }

    @Test
    @DisplayName("Добавление элемента в коллекцию")
    void addElement() {
        int size = collectionManager.getCollectionSize();
        collectionManager.addProduct(new Product());
        assertEquals(size + 1, collectionManager.getCollectionSize());
    }

    @Test
    @DisplayName("Проверка id")
    void removeByIdThrowsException() {
        //collectionManager.addProduct(new Product());
        assertThrows(IdNotFoundException.class, () -> {
            collectionManager.removeProductById(1);
        });
    }

    @Test
    @DisplayName("Очистка коллекции")
    void clearCollection() {
        collectionManager.addProduct(new Product());
        collectionManager.clearCollection();
        assertEquals(0, collectionManager.getCollectionSize());
    }
}
