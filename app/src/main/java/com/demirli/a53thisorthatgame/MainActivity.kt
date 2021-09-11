package com.demirli.a53thisorthatgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a53thisorthatgame.model.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ScoreBoardAdapter

    private lateinit var shuffledList: List<Image>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //IMAGE Kullanmak saçma oldu değiştirmek gerekir. Aynı isimde class vs bok püsürle karışıyor.

        viewModel = MainViewModel(application)

        val llm = LinearLayoutManager(this)
        llm.stackFromEnd = true
        llm.reverseLayout = true
        scoreBoard_recyclerView.layoutManager = llm

        start_btn.setOnClickListener {
            getAllImages()
        }

        this_btn.setOnClickListener {
            next(this_btn)
        }

        that_btn.setOnClickListener {
            next(that_btn)
        }
    }

    fun getAllImages(){
        viewModel.getAllImages().observe(this, Observer {

            val sortedList = it.sortedWith(compareBy{it.globalVote})

            adapter = ScoreBoardAdapter(sortedList)
            scoreBoard_recyclerView.adapter = adapter

            //Random fotoğraf çiftleri için liste karıştırıldı.
            shuffledList = it.shuffled()

            runGame()
        })
    }

    fun runGame(){

        //this '0' that '1' olarak alınıp view'lara verildi.
        Picasso.get().load(shuffledList[0].photoUrl).into(imageView)
        Picasso.get().load(shuffledList[1].photoUrl).into(imageView2)
        textView.setText(shuffledList[0].globalVote.toString())
        textView2.setText(shuffledList[1].globalVote.toString())
    }

    fun next(thisOrThat: Button){

        //Hangi fotoğrafı beğendiyse this '0' that '1' olarak alınıp karıştırılan listeye verildi.
        var thisOrthatInt: Int? = null
        if(thisOrThat == this_btn){
            thisOrthatInt = 0
        }else if(thisOrThat == that_btn){
            thisOrthatInt = 1
        }

        //Oylanan fotoğraf için yeni obje oluşturuldu.
        val image = Image(shuffledList[thisOrthatInt!!].photo_id,
            shuffledList[thisOrthatInt].photoUrl,
            shuffledList[thisOrthatInt].globalVote + 1)

        //Oylanan fotoğraf database'e yenisinin üzerine yazılarak kaydedildi.
        viewModel.addImage(image)

        //Oyuna devam edildi.
        runGame()
    }
}
