package com.cube.notifier

import com.google.gson.GsonBuilder
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

data class Settings(
    var updateMessage : String = "Updating in {time} - {reason}",
    var kickMessage : String = "This server is about to update {time} - {reason}",
    var perm : String = "allow.update",
    var error : String = "Wrong Format please use the following /update 1h 1m 20s {reason}",
    var actionbar : Boolean = true,
    var allowDamage : Boolean = false,
    var allowLogin : Boolean = false,
    var safeTime : Int = 60,
    val scheduled : MutableList<Schedule> = listOf(
        Schedule(12,0,0,"Midnight update","1h5m0s")
    ).toMutableList()
)


data class Schedule(var hour : Int, var mins : Int, var seconds : Int,var message : String,val time: String)

object Configuration {


    /**
     *  [gson] Gson Builder
     */
    val gson = GsonBuilder().setPrettyPrinting().create()

    /**
     *  [data] Data for settings
     */
    lateinit var data : Settings

    fun load(plugin : JavaPlugin) {
        val file = File(plugin.dataFolder,"configs.json")
        if(!file.exists()) {
            file.createNewFile()
            file.writeText(gson.toJson(Settings()))
        }
        data = gson.fromJson(file.readText(), Settings::class.java)
    }


}