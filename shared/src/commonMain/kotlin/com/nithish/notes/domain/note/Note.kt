package com.nithish.notes.domain.note

import com.nithish.notes.presentation.BabyBlueHex
import com.nithish.notes.presentation.LightGreenHex
import com.nithish.notes.presentation.RedOrangeHex
import com.nithish.notes.presentation.RedPinkHex
import com.nithish.notes.presentation.VioletHex
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("id")
    val id: Long?,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("colorHex")
    val colorHex:String,
    @SerialName("created")
    val created: LocalDateTime
) {
    private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

    fun generateRandomColor() = colors.random()
}