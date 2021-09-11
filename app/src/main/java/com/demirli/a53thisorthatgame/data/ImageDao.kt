package com.demirli.a53thisorthatgame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demirli.a53thisorthatgame.model.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM images")
    fun getAllImages(): LiveData<List<Image>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImage(image: Image)
}
