package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.newsapp.postdata.PostData
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Response


class AddRepo : AppCompatActivity() {
    private var editText: AppCompatEditText? = null
    private var add: MaterialButton? = null
    private var share: MaterialButton? = null
    private var text: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_repo)

        add = findViewById(R.id.saveBtn)
        editText = findViewById(R.id.addRepo)
        text = findViewById(R.id.sharerepo)
        share = findViewById(R.id.shareBtn)
        val intents = intent.getStringExtra("url")
        println("AddRepo.onCreate 134 $intents")

        if(intents == null){
            text?.visibility = View.GONE
            share?.visibility = View.GONE
            add?.visibility = View.VISIBLE
            editText?.visibility = View.VISIBLE
        }else{
            text?.visibility = View.VISIBLE
            share?.visibility = View.VISIBLE
            add?.visibility = View.GONE
            editText?.visibility = View.GONE
            text?.text = intents
        }

        add?.setOnClickListener {
            val repoPost = PostDataHelper.apiClient.postData(editText?.text.toString())
            repoPost.enqueue(object : retrofit2.Callback<PostData> {
                override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                    println("MainActivity.onCreate 11-> scuess")
                    finish()
                }

                override fun onFailure(call: Call<PostData>, t: Throwable) {
                    println("MainActivity.onCreate 11-> failed")
                }
            })
        }

        share?.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage =
                    """
                    ${shareMessage + intents}
                    
                    
                    """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }
    }
}