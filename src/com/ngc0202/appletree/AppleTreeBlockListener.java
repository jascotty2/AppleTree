package com.ngc0202.appletree;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

public class AppleTreeBlockListener extends BlockListener {

    public static AppleTree plugin;
    PropertiesFile prop = new PropertiesFile("appletree.properties");

    public AppleTreeBlockListener(AppleTree instance) {
        plugin = instance;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.LEAVES) {
            double rand = Math.random();
            if (rand <= prop.getDouble("AChance")) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
            } else if (prop.getBoolean("CanDropGold")
                    && rand <= prop.getDouble("GAChance") + prop.getDouble("AChance")) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
            } else if (prop.getBoolean("CanDropCocoa")
                    && rand<= prop.getDouble("CBChance") + prop.getDouble("AChance") + (prop.getBoolean("CanDropGold") ? prop.getDouble("GAChance") : 0) ) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 1, (short) 3));
            }
        }
    }

    @Override
    public void onLeavesDecay(LeavesDecayEvent event) {
        if (prop.getBoolean("CanDropDecay")) {
            double rand = Math.random();
            if (rand <= prop.getDouble("AChance")) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
            } else if (prop.getBoolean("CanDropGold")
                    && rand <= prop.getDouble("GAChance") + prop.getDouble("AChance")) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
            } else if (prop.getBoolean("CanDropCocoa")
                    && rand <= prop.getDouble("CBChance")+ prop.getDouble("AChance") + (prop.getBoolean("CanDropGold") ? prop.getDouble("GAChance") : 0)) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 1, (short) 12));
            }
        }
    }
}
