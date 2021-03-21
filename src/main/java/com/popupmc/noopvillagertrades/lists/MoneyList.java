package com.popupmc.noopvillagertrades.lists;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class MoneyList {
    public static void setup() {
        for(Material type : list) {
            hashList.put(type, true);
        }
    }

    public static final Material[] list = {
            Material.IRON_BLOCK,
            Material.IRON_INGOT,
            Material.IRON_NUGGET,
            Material.IRON_ORE,

            Material.LAPIS_BLOCK,
            Material.LAPIS_LAZULI,
            Material.LAPIS_ORE,

            Material.GOLD_BLOCK,
            Material.GOLD_INGOT,
            Material.GOLD_NUGGET,
            Material.GOLD_ORE,

            Material.REDSTONE,
            Material.REDSTONE_BLOCK,
            Material.REDSTONE_ORE,
            Material.REDSTONE_WIRE,

            Material.DIAMOND,
            Material.DIAMOND_BLOCK,
            Material.DIAMOND_ORE,

            Material.EMERALD,
            Material.EMERALD_BLOCK,
            Material.EMERALD_ORE
    };

    public static final HashMap<Material,Boolean> hashList = new HashMap<>();
}
