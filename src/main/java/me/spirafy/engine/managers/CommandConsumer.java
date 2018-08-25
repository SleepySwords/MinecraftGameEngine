package me.spirafy.engine.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CommandConsumer {
    void accept(CommandSender commandSender, Command command, String s, String[] strings);
}
