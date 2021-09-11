package com.demirli.a53thisorthatgame

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.demirli.a53thisorthatgame.data.ImageDao
import com.demirli.a53thisorthatgame.data.ImageDb
import com.demirli.a53thisorthatgame.model.Image

class MainRepository(context: Context) {

    private val db by lazy{ ImageDb.getInstance(context)}
    private val dao by lazy{db.imageDao()}

    fun getAllImages(): LiveData<List<Image>> = dao.getAllImages()

    fun addImage(image: Image) = AddCartAsyncTask(dao).execute(image)

    private class AddCartAsyncTask(val dao: ImageDao): AsyncTask<Image, Void, Void>(){
        override fun doInBackground(vararg params: Image?): Void? {
            dao.addImage(params[0]!!)
            return null
        }
    }

}