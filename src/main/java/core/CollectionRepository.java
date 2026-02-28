package core;

import models.Product;
import utility.ProductForm;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Интерфейс для взаимодействия с {@link CollectionManager}
 */
public interface CollectionRepository {
    void sort();
    void randomSort();

    int getSumOfPrice();
    double getAvgOfPrice();

    void addProduct(Product product);
    void updateProductById(int id, ProductForm productForm);
    void removeProductById(int id);
    void clearCollection();

    void initCollection(LocalDateTime dateOfInit);
    void saveCollection(Consumer<Product> saveAction);
    LocalDateTime getDateOfInit();
    int getCollectionSize();
    String getFormattedCollection(Predicate<Product> filter);
}
