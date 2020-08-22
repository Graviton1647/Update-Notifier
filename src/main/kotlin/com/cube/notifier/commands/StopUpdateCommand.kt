package com.cube.notifier.commands

import com.cube.notifier.Configuration
import com.cube.notifier.Update
import com.cube.notifier.Update.Companion.timer
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.cube.api.commands.CommandData
import org.cube.api.commands.MinecraftCommand

class StopUpdateCommand {

    @MinecraftCommand(
        name ="update.stop",
        description = "Stops a current Update",
        usage = "/stopupdate",
        aliases = ["us"],
        permission = "update.*"
    )

    fun stop(data : CommandData) {
        val player = data.getPlayer()
        timer.timer.cancel()
        Update.update = false
        player!!.sendMessage("${ChatColor.DARK_RED}Update Stopped")
    }

}
