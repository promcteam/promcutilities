package com.promcteam.sapphire.commands;

import com.promcteam.sapphire.Sapphire;
import com.promcteam.risecore.item.DarkRiseItem;
import com.promcteam.risecore.command.RiseCommand;
import com.promcteam.risecore.legacy.util.message.MessageData;
import com.promcteam.risecore.legacy.util.message.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Map;

public class SapphireClaimCommand extends RiseCommand {

    private final Sapphire eco;

    public SapphireClaimCommand(Sapphire plugin, SapphireCommand command) {
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
        MessageUtil.sendMessage("sapphire.commands.claim.claimed", sender, new MessageData("amount", added.size()));
    }
}