package commands.di;

import core.CommandRegistry;

public interface CommandManagerDependant {
    void setCommandManager(CommandRegistry commandManager);
}
