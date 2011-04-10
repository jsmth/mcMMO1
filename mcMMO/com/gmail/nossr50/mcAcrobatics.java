package com.gmail.nossr50;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;


public class mcAcrobatics {
	private static volatile mcAcrobatics instance;
	public static mcAcrobatics getInstance() {
    	if (instance == null) {
    	instance = new mcAcrobatics();
    	}
    	return instance;
    	}
	public void acrobaticsCheck(Player player, EntityDamageEvent event, Location loc, int xx, int y, int z){
    	if(player != null && mcPermissions.getInstance().acrobatics(player)){
    		int acrovar = mcUsers.getProfile(player).getAcrobaticsInt();
    		if(player.isSneaking())
    			acrovar = acrovar * 2;
			if(Math.random() * 1000 <= acrovar){
				int threshold = 7;
				if(player.isSneaking())
					threshold = 14;
				int newDamage = event.getDamage() - threshold;
				if(newDamage < 0)
					newDamage = 0;
				if(!event.isCancelled())
					mcUsers.getProfile(player).addAcrobaticsGather((event.getDamage() * 8) * mcLoadProperties.xpGainMultiplier);
				mcSkills.getInstance().XpCheck(player);
				event.setDamage(newDamage);
				if(event.getDamage() <= 0)
					event.setCancelled(true);
				if(player.isSneaking()){
					player.sendMessage(ChatColor.GREEN+"**GRACEFUL ROLL**");
				} else {
					player.sendMessage("**ROLL**");
				}
			} else if (!mcConfig.getInstance().isBlockWatched(loc.getWorld().getBlockAt(xx, y, z)) && !event.isCancelled()){
				mcUsers.getProfile(player).addAcrobaticsGather((event.getDamage() * 12) * mcLoadProperties.xpGainMultiplier);
				mcSkills.getInstance().XpCheck(player);
			}
    	}
    }
	
}