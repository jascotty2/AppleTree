package com.ngc0202.appletree;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class AppleTree extends JavaPlugin {

    private final AppleTreeBlockListener blockListener = new AppleTreeBlockListener(this);

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.LEAVES_DECAY, blockListener, Event.Priority.Highest, this);

        PropertiesFile prop = new PropertiesFile("appletree.properties");
        if (!prop.keyExists("AChance")) {
            prop.setDouble("AChance", 0.05);
        }
        if (!prop.keyExists("GAChance")) {
            prop.setDouble("GAChance", 0.005);
        }
        if (!prop.keyExists("CBChance")) {
            prop.setDouble("CBChance", 0.02);
        }
        //if (!prop.keyExists("SaplingChance")) prop.setDouble("SaplingChance", 0.1);
        if (!prop.keyExists("CanDropGold")) {
            prop.setBoolean("CanDropGold", true);
        }
        if (!prop.keyExists("CanDropCocoa")) {
            prop.setBoolean("CanDropCocoa", true);
        }
        if (!prop.keyExists("CanDropDecay")) {
            prop.setBoolean("CanDropDecay", true);
        }
        double total = prop.getDouble("AChance") + prop.getDouble("GAChance")
                + prop.getDouble("CBChance");// + prop.getDouble("SaplingChance");
        if (total > 1) {
            prop.setDouble("AChance", prop.getDouble("AChance") / total);
            prop.setDouble("GAChance", prop.getDouble("GAChance") / total);
            prop.setDouble("CBChance", prop.getDouble("CBChance") / total);
            //prop.setDouble("SaplingChance", prop.getDouble("SaplingChance") / total);
        }
        System.out.println(prop.getDouble("AChance") +", " + prop.getDouble("GAChance") + ", " + prop.getDouble("CBChance"));
        System.out.println("AppleTree v" + getDescription().getVersion() + " activated.");
    }

    @Override
    public void onDisable() {
        System.out.println("AppleTree disabled.");
    }
}
