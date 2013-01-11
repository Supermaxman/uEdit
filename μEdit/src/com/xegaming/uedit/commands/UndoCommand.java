package com.xegaming.uedit.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.xegaming.uedit.UndoThread;
import com.xegaming.uedit.Util;

public class UndoCommand {
	
	public static void undo(Player p, String[] args){
		//UndoThread.undo(p);
		Util.sendMessage(p, ChatColor.RED+"Under Development");
	}
}
