package commands.di;

import core.CommandRegistry;

/**
 * Интерфейс для команд, которым нужен доступ к {@link core.CommandManager}.
 * Внедряется в {@link core.CommandManager}
 */
public interface CommandManagerDependant {
    void setCommandManager(CommandRegistry commandManager);
}
