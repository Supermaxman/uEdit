package com.xegaming.uedit;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.xegaming.uedit.commands.BaseCommandExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class uEdit extends JavaPlugin implements Listener {

    //Required
    public static final Map<String, Location> rloc = new HashMap<String, Location>();
    public static final Map<String, Location> lloc = new HashMap<String, Location>();
    public BlockQueue bq;
    public UndoThread ut;

    public static Logger log;
    public static uEdit plugin;
    
    @Override
    public void onDisable() {
        log.info("Shutting down blockqueue and undothread, please wait.");
        bq.shouldrun = false;
        while (bq.isAlive()) {
            //wait
        }
        ut.canrun = false;
        while (ut.isAlive()) {
            //wait
        }
        log.info("uEdit Disabled.");
    }

    @Override
    public void onEnable() {
    	uEdit.plugin = this;
        log = getLogger();
        getServer().getPluginManager().registerEvents(new uEdit(), this);
        bq = new BlockQueue(this);
        bq.start();
        ut = new UndoThread(this);
        ut.start();
        log.info("uEdit enabled!");
        getCommand("//").setExecutor(new BaseCommandExecutor(this));
    }
    
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            if ((player.getItemInHand().getType() == Material.GOLD_AXE) && (player.isOp())) {
                Location loc = event.getClickedBlock().getLocation();
                rloc.put(player.getName(), loc);
                player.sendMessage(ChatColor.AQUA + "[WorldThredit] " + ChatColor.GREEN + "Location 2 Placed.");
                event.setCancelled(true);
            }

        } else if (action == Action.LEFT_CLICK_BLOCK) {
            if ((player.getItemInHand().getType() == Material.GOLD_AXE) && (player.isOp())) {
                Location loc = event.getClickedBlock().getLocation();
                lloc.put(player.getName(), loc);
                player.sendMessage(ChatColor.AQUA + "[WorldThredit] " + ChatColor.GREEN + "Location 1 Placed.");
                event.setCancelled(true);
            }

        }
    }


}