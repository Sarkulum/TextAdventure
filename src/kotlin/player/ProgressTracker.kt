package src.kotlin.player

data class TutorialTracker(
    var passed: Boolean = false,
    var ownsSilverRing: Boolean = false,
    var gotMission: Boolean = false,
    var tookMedKit: Boolean = false,
    var ateBurger: Boolean = false,
)

data class FirstRoomTracker(
    var firstRoomVisit: Boolean = false,
    var tookBottle: Boolean = false,
    var tookMedKit: Boolean = false,
    var tookBoots: Boolean = false,
    var tookFood: Boolean = false,
    var hasKey: Boolean = false,
)

data class SecondRoomTracker(
    var tookSoda: Boolean = false,
    var ateSandwich: Boolean = false,
    var foughtZombie: Boolean = false,
)
