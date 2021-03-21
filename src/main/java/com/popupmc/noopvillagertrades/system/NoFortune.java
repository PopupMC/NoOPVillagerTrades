package com.popupmc.noopvillagertrades.system;

import com.popupmc.noopvillagertrades.NoOPVillagerTrades;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class NoFortune {
    public static void stopLootingOnOres(BlockBreakEvent event) {
        Material material = event.getBlock().getType();

        if(material != Material.DIAMOND_ORE &&
                material != Material.EMERALD_ORE &&
                material != Material.NETHER_GOLD_ORE &&
                material != Material.NETHER_QUARTZ_ORE &&
                material != Material.LAPIS_ORE &&
                material != Material.REDSTONE_ORE)
            return;

        if(material == Material.EMERALD_ORE ||
                material == Material.DIAMOND_ORE ||
                material == Material.NETHER_GOLD_ORE) {
            if (NoOPVillagerTrades.random.nextInt(100 + 1) >= 25)
                return;
        }
        else {
            if (NoOPVillagerTrades.random.nextInt(100 + 1) >= 50)
                return;
        }

        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
        if(tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH))
            return;

        if(NoOPVillagerTrades.random.nextInt(100 + 1) <= 15) {
            event.getBlock().setType(Material.AIR);
            return;
        }

        event.getBlock().setType(Material.AIR);

        if(material == Material.DIAMOND_ORE) {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(), new ItemStack(Material.DIAMOND, 1));
        }
        else if(material == Material.EMERALD_ORE) {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(), new ItemStack(Material.EMERALD, 1));
        }
        else if(material == Material.NETHER_GOLD_ORE) {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(), new ItemStack(Material.GOLD_NUGGET, NoOPVillagerTrades.random.nextInt(4 + 1) + 2));
        }
        else if(material == Material.NETHER_QUARTZ_ORE) {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(), new ItemStack(Material.QUARTZ, 1));
        }
        else if(material == Material.LAPIS_ORE) {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(), new ItemStack(Material.LAPIS_LAZULI, NoOPVillagerTrades.random.nextInt(5 + 1) + 4));
        }
        else {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(), new ItemStack(Material.REDSTONE, NoOPVillagerTrades.random.nextInt(1 + 1) + 4));
        }
    }
}
