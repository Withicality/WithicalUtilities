package withicality.withicalutilities;

import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import withicality.withicalutilities.command.WithicalCommand;

import java.lang.reflect.Field;


public class APIManager {
    private static boolean PAPER = false;
    public static boolean isOnPaper() { return PAPER; }
    protected static void paper() { PAPER = true; }
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