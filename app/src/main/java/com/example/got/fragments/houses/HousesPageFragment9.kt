package com.example.got.fragments.houses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.`interface`.ApiInterface
import com.example.got.adapter.HouseAdapter
import com.example.got.models.House
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val BASE_URL = "https://anapioficeandfire.com/api/"
/**
 * A simple [Fragment] subclass.
 * Use the [HousesPageFragment9.newInstance] factory method to
 * create an instance of this fragment.
 */
class HousesPageFragment9 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var houseRecyclerView: RecyclerView
    private lateinit var houseAdapter: HouseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getHouseData { houses ->
            houseAdapter = HouseAdapter(houses)
            houseRecyclerView.adapter = houseAdapter
            houseRecyclerView.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_houses_page9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        houseRecyclerView = view.findViewById(R.id.housesRecyclerView)
        houseRecyclerView.setHasFixedSize(true)
    }

    private fun getHouseData(callback: (List<House>) -> Unit) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getHousesPage9()
        retrofitData.enqueue(object : Callback<List<House>?> {
            override fun onResponse(call: Call<List<House>?>, response: Response<List<House>?>) {
                val responseBody = response.body() ?: emptyList()
                callback(responseBody)
            }

            override fun onFailure(call: Call<List<House>?>, t: Throwable) {
                Log.d("HouseFragment", "onFailure: " + t.message)
                // Optionally handle failure here
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HousesPageFragment9.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HousesPageFragment9().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}