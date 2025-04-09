package stsa.kotlin_htmx.database

import com.typesafe.config.ConfigFactory
import org.jetbrains.exposed.sql.Database

object DatabaseFactory{
    fun init(){
        val config = ConfigFactory.load().getConfig("database")
        Database.connect(
            url = config.getString("url"),
            driver = config.getString("driver"),
            user = config.getString("user"),
            password = config.getString("password")
        )
    }
}