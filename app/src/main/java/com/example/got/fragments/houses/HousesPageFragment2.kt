package com.example.got.fragments.houses

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.`interface`.ApiInterface
import com.example.got.adapter.HouseAdapter
import com.example.got.models.House
import com.example.got.utils.Common
import org.json.JSONObject
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
 * Use the [HousesPageFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class HousesPageFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView2: RecyclerView
    private lateinit var houseAdapter: HouseAdapter
    private lateinit var common: Common


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getHouseData {houses ->
            houseAdapter = HouseAdapter(houses){
                    clickedItem -> showItemDetails(clickedItem)
            }
            recyclerView2.adapter = houseAdapter
            recyclerView2.layoutManager = GridLayoutManager(context, 2)
        }
        common = Common()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_houses_page2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView2 = view.findViewById(R.id.housesRecyclerView2)
        recyclerView2.setHasFixedSize(true)
    }

    private fun getHouseData(callback: (List<House>) -> Unit) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getHousesPage2()
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateDisplay(house: House, characterNameList: Map<String, String>) {
        val currentLord = "Current Lord"
        val heir = "Heir"
        val overlord = "Overlord"
        val region = "Region: " + house.region
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.detailed_list_item_layout)

        val itemImageView = dialog.findViewById<ImageView>(R.id.item_imageview)
        val titleNameTextView = dialog.findViewById<TextView>(R.id.item_nameview)
        val itemDetailTextView = dialog.findViewById<TextView>(R.id.item_detail1)
        val itemDetailTitleTextView1 = dialog.findViewById<TextView>(R.id.item_detail_title1)
        val itemDetailTitleTextView2 = dialog.findViewById<TextView>(R.id.item_detail_title2)
        val itemDetailTitleTextView3 = dialog.findViewById<TextView>(R.id.item_detail_title3)
        val itemDetailValueTextView1 = dialog.findViewById<TextView>(R.id.item_detail_value1)
        val itemDetailValueTextView2 = dialog.findViewById<TextView>(R.id.item_detail_value2)
        val itemDetailValueTextView3 = dialog.findViewById<TextView>(R.id.item_detail_value3)

        Log.d("HouseFragment", "Character Names: $characterNameList")

        common.setImage(itemImageView,house.name)
        titleNameTextView.text = house.name
        itemDetailTextView.text = region
        itemDetailTitleTextView1.text = currentLord
        itemDetailTitleTextView2.text = heir
        itemDetailTitleTextView3.text = overlord
        itemDetailValueTextView1.text = characterNameList["Current Lord"] ?: "None"
        itemDetailValueTextView2.text = characterNameList["Heir"] ?: "None"
        itemDetailValueTextView3.text = characterNameList["Overlord"] ?: "None"

        // Close the dialog when the close button is clicked
        val closeButton = dialog.findViewById<TextView>(R.id.close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showItemDetails(house: House){
        val characterNames = mutableMapOf<String, String>()
        val currentLord = house.currentLord
        val heir = house.heir
        val overlord = house.overlord

        val totalRequests = listOf(currentLord, heir, overlord).count(){it.isNotEmpty()}
        var completedRequests = 0

        val onCompletedRequest: () -> Unit = {
            completedRequests++
            if (completedRequests == totalRequests) {
                updateDisplay(house, characterNames) // All requests are done
            }
        }

        val handleRequest = { title: String, url: String? ->
            if (url != null) {
                val path = common.getPath(url)
                if (path != null) {
                    common.getResponseData(path) { responseBody, error ->
                        if (error != null) {
                            Log.e("HouseFragment", "Error fetching data for $title: $error")
                        } else {
                            if (responseBody != null) {
                                val response = JSONObject(responseBody)
                                val name = response.getString("name")
                                characterNames[title] = name // Store the name in the map
                            }
                        }
                        onCompletedRequest() // Always call onCompletedRequest after processing
                    }
                }
            } else {
                onCompletedRequest() // If URL is null, still count as completed
            }
        }

        handleRequest("Current Lord", currentLord)
        handleRequest("Heir", heir)
        handleRequest("Overlord", overlord)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HousesPageFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HousesPageFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}