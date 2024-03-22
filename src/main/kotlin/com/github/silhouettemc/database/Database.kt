package com.github.silhouettemc.database

import com.github.silhouettemc.Silhouette
import com.github.silhouettemc.history.History
import com.github.silhouettemc.punishment.Punishment
import com.github.silhouettemc.punishment.PunishmentType
import org.bson.conversions.Bson
import java.util.*

interface Database {
    fun initialize(plugin: Silhouette)

    fun addHistory(history: History)

    fun addPunishment(punishment: Punishment)

    fun updatePunishment(punishment: Punishment, vararg updates: Bson)

    fun removePunishment(punishment: Punishment)

    fun listPunishments(player: UUID): List<Punishment>

    fun getLatestActivePunishment(player: UUID, type: PunishmentType): Punishment?
    fun hasActivePunishment(player: UUID, type: PunishmentType): Boolean

    // todo: disconnect()?
}