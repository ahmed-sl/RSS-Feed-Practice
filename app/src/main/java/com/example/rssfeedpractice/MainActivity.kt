package com.example.rssfeedpractice


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var rcv : RecyclerView
     var title = mutableListOf<RecentQuestions>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcv = findViewById(R.id.rv)
        fitchData().execute()
    }
    private inner class fitchData: AsyncTask<Void,Void,MutableList<RecentQuestions>>(){
        val par = XMLParser()
        override fun doInBackground(vararg p0: Void?): MutableList<RecentQuestions> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            title = urlConnection.getInputStream()?.let {
                par.parse(it)
            } as MutableList<RecentQuestions>
            return title
        }

        override fun onPostExecute(result: MutableList<RecentQuestions>?) {
            super.onPostExecute(result)
            rcv.adapter= rvAdapter(title)
            rcv.layoutManager=LinearLayoutManager(this@MainActivity)
        }

    }
}