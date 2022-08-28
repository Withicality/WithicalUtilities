package withicality.withicalutilities.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class WithicalCommand extends Command {
    protected WithicalCommand(String name, String description, String usageMessage, List<String> aliases, String permission) {
        super(name, description, usageMessage, aliases);
        setPermission(permission);
        setPermissionMessage("Unknown command. Type \"/help\" for help.");
    }

    protected WithicalCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    protected WithicalCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        //making sure
        if (getPermission() != null && !getPermission().isEmpty() && !sender.hasPermission(getPermission())) {
            sender.sendMessage("Unknown command. Type \"/help\" for help.");
            return true;
        }
        run(sender, args);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (getPermission() != null && !getPermission().isEmpty() && !sender.hasPermission(getPermission())) {
            return null;
        }
        List<String> list = tab(sender, args);
        if (list == null) return null;
        List<String> completions = new ArrayList<>();
           for (String x : list) {
            if (x.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) completions.add(x);
        }
        return completions;
    }

    public abstract void run(CommandSender sender, String[] args);
    public List<String> tab(CommandSender sender, String[] args) {
        return null;
    }
}