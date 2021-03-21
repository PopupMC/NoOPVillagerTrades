package com.popupmc.noopvillagertrades.events;

import com.popupmc.noopvillagertrades.system.NoFortune;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        NoFortune.stopLootingOnOres(event);
    }
}
