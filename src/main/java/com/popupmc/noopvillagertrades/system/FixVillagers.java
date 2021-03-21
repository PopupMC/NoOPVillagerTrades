package com.popupmc.noopvillagertrades.system;

import com.google.common.collect.Lists;
import com.popupmc.noopvillagertrades.NoOPVillagerTrades;
import com.popupmc.noopvillagertrades.lists.HighMoneyList;
import com.popupmc.noopvillagertrades.lists.MoneyList;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FixVillagers {
    public static void fix(Villager villager) {
        // Get recipes
        List<MerchantRecipe> recipes = Lists.newArrayList(villager.getRecipes());

        // Stop if empty recipes
        if(recipes.size() == 0)
            return;

        // Get Iterator & New fixed Recipes to re-add back in
        Iterator<MerchantRecipe> recipeIterator;
        ArrayList<MerchantRecipe> newRecipes = new ArrayList<>();

        // Loop through recipes
        for (recipeIterator = recipes.iterator(); recipeIterator.hasNext(); ) {

            // Get recipe
            MerchantRecipe recipe = recipeIterator.next();

            // Get Ingridients and stop if none
            List<ItemStack> ings = recipe.getIngredients();
            if (ings.size() == 0)
                continue;

            // Get first recipe
            ItemStack ing1 = ings.get(0);

            // Skip if null or is air
            if(ing1 == null || ing1.getType().isAir())
                continue;

            ItemStack ing2 = null;
            boolean has2ndIng = false;

            // Get second recipe
            if (ings.size() > 1) {
                ing2 = ings.get(1);
                has2ndIng = ing2 != null && !ing2.getType().isAir();
            }

            // Get Result
            ItemStack result = recipe.getResult();

            // Now check exp and randomize if none
            boolean expModified = false;
            int exp = recipe.getVillagerExperience();
            if(exp <= 0) {
                expModified = true;
                exp = NoOPVillagerTrades.random.nextInt(17) + 1;
            }
            recipe.setVillagerExperience(exp);

            // Get whether any of the ingridients are money
            boolean ingIsMoney;

            if(!has2ndIng)
                ingIsMoney = MoneyList.hashList.containsKey(ing1.getType());
            else
                ingIsMoney = MoneyList.hashList.containsKey(ing1.getType()) || MoneyList.hashList.containsKey(ing2.getType());

            // Get whether the result is money
            boolean resultIsmoney = MoneyList.hashList.containsKey(result.getType());

            // If both are money remove and continue
            if (ingIsMoney && resultIsmoney) {
                recipeIterator.remove();
                continue;
            }

            /////////////////////////////////////////////////////

            // Now one of them may be money but not both

            boolean changedRecipe = false;

            if(HighMoneyList.hashList.containsKey(ing1.getType())) {
                ing1.setType(Material.GOLD_INGOT);
                changedRecipe = true;
            }

            if(has2ndIng && HighMoneyList.hashList.containsKey(ing2.getType())) {
                ing2.setType(Material.GOLD_INGOT);
                changedRecipe = true;
            }

            if(HighMoneyList.hashList.containsKey(result.getType())) {
                result.setType(Material.GOLD_INGOT);
                changedRecipe = true;
            }

            /////////////////////////////////////////////////////

            // If any contained money, we need to re-add in the recipe

            if(changedRecipe || expModified) {
                MerchantRecipe newRecipe = new MerchantRecipe(result, recipe.getMaxUses());
                newRecipe.addIngredient(ing1);
                if(has2ndIng)
                    newRecipe.addIngredient(ing2);

                newRecipe.setVillagerExperience(recipe.getVillagerExperience());
                newRecipe.setMaxUses(recipe.getMaxUses());
                newRecipe.setExperienceReward(recipe.hasExperienceReward());
                newRecipe.setIgnoreDiscounts(false);
                newRecipe.setPriceMultiplier(recipe.getPriceMultiplier());
                newRecipe.setUses(recipe.getUses());

                recipeIterator.remove();
                newRecipes.add(newRecipe);
            }
        }

        if(newRecipes.size() > 0)
            recipes.addAll(newRecipes);

        // Re-Update Recipes
        villager.setRecipes(recipes);
    }
}
