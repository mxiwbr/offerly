package io.github.mxiwbr.offerly.factories;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class ItemFactory {

    /**
     * Creates a customized player head
     * @param customItemName The name which the item should have
     * @param itemNameColor NamedTextColor of the items name
     * @param textureValue base64 value of the skull texture
     * @return ItemStack
     */
    public static ItemStack createCustomPlayerHead(
            String customItemName,
            NamedTextColor itemNameColor,
            String textureValue,
            int amount
    )
    {

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        PlayerProfile playerProfile = Bukkit.createProfile(UUID.randomUUID());
        playerProfile.setProperty(
                new ProfileProperty(
                        "textures",
                        textureValue
                )
        );

        skullMeta.setPlayerProfile(playerProfile);
        skullMeta.customName(
                Component.text(
                        customItemName,
                        itemNameColor
                )
        );

        itemStack.setItemMeta(skullMeta);
        itemStack.setAmount(amount);

        return itemStack;

    }

}
