package com.gotofinal.darkrise.economy.commands;

import com.gotofinal.darkrise.economy.DarkRiseEconomy;
import me.travja.darkrise.core.item.DarkRiseItem;
import me.travja.darkrise.core.command.RiseCommand;
import me.travja.darkrise.core.legacy.util.message.MessageData;
import me.travja.darkrise.core.legacy.util.message.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Map;

public class EconClaimCommand extends RiseCommand {

    private DarkRiseEconomy eco;

    public EconClaimCommand(DarkRiseEconomy plugin, EconCommand command) {
        super("claim", Collections.singletonList("claim"), command);
        eco = plugin;
    }


    @Override
    public void runCommand(CommandSender sender, RiseCommand command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You can't perform this command from the console!");
            return;
        }

        Map<DarkRiseItem, Integer> added = eco.checkItemsToAdd((Player) sender);
        MessageUtil.sendMessage("economy.commands.claim.claimed", sender, new MessageData("amount", added.size()));
    }
}
