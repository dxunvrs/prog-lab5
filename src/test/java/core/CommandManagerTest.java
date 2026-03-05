package core;

import commands.AddCommand;
import commands.Command;
import commands.RemoveCommand;
import io.FileManager;
import io.InputReader;
import models.Product;
import models.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utility.ExecutionResponse;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommandManagerTest {
    private CommandManager commandManager;

    @Mock
    private InputReader mockReader;

    @Mock
    private CollectionManager mockCollection;

    @Mock
    private FileManager mockFileManager;

    @BeforeEach
    void setUp() {
        commandManager = new CommandManager(mockCollection, mockReader, mockFileManager);
    }

    @Test
    @DisplayName("Регистрация и выполнение команды")
    void registrationCommands() {
        Command mockCommand = mock(Command.class);
        when(mockCommand.getName()).thenReturn("test");
        when(mockCommand.execute(any())).thenReturn(new ExecutionResponse("message", false));

        commandManager.addCommand(mockCommand);
        commandManager.executeCommand(mockCommand.getName());
        verify(mockCommand, times(1)).execute(any());
    }

    @Test
    @DisplayName("Корректность сборки объекта командой Add")
    void addCommand() {
        commandManager.addCommand(new AddCommand());
        when(mockReader.readNextLine(any())).thenReturn("Name", "100", "101", "102", "PCS", "name", "2001-01-01", "103");

        commandManager.executeCommand("add");

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(mockCollection).addProduct(productCaptor.capture());
        Product product = productCaptor.getValue();
        assertEquals("Name", product.getName());
        assertEquals(100, product.getCoordinates().x());
        assertEquals(101, product.getCoordinates().y());
        assertEquals(102, product.getPrice());
        assertEquals(UnitOfMeasure.PCS, product.getUnitOfMeasure());
        assertEquals("name", product.getOwner().name());
        assertEquals(LocalDate.parse("2001-01-01"), product.getOwner().birthday());
        assertEquals(103, product.getOwner().height());
    }

    @Test
    @DisplayName("Корректность выполнения команды без ожидаемых аргументов")
    void executeWithEmptyArgs() {
        commandManager.addCommand(new RemoveCommand());
        commandManager.executeCommand("remove_by_id");

        verifyNoInteractions(mockCollection);
    }
}
