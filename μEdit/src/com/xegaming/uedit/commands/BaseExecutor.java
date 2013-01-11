package com.xegaming.uedit.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xegaming.uedit.uEdit;

abstract class BaseExecutor implements CommandExecutor {
    @SuppressWarnings("unused")
    private static uEdit plugin;
    // Permission permission = xEssentials.permission;

    BaseExecutor(uEdit plugin) {
        BaseExecutor.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        final boolean isPlayer = (sender instanceof Player);

        if (isPlayer) {
            player = (Player) sender;
        } else {
            return false;
        }

        this.run(player, args);

        return true;
    }

    protected abstract void run(Player player, String[] args);

}