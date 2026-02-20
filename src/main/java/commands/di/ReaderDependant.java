package commands.di;

import io.UserInput;

/**
 * Интерфейс для команд, которым нужен доступ к {@link io.ConsoleReader}.
 * Внедряется в {@link core.CommandManager}
 */
public interface ReaderDependant {
    void setReader(UserInput reader);
}
