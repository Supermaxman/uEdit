package com.xegaming.uedit.commands;

import org.bukkit.entity.Player;

import com.xegaming.uedit.Util;

public class CountCommand {

	public static void count(Player p, String[] args){
		Util.sendMessage(p, Util.findVolume(p)+" Block(s) Within Selected Area.");
	}
}
