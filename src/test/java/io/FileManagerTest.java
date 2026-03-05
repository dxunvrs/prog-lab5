package io;

import core.CollectionManager;
import models.Coordinates;
import models.Person;
import models.Product;
import models.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    private FileManager fileManager;
    private CollectionManager collectionManager;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
        collectionManager = new CollectionManager();
    }

    @Test
    @DisplayName("Сохранение и загрузка коллекции")
    void saveAndLoad() throws IOException {
        Path file = tempDir.resolve("collection.csv");
        Files.createFile(file);
        fileManager.setFileName(file.toString());

        collectionManager.addProduct(new Product("Test1", new Coordinates(23L, 23), 223, UnitOfMeasure.METERS,
                new Person("Owner1", LocalDate.parse("2000-01-01"), 120L)
        ));
        collectionManager.addProduct(new Product("Test2", new Coordinates(22L, 20), 200, UnitOfMeasure.PCS,
                new Person("Owner2", LocalDate.parse("2010-11-12"), 160L)
        ));

        fileManager.save(collectionManager);
        String previousCollection = collectionManager.getFormattedCollection(product -> true);

        collectionManager = new CollectionManager();
        fileManager.load(collectionManager);
        assertEquals(previousCollection, collectionManager.getFormattedCollection(product -> true));
    }

    @Test
    @DisplayName("Ошибка чтения поврежденного файла")
    void loadCorruptedFile() throws IOException {
        Path file = tempDir.resolve("collection.csv");
        Files.createFile(file);
        fileManager.setFileName(file.toString());

        Files.writeString(file, "2026-02-19T22:56:27.094039103\ndafa32adf, adfadsf, ewrqer");
        // Files.writeString(file, "2026-02-19T22:56:27.094039103\n2,3q4,324,234,\"04.03.2026 19:25:35\",234,PCS,324,2333-02-03,324");

        int previousCollectionSize = collectionManager.getCollectionSize();

        fileManager.load(collectionManager);
        assertEquals(previousCollectionSize, collectionManager.getCollectionSize());
    }
}
