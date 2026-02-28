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

    void addProduct(Product product);
    void updateProductById(int id, ProductForm productForm);
    void removeProductById(int id);
    void clearCollection();

    int getCollectionSize();
    LocalDateTime getDateOfInit();

    int getSumOfPrice();
    double getAvgOfPrice();

    String getFormattedCollection(Predicate<Product> filter);

    void initCollection(LocalDateTime dateOfInit);
    void saveCollection(Consumer<Product> saveAction);
}
