package commands.di;

import core.CollectionRepository;

/**
 * Интерфейс для команд, которым нужен доступ к {@link core.CollectionManager}.
 * Внедряется в {@link core.CommandManager}
 */
public interface CollectionManagerDependant {
    void setCollectionManager(CollectionRepository collectionManager);
}
