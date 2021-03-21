package com.popupmc.noopvillagertrades.lists;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class HighMoneyList {
    public static void setup() {
        for(Material type : list) {
            hashList.put(type, true);
        }
    }

    public static final Material[] list = {
            Material.GOLD_BLOCK,
            Material.GOLD_ORE,

            Material.DIAMOND,
            Material.DIAMOND_BLOCK,
            Material.DIAMOND_ORE,

            Material.EMERALD,
            Material.EMERALD_BLOCK,
            Material.EMERALD_ORE
    };

    public static final HashMap<Material,Boolean> hashList = new HashMap<>();
}
