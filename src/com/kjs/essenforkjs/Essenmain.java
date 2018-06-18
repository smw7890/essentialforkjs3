package com.kjs.essenforkjs;
import org.bukkit.plugin.java.JavaPlugin;

public class Essenmain extends JavaPlugin {
	
    @Override
    public void onEnable() {
    	this.getCommand("reinf").setExecutor(new Command1());
    	getServer().getPluginManager().registerEvents(new Listener1(), this);
    	getServer().getPluginManager().registerEvents(new Listener2(), this);
    }

    @Override
    public void onDisable() {

    }
}
