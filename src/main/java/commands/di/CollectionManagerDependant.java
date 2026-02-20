package commands.di;

import core.CollectionRepository;

public interface CollectionManagerDependant {
    void setCollectionManager(CollectionRepository collectionManager);
}
