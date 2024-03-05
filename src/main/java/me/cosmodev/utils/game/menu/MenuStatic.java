package me.cosmodev.utils.game.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuStatic {
    //public static HashMap<String, MenuStatic> viewers = new HashMap<>();
    public static List<MenuStatic> menus = new ArrayList<>();
    private String title;
    private int amount;
    private Inventory example;
    private List<String> viewers = new ArrayList<>();

    public static MenuStatic create(String title, int amount) {
        MenuStatic menuStatic = new MenuStatic(title, amount);

        menus.add(menuStatic);

        return menuStatic;
    }

    public MenuStatic(String title, int amount) {
        example = Bukkit.createInventory(null, amount, title);
        this.title = title;
        this.amount = amount;
    }

    public void setItem(ItemStack item, int... slots) {
        for (int i : slots) {
            example.setItem(i, item);
        }
    }

    public void open(Player p) {
        p.openInventory(example);
        viewers.add(p.getName());
    }

    public int getAmount() {
        return amount;
    }

    public Inventory getExample() {
        return example;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getViewers() {
        return viewers;
    }
}