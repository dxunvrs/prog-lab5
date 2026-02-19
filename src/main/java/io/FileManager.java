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

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

public class FileManager implements FileStorage {
    private final CsvMapper mapper = new CsvMapper();
    private final CsvSchema schema = mapper.schemaFor(Product.class);
    private String fileName = "collection.csv";

    public FileManager() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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
            System.out.println("Коллекция загружена");
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты инициализации коллекции в файле, загрузка не удалась, создана новая коллекция");
        } catch (CsvReadException | JsonParseException e) {
            System.out.println("Структура CSV не распознана, загрузка не удалась, создана новая коллекция");
        } catch (InvalidFormatException e) {
            System.out.println("Неверный формат данных, загрузка не удалась, создана новая коллекция");
        } catch (DatabindException e) {
            System.out.println("Ошибка маппинга полей, загрузка не удалась, создана новая коллекция");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, загрузка не удалась, создана новая коллекция");
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав, загрузка не удалась, создана новая коллекция");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
            System.out.println("Создана новая коллекция");
        }
    }

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
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, сохранение не удалось");
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав, сохранение не удалось");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}
