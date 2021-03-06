package com.gotofinal.darkrise.economy.commands;

import com.gotofinal.darkrise.economy.DarkRiseEconomy;
import com.gotofinal.darkrise.economy.cfg.VoucherManager;
import me.travja.darkrise.core.command.RiseCommand;
import me.travja.darkrise.core.legacy.util.message.MessageData;
import me.travja.darkrise.core.legacy.util.message.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

//@DarkRiseSubCommand(value = EconCommand.class, name = "voucher")
public class EconVoucherCommand extends RiseCommand {
    public EconVoucherCommand(DarkRiseEconomy plugin, EconCommand command) {
        super("voucher", Collections.singletonList("voucher"), command);
        this.setUsage(command.getUsage());
    }

    @Override
    public void runCommand(CommandSender sender, RiseCommand command, String label, String[] args) {
        if (!this.checkPermission(sender, "pmcu.voucher")) {
            return;
        }

        if (args.length != 1) {
            this.sendUsage(command.getUsage(), sender, command, args);
            return;
        }

        Integer voucherId;
        try {
            voucherId = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            MessageUtil.sendMessage("notANumber", sender, new MessageData("text", args[0]));
            return;
        }

        Optional<VoucherManager.VoucherData> data = VoucherManager.getInstance().getData(voucherId);

        if (data.isPresent()) {
            Date time = new Date(data.get().timestamp * 1000);
            OfflinePlayer player = Bukkit.getOfflinePlayer(data.get().playerUUID);

            MessageUtil.sendMessage("economy.commands.voucher.info", sender,
                    new MessageData("voucher_id", voucherId),
                    new MessageData("player", player),
                    new MessageData("date", time.toString()));
        } else {
            MessageUtil.sendMessage("economy.commands.voucher.info-unused", sender,
                    new MessageData("voucher_id", voucherId));
        }
    }
}
