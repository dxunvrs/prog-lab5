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
public class CollectionManager {
    private static final Logger logger = LoggerFactory.getLogger(CollectionManager.class);
    private final List<Product> collection = new LinkedList<>();
    private LocalDateTime dateOfInit = LocalDateTime.now();

    /**
     * Последнее использованное id для автогенерации следующего
     */
    private int lastId = 0;

    /**
     * Инициализация коллекции
     * @param dateOfInit время инициализации
     */
    public void initCollection(LocalDateTime dateOfInit) {
        this.dateOfInit = dateOfInit;
        lastId = collection.stream().mapToInt(Product::getId).max().orElse(0);
        logger.info("Инициализация коллекции, последний id: {}, дата: {}", lastId, dateOfInit);
    }

    /**
     * Сортировка в естественном порядке, прописана в {@link Product}, который реализует Comparable
     */
    public void sort() {
        Collections.sort(collection);
        logger.info("Коллекция отсортирована в естественном порядке");
    }

    /**
     * Рандомная сортировка
     */
    public void randomSort() {
        Collections.shuffle(collection);
        logger.info("Коллекция отсортирована в случайном порядке");
    }

    /**
     * Добавление заданного продукта
     */
    public void addProduct(Product product) {
        product.setId(++lastId);
        product.setCreationDate(new Date());
        collection.add(product);
        logger.info("В коллекцию добавлен новый продукт {}", product);
    }

    /**
     * Удаление продукта по id
     */
    public void removeProductById(int id) {
        boolean removed = collection.removeIf(product -> product.getId()==id);
        if (!removed) throw new IdNotFoundException("Нет такого id");
        logger.info("Из коллекции удален элемент с id {}", id);
    }

    /**
     * Обновление продукта по id
     * @param productForm форма для запроса продукта
     */
    public void updateProductById(int id, ProductForm productForm) {
        Product updatedProduct = collection.stream()
                        .filter(product -> product.getId() == id)
                        .findFirst()
                        .orElseThrow(() -> new IdNotFoundException("Нет такого id"));
        Product product = productForm.getProduct();
        updatedProduct.update(product);
        logger.info("Элемент с id {} обновлен, новое значение {}", id, product);
    }

    /**
     * Очистка коллекции
     */
    public void clearCollection() {
        collection.clear();
        logger.info("Коллекция очищена");
    }

    /**
     * Получение размера коллекции
     */
    public int getCollectionSize() {
        return collection.size();
    }

    /**
     * Получение даты инициализации коллекции
     */
    public LocalDateTime getDateOfInit() {
        return dateOfInit;
    }

    /**
     * Получение суммы всех цен
     */
    public int getSumOfPrice() {
        return collection.stream().mapToInt(Product::getPrice).sum();
    }

    /**
     * Получение среднего значения всех цен
     */
    public double getAvgOfPrice() {
        return collection.stream().mapToInt(Product::getPrice).average().orElse(0.0);
    }

    /**
     * Получение отфильтрованных элементов коллекции в виде строки
     * @param filter лямбда-функция фильтр
     */
    public String getFormattedCollection(Predicate<Product> filter) {
        if (collection.isEmpty()) return "Коллекция пуста";

        String result = collection.stream()
                .filter(filter).map(Product::toFormattedString).collect(Collectors.joining("\n"));

        if (result.isEmpty()) return "Совпадений не найдено";
        return result;
    }

    /**
     * Сохранение коллекции
     */
    public void saveCollection(Consumer<Product> saveAction) {
        collection.forEach(saveAction);
    }
}
