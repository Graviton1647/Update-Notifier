package com.cube.notifier

import com.cube.notifier.commands.ReloadCommand
import com.cube.notifier.commands.StopUpdateCommand
import com.cube.notifier.commands.UpdateCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.cube.api.CubePlugin
import org.cube.api.timer.Countdown
import org.cube.api.timer.convertTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule





class Update : CubePlugin() {

    companion object {

        lateinit var plugin : JavaPlugin

        var update = false

        lateinit var timer : Countdown

        var message : String = ""



    }

    override fun start() {
        plugin = this
        Configuration.load(this)


        Configuration.data.scheduled.forEach {

            val today = Calendar.getInstance()
            today[Calendar.HOUR_OF_DAY] = it.hour
            today[Calendar.MINUTE] = it.mins
            today[Calendar.SECOND] = it.seconds

            Timer("Task:${it.message}", false).schedule(today.time,TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                update(it.time, it.message)
            }

        }

    }

    override fun stop() {

    }

    override var commandList: MutableList<Any> = listOf(
            ReloadCommand(),
            StopUpdateCommand(),
            UpdateCommand()
    ).toMutableList()

    override var eventsList: MutableList<Listener> = listOf(
            UpdateEvents()
    ).toMutableList()

    private fun update(time: String, message: String) {
        try {
            timer = object : Countdown(Update.plugin, convertTime(time)) {
                override fun onFinish() {
                    Bukkit.getConsoleSender().sendMessage("restart")
                }
                override fun onTick() {
                    Update.message = message
                    Utils.drawText()
                }
            }
            timer.start()
            update = true
        }catch (event : NumberFormatException) {
            update = false
        }

    }

}