package com.ngc0202.appletree;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

public class AppleTreeBlockListener extends BlockListener {

	public static AppleTree plugin;
	PropertiesFile prop = new PropertiesFile(AppleTree.propFile.getAbsolutePath());

	public AppleTreeBlockListener(AppleTree instance) {
		plugin = instance;
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}
		BlockState b = event.getBlock().getState();
		String w = b.getWorld().getName().toLowerCase();
		if (plugin.disabledWorlds.contains(w)) {
			return;
		}
		if (b.getType() == Material.LEAVES) {
			double rand = Math.random();
			if (rand <= prop.getDouble("AChance")) {
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			} else if (prop.getBoolean("CanDropGold")
					&& rand <= prop.getDouble("GAChance") + prop.getDouble("AChance")) {
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
			} else if (prop.getBoolean("CanDropCocoa")
					&& rand <= prop.getDouble("CBChance") + prop.getDouble("AChance") + (prop.getBoolean("CanDropGold") ? prop.getDouble("GAChance") : 0)) {
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 1, (short) 3));
			}
			if (prop.getBoolean("DropLeaves")) {
				//System.out.println(event.getBlock().getData() + " " + b.getRawData());
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(),
						new ItemStack(Material.LEAVES, 1, (short) 0, (byte) (b.getRawData() % 8)));
				//new ItemStack(Material.LEAVES, 1, (short) event.getBlock().getData());//, event.getBlock().getData()));
			}
		}
	}

	@Override
	public void onLeavesDecay(LeavesDecayEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (prop.getBoolean("CanDropDecay")) {
			BlockState b = event.getBlock().getState();
			String w = b.getWorld().getName().toLowerCase();
			if (plugin.disabledWorlds.contains(w)) {
				return;
			}
			double rand = Math.random();
			if (rand <= prop.getDouble("DecayAChance")) {
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			} else if (prop.getBoolean("DecayCanDropGold")
					&& rand <= prop.getDouble("DecayGAChance") + prop.getDouble("AChance")) {
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
			} else if (prop.getBoolean("DecayCanDropCocoa")
					&& rand <= prop.getDouble("DecayCBChance") + prop.getDouble("AChance") + (prop.getBoolean("CanDropGold") ? prop.getDouble("GAChance") : 0)) {
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 1, (short) 3));
			}
			if (prop.getBoolean("DecayDropLeaves")) {
				//System.out.println(event.getBlock().getData() + " " + b.getRawData());
				b.getWorld().dropItemNaturally(event.getBlock().getLocation(),
						new ItemStack(Material.LEAVES, 1, (short) 0, (byte) (b.getRawData() % 8)));
				//new ItemStack(Material.LEAVES, 1, (short) event.getBlock().getData());//, event.getBlock().getData()));
			}
		}
	}
}
