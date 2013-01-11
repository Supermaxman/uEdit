package com.xegaming.uedit.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.xegaming.uedit.Util;
import com.xegaming.uedit.uEdit;

public class ExpandCommand {
	
	public static void expand(Player p, String[] args){

        if (args.length == 3) {
            BlockFace side;
            try {
                side = BlockFace.valueOf(args[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                Util.sendMessage(p, ChatColor.RED + "That is not a Direction.");
                return;
            }
            int amt;
            try {
                amt = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                Util.sendMessage(p, ChatColor.RED + "That is not a Number.");
                return;
            }
            if (!uEdit.rloc.containsKey(p.getName()) && !uEdit.lloc.containsKey(p.getName())) {
                Util.sendMessage(p, ChatColor.RED + "You have not made an area selection yet.");
                return;
            }
            final Location rl = uEdit.rloc.get(p.getName());
            final Location ll = uEdit.lloc.get(p.getName());
            Vector v = rl.toVector().subtract(p.getLocation().toVector());
            Vector v2 = ll.toVector().subtract(p.getLocation().toVector());
            /*
             * -x = north
             * +x = south
             * +z = west
             * -z = east
             */


            if (side == BlockFace.UP) {
                if (v.getY() > v2.getY()) {
                    if (rl.getY() + amt >= 255) {
                        uEdit.rloc.put(p.getName(), new Location(rl.getWorld(), rl.getBlock().getX(), 254, rl.getBlock().getZ()));
                    } else {
                        uEdit.rloc.put(p.getName(), rl.getBlock().getRelative(side, amt).getLocation());
                    }
                } else {
                    if (ll.getY() + amt >= 255) {
                        uEdit.lloc.put(p.getName(), new Location(ll.getWorld(), ll.getBlock().getX(), 254, ll.getBlock().getZ()));
                    } else {
                        uEdit.lloc.put(p.getName(), ll.getBlock().getRelative(side, amt).getLocation());
                    }
                }
            } else if (side == BlockFace.DOWN) {
                if (v.getY() < v2.getY()) {
                    if (rl.getY() - amt <= 0) {
                        uEdit.rloc.put(p.getName(), new Location(rl.getWorld(), rl.getBlock().getX(), 0, rl.getBlock().getZ()));
                    } else {
                        uEdit.rloc.put(p.getName(), rl.getBlock().getRelative(side, amt).getLocation());
                    }
                } else {
                    if (ll.getY() - amt <= 0) {
                        uEdit.lloc.put(p.getName(), new Location(ll.getWorld(), ll.getBlock().getX(), 0, ll.getBlock().getZ()));
                    } else {
                        uEdit.lloc.put(p.getName(), ll.getBlock().getRelative(side, amt).getLocation());
                    }
                }
            } else if (side == BlockFace.NORTH) {
                if (v.getX() < v2.getX()) {
                    uEdit.rloc.put(p.getName(), rl.getBlock().getRelative(side, amt).getLocation());
                } else {
                    uEdit.lloc.put(p.getName(), ll.getBlock().getRelative(side, amt).getLocation());
                }
            } else if (side == BlockFace.SOUTH) {
                if (v.getX() > v2.getX()) {
                    uEdit.rloc.put(p.getName(), rl.getBlock().getRelative(side, amt).getLocation());
                } else {
                    uEdit.lloc.put(p.getName(), ll.getBlock().getRelative(side, amt).getLocation());
                }
            } else if (side == BlockFace.EAST) {
                if (v.getZ() < v2.getZ()) {
                    uEdit.rloc.put(p.getName(), rl.getBlock().getRelative(side, amt).getLocation());
                } else {
                	uEdit.lloc.put(p.getName(), ll.getBlock().getRelative(side, amt).getLocation());
                }
            } else if (side == BlockFace.WEST) {
                if (v.getZ() > v2.getZ()) {
                    uEdit.rloc.put(p.getName(), rl.getBlock().getRelative(side, amt).getLocation());
                } else {
                    uEdit.lloc.put(p.getName(), ll.getBlock().getRelative(side, amt).getLocation());
                }
            } else {
                Util.sendMessage(p, ChatColor.RED + "Direction Not Allowed.");
                return;
            }

            Util.sendMessage(p, ChatColor.GREEN + "Expanded " + amt + " " + side.toString().toLowerCase() + ".");

        } else if (args.length == 2) {
        	//TODO direction expand
        } else {
            Util.sendMessage(p, ChatColor.RED + "Incorrect Syntax.");
        }
	}
	
}
