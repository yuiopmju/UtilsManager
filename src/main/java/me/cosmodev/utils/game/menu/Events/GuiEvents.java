package me.cosmodev.utils.game.menu.Events;

import me.cosmodev.utils.game.menu.Button;
import me.cosmodev.utils.game.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;

public class GuiEvents implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            Menu menu = Menu.getMenuBy(event.getClickedInventory());
            if (menu != null) {
               event.setCancelled(true);

               Button button = menu.getButtonBy(event.getSlot());
               if (button != null) {
                   button.onClick((Player) event.getWhoClicked());
               }
            }
        }
    }
}
