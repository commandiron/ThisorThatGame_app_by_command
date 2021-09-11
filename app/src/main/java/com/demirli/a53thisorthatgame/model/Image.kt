package com.demirli.a53thisorthatgame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = true)
    var photo_id: Int = 0,
    var photoUrl: String,
    var globalVote: Int
) {
}