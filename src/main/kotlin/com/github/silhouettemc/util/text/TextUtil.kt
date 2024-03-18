package com.github.silhouettemc.util.text

import com.github.silhouettemc.Silhouette.Companion.mm
import com.github.silhouettemc.util.ConfigUtil
import org.bukkit.command.CommandSender
import java.util.*

fun translate(input: String) = mm.deserialize(input)

private val errorPrefix = ConfigUtil.messages.getString("errorPrefix")
    ?: "<#ff6e6e>⚠<#ff7f6e>"

fun warning(input: String) = translate("$errorPrefix $input")

fun CommandSender.sendError(error: String) {
    this.sendMessage(warning(error))
}

fun CommandSender.sendTranslated(message: String) {
    this.sendMessage(translate(message))
}

fun CommandSender.send(key: String) {
    val message = ConfigUtil.getMessage(key)
    this.sendMessage(translate(message))
}

fun CommandSender.send(key: String, placeholders: Map<String, String>) {
    val message = ConfigUtil.getMessage(key, placeholders)
    this.sendMessage(translate(message))
}

fun UUID.withoutDashes() = this.toString().replace("-", "")
fun String.toUUID(): UUID = UUID.fromString(this.insertUUIDDashes())

/**
 * Inserts dashes into a UUID string, or returns the string if it already has dashes (checks if it has 36 characters compared to 32)
 */
fun String.insertUUIDDashes(): String {
    if (length == 36) return this
    if (length != 32) throw IllegalArgumentException("Invalid UUID length")
    return StringBuilder()
        .append(substring(0, 8))
        .append("-")
        .append(substring(8, 12))
        .append("-")
        .append(substring(12, 16))
        .append("-")
        .append(substring(16, 20))
        .append("-")
        .append(substring(20))
        .toString()
}

fun String.replacePlaceholders(map: Map<String, String>, parenthesis: String = "{}", ignoreCase: Boolean = false) : String {
    var placeholded = this
    for (value in map) {
        placeholded = placeholded.replace("${parenthesis[0]}${value.key}${parenthesis[1]}", value.value, ignoreCase)
    }
    return placeholded
}