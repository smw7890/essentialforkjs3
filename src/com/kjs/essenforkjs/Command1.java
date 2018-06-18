package com.kjs.essenforkjs;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//강화 커맨드 1 리스너

public class Command1 implements CommandExecutor {
	
    public int getrannum() //난수 발생 함수
    {
    	double firstrandom = 0;
    	int finalrandom = 0;
    	
    	firstrandom = Math.random();
    	finalrandom = (int)(firstrandom * 100) + 1;
    	
    	return finalrandom;
    }
    
    public int getpsb(int entlev)
    {
    	if(entlev < 4)
    	{
    		return 95;	
    	}
    	    	
    	if(entlev > 3 && entlev < 9)
    	{
    		return 85 - (10 * (entlev - 4));
    	}
    	
    	if(entlev == 9)
    	{
    	return 25;	
    	}
    	
    	if(entlev > 9)
    	{
    	return 5;	
    	}
    	
    	return 999;
    	
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
			
	    if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) //다이아몬드 칼을 오른손에 쥐고 있는지 체크
	   {
	    	//만약 쥐고 있다면 다음 단계로...
	    	int nowent = 0; //검의 강화단계를 저장해놓기 위한 변수 선언
	    	
	        nowent = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
	        player.sendMessage("현재 강화 단계는 +" + nowent + "입니다.");
	        player.sendMessage("강화 시도에 필요한 다이아는 " + ((nowent + 1) * 2) + "개입니다.");
	        
	        if(player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), ((nowent + 1) * 2)))
	        		{
	        	player.sendMessage("+" + (nowent + 1) + "로의 강화를 시도합니다.");
	        	player.sendMessage("확률: " + getpsb(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)) + "%");
	        	player.getInventory().removeItem(new ItemStack(Material.DIAMOND, ((nowent + 1) * 2)));
	        	if(getrannum() < getpsb(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)))
	        	{
	        		player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, (nowent + 1));
	        		
	        		player.sendMessage("강화에 성공하셨습니다!");
	        	}
	        	else{
	        		
	        		player.sendMessage("강화에 실패하셨습니다.");
	        	}
	        		}
	        else {
	        	player.sendMessage("강화 시도에 필요한 다이아가 부족합니다.");
	        }
	    
	   }
	    else{ //만약 다이아몬드 검을 오른손에 쥐고 있지 않다면...
	    	 player.sendMessage("손에 다이아몬드 검을 들고 다시 시도해주세요."); //메시지와 함께 함수 탈출
	    }
		
	return true;
    }
	
}
