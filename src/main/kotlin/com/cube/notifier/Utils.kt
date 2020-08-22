package com.cube.notifier

import org.bukkit.Bukkit

import org.cube.api.actionbar.ActionBar
import org.cube.api.timer.secondsToString
import org.bukkit.inventory.ItemStack



object Utils {

    fun drawText() {
        val message =
            Configuration.data.updateMessage.replace("{time}",secondsToString(Update.timer.timeLeft)
        ).replace("{reason}", Update.message)

        if(Configuration.data.actionbar) {
            val bar = ActionBar(message)
            bar.sendToAll()
        } else {
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendTitle("",message)
            }

        }
    }


}
