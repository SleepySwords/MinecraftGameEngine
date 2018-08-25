package me.spirafy.engine.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {

    private String cmdName;
    private Map<String, CommandConsumer> commands = new HashMap<>();
    private CommandConsumer mainCommand;

    public CommandManager(String cmdName, JavaPlugin plugin){
        this.cmdName = cmdName;
        plugin.getCommand(cmdName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!command.getName().equalsIgnoreCase(cmdName)) return true;
        if (strings.length > 0) {
            if (commands.containsKey(strings[0].toLowerCase())) {
                commands.get(strings[0]).accept(commandSender, command, s, strings);
            }
        }else {
            mainCommand.accept(commandSender, command, s, strings);
        }
        return true;
    }

    public void registerSubCommand(String subCommand, CommandConsumer consumer) {
        commands.put(subCommand.toLowerCase(), consumer);
    }

    public void regsterMainCommad(CommandConsumer consumer){
        mainCommand = consumer;
    }
}
