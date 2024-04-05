package com.example.got.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.`interface`.ApiInterface
import com.example.got.adapter.CharacterAdapter
import com.example.got.adapter.FeaturedCharacterAdapter
import com.example.got.adapter.FeedAdapter
import com.example.got.models.Characters
import com.example.got.models.Feed
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
private const val CHARACTERS_URL = "https://anapioficeandfire.com/api/"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var featdXterRecyclerView: RecyclerView
    private lateinit var feedRecyclerView: RecyclerView
    private lateinit var featuredCharacterAdapter: FeaturedCharacterAdapter
    private lateinit var feedAdapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getCharacterData {characters ->
            featuredCharacterAdapter = FeaturedCharacterAdapter(characters)
            featdXterRecyclerView.adapter = featuredCharacterAdapter
            featdXterRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        featdXterRecyclerView = view.findViewById(R.id.profile_list)
        feedRecyclerView = view.findViewById(R.id.feed_list)
        featdXterRecyclerView.setHasFixedSize(true)
        populateFeed()
    }

    private fun populateFeed() {
        var feed1 = Feed(R.drawable.noun_arya_stark, "Arya Stark", R.drawable.noun_arya_stark, "Daughter of Eddard")
        var feed2 = Feed(R.drawable.noun_cersei_lannister, "Cersei Lannister", R.drawable.noun_cersei_lannister, "I am Queen Cersei")
        var feed3 = Feed(R.drawable.noun_house_arryn, "House Arryn", R.drawable.noun_house_arryn, "House of Arryn")
        var feed4 = Feed(R.drawable.noun_eddard_stark, "Eddard Stark", R.drawable.noun_eddard_stark, "I was beheaded for no reason")
        var feed5 = Feed(R.drawable.noun_grey_worm, "Grey Worm", R.drawable.noun_grey_worm, "I love Missandei")
        var feed6 = Feed(R.drawable.noun_daenerys_targaryen, "Daenerys Targaryen", R.drawable.noun_dragon, "My dragon! My love")

        val fList = mutableListOf(feed1,feed2,feed3,feed4,feed5,feed6)
        feedAdapter = FeedAdapter(fList)
        feedRecyclerView.adapter = feedAdapter
        feedRecyclerView.layoutManager = LinearLayoutManager(context)


    }

    private fun getCharacterData(callback: (List<Characters>) -> Unit) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CHARACTERS_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCharacters()
        retrofitData.enqueue(object : Callback<List<Characters>?> {
            override fun onResponse(call: Call<List<Characters>?>, response: Response<List<Characters>?>) {
                val responseBody = response.body() ?: emptyList()
                callback(responseBody)
            }

            override fun onFailure(call: Call<List<Characters>?>, t: Throwable) {
                Log.d("CharactersFragment", "onFailure: " + t.message)
                // figure out how to show Toast
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}