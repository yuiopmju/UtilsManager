package me.cosmodev.utils.game.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class Button {
    private final List<Integer> slots;
    private final ItemStack itemStack;

    public Button(ItemStack itemStack, Integer ... slots) {
        this.slots = Arrays.asList(slots);
        this.itemStack = itemStack;
    }

    public abstract void onClick(Player player);

    public List<Integer> getSlots() {
        return slots;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
