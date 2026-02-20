package core;

import exceptions.IdNotFoundException;
import models.Product;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс для управления коллекцией
 */
public class CollectionManager implements CollectionRepository {
    private final List<Product> collection = new LinkedList<>();
    private LocalDateTime dateOfInit = LocalDateTime.now();

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
        collection.add(product);
    }

    /**
     * Удаление продукта по индексу
     */
    @Override
    public void removeProduct(int index) {
        collection.remove(index);
    }

    /**
     * Обновление продукта по индексу
     */
    @Override
    public void updateProduct(int index, Product product) {
        collection.set(index, product);
    }

    /**
     * Очистка коллекции
     */
    @Override
    public void clearCollection() {
        collection.clear();
    }

    /**
     * Поиск индекса по id продукта. O(n)
     * @return Возвращает индекс, если он был найден
     * @throws IdNotFoundException ошибка поиска, если id не найден
     */
    @Override
    public int findIndexById(int id) throws IdNotFoundException {
        int index = 0;
        for (Product product: collection) {
            if (id == product.getId()) {
                return index;
            }
            index++;
        }
        throw new IdNotFoundException("Такого id нет");
    }

    /**
     * Получение продукта по индексу
     */
    @Override
    public Product getProduct(int index) {
        return collection.get(index);
    }

    /**
     * Получение итератора
     * @return Защищенный от удаления итератор коллекции
     */
    @Override
    public Iterator<Product> getIterator() {
        return Collections.unmodifiableCollection(collection).iterator();
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

    /**
     * Устанавливает дату инициализации при загрузке уже существующей коллекции
     */
    @Override
    public void setDateOfInit(LocalDateTime dateOfInit) {
        this.dateOfInit = dateOfInit;
    }
}
