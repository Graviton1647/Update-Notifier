package com.cube.notifier.commands

import com.cube.notifier.Configuration
import com.cube.notifier.Update
import com.cube.notifier.Update.Companion.timer
import com.cube.notifier.Update.Companion.update
import com.cube.notifier.Utils.drawText
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.cube.api.commands.CommandData
import org.cube.api.commands.MinecraftCommand
import org.cube.api.timer.Countdown
import org.cube.api.timer.convertTime

class UpdateCommand {

    @MinecraftCommand(
        name ="update.start",
        description = "Stops a current Update",
        usage = "/update start 1m 20s {message}",
        aliases = ["us"],
        permission = "update.*"
    )

    fun update(data : CommandData) {
        val player = data.getPlayer()
        val args = data.args
        if (check(args) && args[0].any { it.isDigit() } && args[0].any { it.isLetter() }) {
            if (update) {
                player!!.sendMessage("Server is Already updating")
                return
            } else {
                update = true
                val time = args
                    .dropLast(1)
                    .filter { it.contains("s") || it.contains("h") || it.contains("m") }
                    .joinToString(" ")

                val message = args
                    .filter { !it.contains("s") || !it.contains("h") || !it.contains("m") }
                    .joinToString(" ")

                update(time, message.replace(time,""), player!!)
            }
        } else {
            player!!.sendMessage(Configuration.data.error)
            return
        }
    }


    private fun check(args: Array<String>): Boolean = args.isEmpty()
            || args.size > 1
            || args[0].contains("h")
            || args[0].contains("s")
            || args[0].contains("m")

    private fun update(time: String, message: String, sender: CommandSender) {
        try {
            timer = object : Countdown(Update.plugin, convertTime(time)) {
                override fun onFinish() {
                    Bukkit.dispatchCommand(sender, "restart")
                }
                override fun onTick() {
                    Update.message = message
                    drawText()
                }
            }
            timer.start()
            update = true
        }catch (event : NumberFormatException) {
            update = false
        }

    }


}
