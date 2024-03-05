package me.cosmodev;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin implements Listener {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initRecipes(){
        // Безформенный крафт
        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(NamespacedKey.fromString("Gaypidor"), new ItemStack(Material.LEATHER));
        shapelessRecipe.addIngredient(Material.ROTTEN_FLESH);

        getServer().addRecipe(shapelessRecipe);

        // Форменный крафт
        ShapedRecipe shapedRecipe = new ShapedRecipe(
                NamespacedKey.fromString("IdiotGayPorn"),
                new ItemStack(Material.IRON_CHESTPLATE)
        );

        shapedRecipe.shape(
                "I I",
                "III",
                "GGG"
        );

        shapedRecipe.setIngredient('I', Material.IRON_INGOT);
        shapedRecipe.setIngredient('G', Material.GOLD_INGOT);

        getServer().addRecipe(shapedRecipe);

        // Переплавка в печке!
        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(NamespacedKey.fromString("IdiotPorno"), new ItemStack(Material.COAL, 9),Material.DIAMOND, 100, 20);

        getServer().addRecipe(furnaceRecipe);
    }
}
