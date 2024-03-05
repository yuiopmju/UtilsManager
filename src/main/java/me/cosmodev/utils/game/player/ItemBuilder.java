package me.cosmodev.utils.game.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta meta;

    public ItemBuilder(Material material, int amount){
        itemStack = new ItemStack(material);
        meta = itemStack.getItemMeta();
        itemStack.setAmount(amount);
    }

    public ItemBuilder setName(String value){
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', value));

        return this;
    }

    public ItemBuilder setLore(List<String> value){
        for (int i = 0; i < value.size(); i++){
            value.set(i, ChatColor.translateAlternateColorCodes('&', value.get(i)));
        }
        meta.setLore(value);

        return this;
    }

    public ItemBuilder addPersistent(String key, PersistentDataType persistentDataType, Object value){
        meta.getPersistentDataContainer().set(NamespacedKey.fromString(key), persistentDataType, value);

        return this;
    }

    public ItemBuilder removePersistent(String key){
        meta.getPersistentDataContainer().remove(NamespacedKey.fromString(key));

        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        meta.addEnchant(enchantment, level, true);

        return this;
    }
    public ItemBuilder removeEnchant(Enchantment enchantment){
        meta.removeEnchant(enchantment);

        return this;
    }
    
    public ItemBuilder setCustomModelDAta(int value){
        meta.setCustomModelData(value);

        return this;
    }


    public ItemStack build() {
        itemStack.setItemMeta(meta);

        return itemStack;
    }
}
