package com.gotofinal.darkrise.economy;

import com.gotofinal.darkrise.economy.commands.*;
import com.gotofinal.darkrise.economy.listener.PlayerListener;
import org.bukkit.plugin.PluginManager;

public class Register {

    public static void register(DarkRiseEconomy plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(plugin), plugin);
//        pm.registerEvents(new TwoHandListener(), plugin);

        EconCommand econ = new EconCommand(plugin);
        new EconClaimCommand(plugin, econ);
        EconItemsCommand items = new EconItemsCommand(plugin, econ);
        new EconItemsCreateCommand(plugin, items);
        new EconItemsDeleteCommand(plugin, items);
        new EconItemsGiveCommand(plugin, items);
        new EconItemsDropCommand(plugin, items);
        new EconItemsListCommand(plugin, items);
        new EconItemsVoucherCommand(plugin, items);
        new EconReloadCommand(plugin, econ);
        new EconSaveCommand(plugin, econ);
        new EconVoucherCommand(plugin, econ);

        //FOR TESTING PURPOSES ONLY
//        new EconTestItemCommand(plugin, econ);
//        new EconTestItemEqualityCommand(plugin, econ);
    }

}
