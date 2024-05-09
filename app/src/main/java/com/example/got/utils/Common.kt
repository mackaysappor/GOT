package com.example.got.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import com.example.got.R
import com.example.got.`interface`.ApiInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://anapioficeandfire.com/api/"
class Common {
    @SuppressLint("UseCompatLoadingForDrawables")
    fun setImage(imageView: ImageView, house: String){
        if (house.contains("arryn", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_arryn))
        }
        else if (house.contains("baratheon", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_baratheon))
        }
        else if (house.contains("frey", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_frey))
        }
        else if (house.contains("greyjoy", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_greyjoy))
        }
        else if (house.contains("lannister", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_lannister))
        }
        else if (house.contains("martell", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_martell))
        }
        else if (house.contains("mormont", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_mormont))
        }
        else if (house.contains("stark", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_stark))
        }
        else if (house.contains("targaryen", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_targaryen))
        }
        else if (house.contains("tully", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_tully))
        }
        else if (house.contains("tyrell", true)){
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.noun_house_tyrell))
        }
        else{
            imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.general_coa))
        }

    }

    fun getResponseData(url: String, callback: (String?, String?) -> Unit) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(url)
        retrofitData.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val responseBody = response.body()?.string()
                callback(responseBody, null)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("Fragment", "onFailure: " + t.message)
                // Optionally handle failure here
            }
        })
    }

    fun getPath(url: String): String? {
        var path: String? = null
        if (url.startsWith(BASE_URL)){
            path = url.substring(BASE_URL.length)
        }
        return path
    }
}