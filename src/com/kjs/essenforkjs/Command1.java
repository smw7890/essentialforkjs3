package com.kjs.essenforkjs;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//��ȭ Ŀ�ǵ� 1 ������

public class Command1 implements CommandExecutor {
	
    public int getrannum() //���� �߻� �Լ�
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
			
	    if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) //���̾Ƹ�� Į�� �����տ� ��� �ִ��� üũ
	   {
	    	//���� ��� �ִٸ� ���� �ܰ��...
	    	int nowent = 0; //���� ��ȭ�ܰ踦 �����س��� ���� ���� ����
	    	
	        nowent = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
	        player.sendMessage("���� ��ȭ �ܰ�� +" + nowent + "�Դϴ�.");
	        player.sendMessage("��ȭ �õ��� �ʿ��� ���̾ƴ� " + ((nowent + 1) * 2) + "���Դϴ�.");
	        
	        if(player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), ((nowent + 1) * 2)))
	        		{
	        	player.sendMessage("+" + (nowent + 1) + "���� ��ȭ�� �õ��մϴ�.");
	        	player.sendMessage("Ȯ��: " + getpsb(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)) + "%");
	        	player.getInventory().removeItem(new ItemStack(Material.DIAMOND, ((nowent + 1) * 2)));
	        	if(getrannum() < getpsb(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL)))
	        	{
	        		player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, (nowent + 1));
	        		
	        		player.sendMessage("��ȭ�� �����ϼ̽��ϴ�!");
	        	}
	        	else{
	        		
	        		player.sendMessage("��ȭ�� �����ϼ̽��ϴ�.");
	        	}
	        		}
	        else {
	        	player.sendMessage("��ȭ �õ��� �ʿ��� ���̾ư� �����մϴ�.");
	        }
	    
	   }
	    else{ //���� ���̾Ƹ�� ���� �����տ� ��� ���� �ʴٸ�...
	    	 player.sendMessage("�տ� ���̾Ƹ�� ���� ��� �ٽ� �õ����ּ���."); //�޽����� �Բ� �Լ� Ż��
	    }
		
	return true;
    }
	
}
