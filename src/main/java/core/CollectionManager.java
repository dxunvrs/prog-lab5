package core;

import exceptions.IdNotFoundException;
import models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ProductForm;

import java.time.LocalDateTime;
import java.util.*;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Класс для управления коллекцией
 */
public class CollectionManager implements CollectionRepository {
    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    private final List<Product> collection = new LinkedList<>();
    private LocalDateTime dateOfInit = LocalDateTime.now();
    private int lastId = 0;

    @Override
    public void initCollection(LocalDateTime dateOfInit) {
        this.dateOfInit = dateOfInit;
        lastId = collection.stream().mapToInt(Product::getId).max().orElse(0);
    }

    /**
     * Сортировка в естественном порядке, прописана в {@link Product}, который реализует Comparable
     */
    @Override
    public void sort() {
        Collections.sort(collection);
    }

    /**
     * Рандомная сортировка
     */
    @Override
    public void randomSort() {
        Collections.shuffle(collection);
    }

    /**
     * Добавление заданного продукта
     */
    @Override
    public void addProduct(Product product) {
        product.setId(++lastId);
        product.setCreationDate(new Date());
        collection.add(product);
        logger.info("В коллекцию добавлен новый продукт {}", product);
    }

    @Override
    public void removeProductById(int id) {
        boolean removed = collection.removeIf(product -> product.getId()==id);
        if (!removed) throw new IdNotFoundException("Нет такого id");
    }

    @Override
    public void updateProductById(int id, ProductForm productForm) {
        Product updatedProduct = collection.stream()
                        .filter(product -> product.getId() == id)
                        .findFirst()
                        .orElseThrow(() -> new IdNotFoundException("Нет такого id"));
        Product product = productForm.getProduct();
        updatedProduct.update(product);
        logger.info("Элемент с id {} обновлен", id);
    }

    /**
     * Очистка коллекции
     */
    @Override
    public void clearCollection() {
        collection.clear();
        logger.info("Коллекция очищена");
    }

    /**
     * Получение размера коллекции
     */
    @Override
    public int getCollectionSize() {
        return collection.size();
    }

    /**
     * Получение даты инициализации коллекции
     */
    @Override
    public LocalDateTime getDateOfInit() {
        return dateOfInit;
    }

    @Override
    public int getSumOfPrice() {
        return collection.stream().mapToInt(Product::getPrice).sum();
    }

    @Override
    public double getAvgOfPrice() {
        return collection.stream().mapToInt(Product::getPrice).average().orElse(0.0);
    }

    @Override
    public String getFormattedCollection(Predicate<Product> filter) {
        if (collection.isEmpty()) return "Коллекция пуста";

        String result = collection.stream()
                .filter(filter).map(Product::toFormattedString).collect(Collectors.joining("\n"));

        if (result.isEmpty()) return "Совпадений не найдено";
        return result;
    }

    @Override
    public void saveCollection(Consumer<Product> saveAction) {
        collection.forEach(saveAction);
    }
}
