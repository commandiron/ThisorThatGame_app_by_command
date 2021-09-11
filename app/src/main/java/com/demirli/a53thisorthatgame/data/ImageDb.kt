package com.demirli.a53thisorthatgame.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demirli.a53thisorthatgame.model.Image

@Database(entities = arrayOf(Image::class), version = 1)
abstract class ImageDb: RoomDatabase() {
    abstract fun imageDao(): ImageDao

    companion object{

        var INSTANCE: ImageDb? = null

        fun getInstance(context: Context): ImageDb{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ImageDb::class.java,
                    "image_db")
                    .addCallback(roomDbCallback)
                    .build()

            }
            return INSTANCE as ImageDb
        }

        private val roomDbCallback = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsyncTask(INSTANCE).execute()
            }
        }

        class PopulateAsyncTask(private val db: ImageDb?): AsyncTask<Void, Void, Void>() {
            private val dao: ImageDao? by lazy { db?.imageDao() }
            override fun doInBackground(vararg params: Void?): Void? {

                var image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/terrier-toy/n02087046_693.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/briard/n02105251_6667.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/husky/n02110185_1066.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/terrier-westhighland/n02098286_4689.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                /*
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/deerhound-scottish/n02092002_3271.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/corgi-cardigan/n02113186_10816.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/mountain-swiss/n02107574_2370.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)
                image = Image(
                    photoUrl = "https://images.dog.ceo/breeds/cotondetulear/IMAG1063.jpg",
                    globalVote = 0
                )
                dao?.addImage(image)

                 */
                return null
            }
        }

    }


}