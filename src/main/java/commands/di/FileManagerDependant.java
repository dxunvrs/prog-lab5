package commands.di;

import io.FileStorage;

/**
 * Интерфейс для команд, которым нужен доступ к {@link io.FileManager}.
 * Внедряется в {@link core.CommandManager}
 */
public interface FileManagerDependant {
    void setFileManager(FileStorage fileManager);
}
