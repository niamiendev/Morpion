package com.berakahnd.morpion.core.navigation

import kotlinx.serialization.Serializable

sealed class Screens{
    @Serializable
    object Welcome : Screens()
    @Serializable
    object Game : Screens()
    @Serializable
    object Score : Screens()
    @Serializable
    object Settings : Screens()
}