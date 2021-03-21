package com.popupmc.noopvillagertrades;

import com.popupmc.noopvillagertrades.events.OnBlockBreak;
import com.popupmc.noopvillagertrades.events.OnVillager;
import com.popupmc.noopvillagertrades.lists.HighMoneyList;
import com.popupmc.noopvillagertrades.lists.MoneyList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class NoOPVillagerTrades extends JavaPlugin {
    @Override
    public void onEnable() {
        MoneyList.setup();
        HighMoneyList.setup();

        Bukkit.getPluginManager().registerEvents(new OnBlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new OnVillager(), this);

        getLogger().info("NoOPVillagerTrades is enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoOPVillagerTrades is disabled");
    }


    public static Random random = new Random();
}
