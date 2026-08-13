package io.github.mxiwbr.offerly.gui;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class MainMenu {

    ChestGui gui = new ChestGui(3, ComponentHolder.of(
            Component.text(
                    "Marketplace",
                    NamedTextColor.GOLD
            )
    ));

    public void open(Player player) {

        gui.show(player);

    }

}
