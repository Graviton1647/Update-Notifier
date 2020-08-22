package com.cube.notifier

import com.cube.notifier.Update.Companion.message
import com.cube.notifier.Update.Companion.timer
import com.cube.notifier.Update.Companion.update
import com.cube.notifier.Utils.drawText
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.cube.api.events.MinecraftEvent
import org.cube.api.timer.secondsToString
import org.cube.api.actionbar.ActionBar
import org.cube.api.player.Title
import org.cube.api.player.message
import org.cube.api.utils.*



@MinecraftEvent
class UpdateEvents : Listener {

    @EventHandler
    fun onPlayerDamage(event : EntityDamageEvent) {
        if (!Configuration.data.allowDamage && event.entity is Player && update && timer.timeLeft <= Configuration.data.safeTime) {
           event.isCancelled = true
        }
    }

    @EventHandler
    fun onLogin(event: PlayerJoinEvent) {
        if(update) {
            if (timer.timeLeft <= Configuration.data.safeTime && !Configuration.data.allowLogin) {
                val message = Configuration.data.updateMessage.
                    replace("{time}", secondsToString(timer.timeLeft)).
                    replace("{reason}",message)
                event.player.kickPlayer(message)
            }
            drawText()
        }
    }


}
