package com.kjs.essenforkjs;
import java.text.DecimalFormat;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.attribute.Attribute;
//import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

//������ �˸� �̺�Ʈ ������

public class Listener1 implements Listener
{

	//@SuppressWarnings("deprecation")
	public String drawbar(LivingEntity c, double dmg)
	{
		String tray = "";
		//double nowhealth = c.getHealth();
		double finalhealth = c.getHealth() - dmg;
		
		//double times = 0;
		double times = Math.round(finalhealth / (c.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) * 10);
		
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
		tray = tray + "��";
		}
		
		tray = tray + ChatColor.WHITE;
		
		if(times<10)
		{
			
			for(int j=1;j<(10-times) + 1;j++)
			{
				tray = tray + "��";
			}
			
		}
		
		
		return tray;
		
	}
	//onEntityDamageByEntity, EntityDamageByEntityEvent
    @EventHandler
    public void onEntityDamage(EntityDamageEvent ett)
    {
    	//Entity from = ett.getDamager();
    	
    	if(!(ett.getEntity() instanceof LivingEntity))
    	{
    		return;
    	}
    	
    	LivingEntity to = (LivingEntity) ett.getEntity();
    	DecimalFormat df = new DecimalFormat("0.#");
    	
    	if(ett.getFinalDamage() >= to.getHealth())
    	{
    		ett.getEntity().setCustomName("");
    		ett.getEntity().setCustomName(to.getName() + " [�����������] 0/" + to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    		ett.getEntity().setCustomNameVisible(true);
    		//from.sendMessage();
    		return;
    	}
    	
    	String finalhealth2 = drawbar(to, ett.getFinalDamage());
    	//from.sendMessage(finalhealth2 + " " + (to.getHealth() - ett.getDamage()) + "/" + to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    	ett.getEntity().setCustomName("");
		ett.getEntity().setCustomName(to.getName() + " [" + finalhealth2 + "] " + df.format(to.getHealth() - ett.getFinalDamage()) + "/" + to.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		ett.getEntity().setCustomNameVisible(true);
		
    	//from.sendMessage("");
    	
    	//String finalheal = drawbar((LivingEntity) to);
    	
    	//ett.getEntity().setCustomName("Touched");
    	//ett.getEntity().setCustomNameVisible(true);
    	
    	//from.sendMessage("Ÿ��: " + to.getName() + ", " + "������: " + ett.getDamage());
    }
    
}