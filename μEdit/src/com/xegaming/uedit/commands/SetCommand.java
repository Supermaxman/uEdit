package com.xegaming.uedit.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.xegaming.uedit.SetCommandThread;
import com.xegaming.uedit.Util;
import com.xegaming.uedit.uEdit;

public class SetCommand {

	
	public static void set(Player p, String[] args){
        final Location rl = uEdit.rloc.get(p.getName());
        final Location ll = uEdit.lloc.get(p.getName());

        if ((rl == null) || (ll == null)) {
            Util.sendMessage(p, ChatColor.RED + "Locations not set.");
            return;
        }
        if (args.length != 2) {
            Util.sendMessage(p, ChatColor.RED + "Arguement Error.");
            return;
        }
        
        Material m;
        try {
            m = Material.getMaterial(Integer.parseInt(args[1]));
        } catch (Exception e) {
            String s = args[1].toUpperCase();
            m = Material.getMaterial(s);
        }
        
        
        if (m == null) {
            Util.sendMessage(p, ChatColor.RED + "Arguement Error.");
            return;
        }
        if (!m.isBlock()) {
            Util.sendMessage(p, ChatColor.RED + "Cannot Set This Material.");
            return;
        }
        if ((m == Material.LAVA) || (m == Material.WATER) || (m == Material.STATIONARY_WATER || (m == Material.STATIONARY_LAVA))) {
            Util.sendMessage(p, ChatColor.RED + "Water and Lava Not Allowed.");
            return;
        }
        if ((m == Material.GLASS)) {
            Util.sendMessage(p, ChatColor.RED + "Glass Not Allowed.");
            return;
        }
        Util.sendMessage(p, ChatColor.GREEN + "Edit queued.");

        new SetCommandThread(uEdit.plugin, p, ll, rl, p.getWorld(), m);
        
        return;
	}
}
