package me.cosmodev.utils.game.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu_old {
    public final static List<Menu_old> MENU_OLDS = new ArrayList<>();
    private String title;
    private int size;
    private List<Button> buttons = new ArrayList<>();
    private Map<Integer, ItemStack> items = new HashMap<>();
    private Inventory inventory;
    private static final HashMap<String, Inventory> viewers = new HashMap<>();

    public static Menu_old create(String title, int size){
        MENU_OLDS.add(new Menu_old(title, size));

        return MENU_OLDS.get(MENU_OLDS.size() - 1);
    }
    @Nullable
    public static Menu_old getMenuBy(Inventory inventory){
        for(Menu_old menuOld : MENU_OLDS){
            if(menuOld.inventory == inventory){
                return menuOld;
            }
        }

        return null;
    }


    private Menu_old(String title, int size) {
        this.title = title;
        this.size = size;

        inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
    }

    public void addButton(Button button){
        for(int slot: button.getSlots()){
            if(getButtonBy(slot) != null){
                throw new IllegalArgumentException("Ошибка при добовления кнопки в слоте: " + slot + " так-как слот занят!");
            }
        }
    }
    public void addItem(ItemStack item, int ... slots) {
        if (slots.length == 0) {
            throw new IllegalArgumentException("Не указаны слоты для добавления предмета");
        }

        for (int slot : slots) {
            if (this.items.containsKey(slot)) {
                throw new IllegalArgumentException("Ошибка при добавлении предмета в слот: " + slot + ", так как слот занят!");
            }

            this.items.put(slot, item);
            inventory.setItem(slot, item);
        }
    }


    public void removeItem(int slot) {
        if (items.containsKey(slot)) {
            items.remove(slot);
            inventory.setItem(slot, null);
        }
    }

    public void changeItem(int slot, ItemStack newItem) {
        if (items.containsKey(slot)) {
            items.put(slot, newItem);
            inventory.setItem(slot, newItem);
        }
    }
    @Nullable
    public Button getButtonBy(int slot){
        for(Button button: buttons){
            if(button.getSlots().contains(slot)){
                return button;
            }
        }
        return null;
    }

    public void show(Player player){
        player.openInventory(inventory);
    }

}
