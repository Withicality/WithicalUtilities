package withicality.withicalapi;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import withicality.withicalapi.command.WithicalCommand;

import java.lang.reflect.Field;


public class APIManager {
    public static void registerCommand(WithicalCommand command, JavaPlugin plugin, String identifier) {
        try {
            Field commandField = plugin.getServer().getClass().getDeclaredField("commandMap");
            commandField.setAccessible(true);

            CommandMap commandMap = (CommandMap) commandField.get(plugin.getServer());
            commandMap.register(identifier, command);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerCommand(WithicalCommand command, JavaPlugin plugin) {
        registerCommand(command, plugin, command.getName());
    }
}