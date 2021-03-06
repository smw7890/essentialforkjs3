package com.kjs.essenforkjs;
import java.text.DecimalFormat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

//엔티티 회복시 리스너

public class Listener2 implements Listener {
	
	public String revdrawbar(LivingEntity g, double dmg2)
	{
		String tray = "";
		double finalhealth = g.getHealth() + dmg2;
		double times = Math.round(finalhealth / (g.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 10);
		
		if(times>6)
		{
			tray = tray + ChatColor.AQUA;
		}
		
		if(times<7 && times>3)
		{
			tray = tray + ChatColor.YELLOW;
		}
		
		if(times<4)
		{
			tray = tray + ChatColor.RED;
		}
		
		for(int i=1;i<times+1;i++)
		{
		tray = tray + "■";
		}
		
		tray = tray + ChatColor.WHITE;
		
		if(times<10)
		{
			
			for(int j=1;j<(10-times) + 1;j++)
			{
				tray = tray + "□";
			}
			
		}
		
		
		return tray;
		
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent ett)
    {
		
    	LivingEntity to = (LivingEntity) ett.getEntity();
    	DecimalFormat df = new DecimalFormat("0.#");
    	
    	String finalhealth2 = revdrawbar(to, ett.getAmount());
    	ett.getEntity().setCustomName("");
    	
    	if(to.getHealth() + ett.getAmount() > to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())
    	{
    		ett.getEntity().setCustomName(to.getName() + " [" + finalhealth2 + "] " + to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + "/" + to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    		ett.getEntity().setCustomNameVisible(true);
    		return;
    	}
    	
		ett.getEntity().setCustomName(to.getName() + " [" + finalhealth2 + "] " + df.format(to.getHealth() + ett.getAmount()) + "/" + to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		ett.getEntity().setCustomNameVisible(true);
    }

}
