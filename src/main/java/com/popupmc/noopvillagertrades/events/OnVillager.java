package com.popupmc.noopvillagertrades.events;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import com.popupmc.noopvillagertrades.system.FixVillagers;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class OnVillager implements Listener {
    // Prevent any villager from giving an offer that involves 2 money
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityAddToWorldEvent(EntityAddToWorldEvent event) {
        if (!(event.getEntity() instanceof Villager))
            return;

        if (event.getEntity().getWorld().getName().equalsIgnoreCase("imperial_city"))
            return;

        FixVillagers.fix((Villager) event.getEntity());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onVillagerInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof Villager))
            return;

        if (event.getRightClicked().getWorld().getName().equalsIgnoreCase("imperial_city"))
            return;

        FixVillagers.fix((Villager) event.getRightClicked());
    }
}
