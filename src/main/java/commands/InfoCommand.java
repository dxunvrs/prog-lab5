package commands;

import core.CollectionManager;
import utility.ExecutionResponse;

import java.time.format.DateTimeFormatter;

/**
 * Команда для получения информации о коллекции (тип, дата создания, размер)
 */
public class InfoCommand extends Command {
    @Inject
    private CollectionManager collectionManager;

    public InfoCommand() {
        super("info", "info - информация о коллекции", 0);
    }

    @Override
    public ExecutionResponse execute(String[] tokens) {
        return new ExecutionResponse(collectionManager.getCollectionInfo(), false);
    }
}
