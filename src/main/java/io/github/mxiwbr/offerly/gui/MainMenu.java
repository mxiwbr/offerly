package io.github.mxiwbr.offerly.gui;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import io.github.mxiwbr.offerly.factories.ItemFactory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class MainMenu {

    ChestGui gui = new ChestGui(3, ComponentHolder.of(
            Component.text(
                    "Marketplace",
                    NamedTextColor.GOLD,
                    TextDecoration.BOLD
            )

    ));

    public void open(Player player) {

        loadContents();
        gui.show(player);

    }

    public void loadContents() {

        // prevent manipulation of chest interface
        gui.setOnTopClick(event -> event.setCancelled(true));
        gui.setOnBottomClick(event -> {
            // prevent shift-clicking items to chest interface
            if (event.getClick().isShiftClick()) {
                event.setCancelled(true);
            }
        });

        StaticPane pane = new StaticPane(9, 3);

        pane.addItem(
                new GuiItem(
                        ItemFactory.createCustomPlayerHead(
                                "Browse Marketplace",
                                NamedTextColor.YELLOW,
                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTMzZmM5YTQ1YmUxM2NhNTdhNzhiMjE3NjJjNmUxMjYyZGFlNDExZjEzMDQ4Yjk2M2Q5NzJhMjllMDcwOTZhYiJ9fX0=",
                                1
                        ),
                        event -> event.getWhoClicked().sendMessage("[DEBUG] Open Marketplace for " + event.getWhoClicked().getName())
                ), 4, 1
        );

        gui.addPane(Slot.fromXY(0, 0), pane);
        gui.update();

    }

}
