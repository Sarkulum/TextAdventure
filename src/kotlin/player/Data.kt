package src.kotlin.player

import src.kotlin.items.Weapon
import src.kotlin.output.TextColor

data class Stats (
    var maxHp: Byte,
    var minDamage: Byte,
    var maxDamage: Byte,
    var heldWeapon: Weapon,
    var goldCoins: Int,
    var score: Int,
    var movementSpeed: Byte,
    var died: Boolean = false,
)

data class UserData (
    var userID: Byte,
    var userName: String,
    var userAge: Byte,
    var selectedTextColor: TextColor,
    var dev: Boolean = false,
)