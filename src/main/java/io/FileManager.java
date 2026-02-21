package io;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvReadException;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.CollectionRepository;
import models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Класс для сохранения/загрузки коллекции из файла.
 * Для сериализации/десериализации - Jackson
 */
public class FileManager implements FileStorage {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
    private final CsvMapper mapper = new CsvMapper();
    private final CsvSchema schema = mapper.schemaFor(Product.class);

    /**
     * Стандартное имя файла
     */
    private String fileName = "collection.csv";

    public FileManager() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Получение имени файла
     */
    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * Установка имени файла из аргументов запуска программы
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Метод загрузки коллекции из .csv файла. Используется {@link Scanner}
     */
    @Override
    public void load(CollectionRepository collectionManager) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            LocalDateTime dateOfInit = null;
            if (scanner.hasNextLine()) {
                dateOfInit = LocalDateTime.parse(scanner.nextLine());
            }
//            if (scanner.hasNextLine()) {
//                scanner.nextLine();
//            }
            while (scanner.hasNextLine()) {
                String dateLine = scanner.nextLine();
                if (dateLine.trim().isEmpty()) continue;
                Product product = mapper.readerFor(Product.class).with(schema).readValue(dateLine);
                collectionManager.addProduct(product);
            }
            collectionManager.setDateOfInit(dateOfInit);
            logger.info("Коллекция из файла {} успешно загружена", fileName);
            System.out.println("Коллекция загружена");
        } catch (DateTimeParseException e) {
            logger.error("Не удалось распарсить дату", e);
            System.out.println("Неверный формат даты инициализации коллекции в файле, загрузка не удалась, создана новая коллекция");
        } catch (CsvReadException | JsonParseException e) {
            logger.error("Не удалось распарсить файл", e);
            System.out.println("Структура CSV не распознана, загрузка не удалась, создана новая коллекция");
        } catch (InvalidFormatException e) {
            logger.error("Не удалось распарсить данный тип", e);
            System.out.println("Неверный формат данных, загрузка не удалась, создана новая коллекция");
        } catch (DatabindException e) {
            logger.error("Ошибка маппинга полей");
            System.out.println("Ошибка маппинга полей, загрузка не удалась, создана новая коллекция");
        } catch (FileNotFoundException e) {
            logger.error("Ошибка загрузки: файл не найден", e);
            System.out.println("Файл не найден, загрузка не удалась, создана новая коллекция");
        } catch (SecurityException e) {
            logger.error("Ошибка загрузки: недостаток прав", e);
            System.out.println("Недостаточно прав, загрузка не удалась, создана новая коллекция");
        } catch (IOException e) {
            logger.error("Ошибка загрузки", e);
            System.out.println("Ошибка загрузки: " + e.getMessage());
            System.out.println("Создана новая коллекция");
        }
    }

    /**
     * Метод сохранения коллекции в .csv файл. Используется {@link BufferedWriter}
     * @param iterator итератор коллекции для последовательного сохранения
     * @param dateOfInit сохраняет также дату инициализации в первую строку файла
     */
    @Override
    public void save(Iterator<Product> iterator, LocalDateTime dateOfInit) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dateOfInit.toString());
            writer.newLine();
//            writer.write(mapper.writer(schema.withHeader()).writeValueAsString(null).trim());
//            writer.newLine();
            while (iterator.hasNext()) {
                String line = mapper.writer(schema.withoutHeader()).writeValueAsString(iterator.next()).trim();
                writer.write(line);
                writer.newLine();
            }
            logger.info("Коллекция успешно сохранена в файл: {}", fileName);
            System.out.println("Коллекция успешно сохранена в файл " + fileName);
        } catch (FileNotFoundException e) {
            logger.error("Ошибка сохранения: файл не найден", e);
            System.out.println("Файл не найден, сохранение не удалось");
        } catch (SecurityException e) {
            logger.error("Ошибка сохранения: недостаток прав", e);
            System.out.println("Недостаточно прав, сохранение не удалось");
        } catch (IOException e) {
            logger.error("Ошибка сохранения", e);
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}
