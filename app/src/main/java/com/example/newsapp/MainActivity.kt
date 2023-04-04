package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.interfaces.ApiInterface
import com.example.newsapp.model.Data
import com.example.newsapp.model.DataItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var list:ArrayList<Data?>? =null
    private var adapter:RecyclerAdapter? = null
    private var recycler:RecyclerView? = null
    private var addRepo:FloatingActionButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiInterface: ApiInterface? = ApiClient.getClient()?.create(ApiInterface::class.java)
        val call: Call<Data?>? = apiInterface?.getExercise()
        list = ArrayList()
        recycler = findViewById(R.id.recycler)
        addRepo = findViewById(R.id.addrepo)

        call!!.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>?, response: Response<Data?>) {
              println("MainActivity.onResponse ${response.body()?.size}")
                for (i in 0..(response.body()?.size?.minus(1) ?: 0)){
                    println("MainActivity.onResponse --> ${response.body()?.get(i)?.url}")
                    list?.add(response?.body())
                    println("MainActivity.onCreate 11-> ${list?.size}")
                    adapter = RecyclerAdapter(list,this@MainActivity)
                    recycler?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                println("MainActivity.onFailure")
            }
        })
        clickListener()
    }

    fun clickListener(){
        addRepo?.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddRepo::class.java))
        }
    }
}