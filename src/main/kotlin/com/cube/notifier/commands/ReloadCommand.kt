package com.cube.notifier.commands

import com.cube.notifier.Configuration
import com.cube.notifier.Update.Companion.plugin
import org.bukkit.ChatColor
import org.cube.api.commands.CommandData
import org.cube.api.commands.MinecraftCommand




class ReloadCommand {


    @MinecraftCommand(
        name ="update.reload",
        description = "Reload the Update Configs",
        usage = "/reloadupdate",
        aliases = ["ur"],
        permission = "update.*"
    )

    fun reload(data : CommandData) {
        val player = data.getPlayer()
        Configuration.load(plugin)
        player!!.sendMessage("${ChatColor.DARK_RED}Configs Reloaded Stopped")
    }

}
