package io;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.CollectionRepository;
import models.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

public class FileManager implements FileStorage {
    private final CsvMapper mapper = new CsvMapper();
    private final CsvSchema schema = mapper.schemaFor(Product.class).withHeader();

    public FileManager() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void load(CollectionRepository collectionManager, String fileName) {
        StringBuilder sb = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (scanner.hasNextLine()) {
                collectionManager.setDateOfInit(LocalDateTime.parse(scanner.nextLine()));
            }
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }

            MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema).readValues(sb.toString());

            while (iterator.hasNext()) {
                collectionManager.addElement(iterator.next());
            }
            System.out.println("Коллекция загружена");
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты в файле, загрузка не удалась, создана новая коллекция");
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
    public void save(Iterator<Product> iterator, LocalDateTime dateOfInit, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));
        SequenceWriter sequenceWriter = mapper.writer(schema).writeValues(writer)) {
            writer.write(dateOfInit.toString());
            writer.newLine();
            while (iterator.hasNext()) {
                sequenceWriter.write(iterator.next());
            }
            System.out.println("Успешно сохранено");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, сохранение не удалось");
        } catch (SecurityException e) {
            System.out.println("Недостаточно прав, сохранение не удалось");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}
