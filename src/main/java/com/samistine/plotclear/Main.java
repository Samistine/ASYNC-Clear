package com.samistine.plotclear;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.worldcretornica.plotme_core.PlotMe_Core;
import com.worldcretornica.plotme_core.bukkit.PlotMe_CorePlugin;

public class Main extends JavaPlugin implements Listener {
	//static String randpass = PassGenerator.main(null);

	
	private PlotMe_Core plotmeAPI;
    private YamlConfiguration yamlFile;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new PlotClear(this), this);
		
        Plugin plotMe = getServer().getPluginManager().getPlugin("PlotMe");
        if (plotMe == null) {
            getLogger().severe("Cannot find PlotMe!");
            return;
        }
        PlotMe_CorePlugin plotme = (PlotMe_CorePlugin) plotMe;
        plotmeAPI = plotme.getAPI();
        
        File f = new File(getDataFolder().getParent() + "/PlotMe/PlotMe-DefaultGenerator/config.yml");
        yamlFile = YamlConfiguration.loadConfiguration(f);
	}
	
	public PlotMe_Core getplotmeAPI() {
		return plotmeAPI;
	}
	
	public YamlConfiguration getYamlFile() {
		return yamlFile;
	}

//	public static String getRandomPassword() {
//		return randpass;
//	}

	// public class PlotClear implements Listener {
	// @EventHandler(ignoreCancelled = true)
	
}