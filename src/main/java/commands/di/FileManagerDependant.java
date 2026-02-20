package commands.di;

import io.FileStorage;

public interface FileManagerDependant {
    void setFileManager(FileStorage fileManager);
}
