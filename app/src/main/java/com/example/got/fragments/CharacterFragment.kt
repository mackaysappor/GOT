package com.example.got.fragments

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.`interface`.ApiInterface
import com.example.got.adapter.CharacterAdapter
import com.example.got.models.Characters
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
private const val CHARACTERS_URL = "https://anapioficeandfire.com/api/"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var characterRecyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var common: Common

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getCharacterData{ characters ->
            characterAdapter = CharacterAdapter(characters){
                    clickedItem -> showItemDetails(clickedItem)
            }
            characterRecyclerView.adapter = characterAdapter
            characterRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        common = Common()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        characterRecyclerView = view.findViewById(R.id.characterRecyclerView)
        characterRecyclerView.setHasFixedSize(true)
    }

    private fun getCharacterData(callback: (List<Characters>) -> Unit){
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
                Log.d("CharactersFragment", "onFailure: "+t.message)
            }
        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateDisplay(characters: Characters, bookNameList: List<String>) {
        val culture = "Culture"
        val gender = "Gender"

        Log.d("CharacterFragment", "List of Book: $bookNameList")
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

        if (characters.gender == "Male"){
            itemImageView.setImageDrawable(itemImageView.context.getDrawable(R.drawable.character_male))
        }
        else itemImageView.setImageDrawable(itemImageView.context.getDrawable(R.drawable.character_female))

        titleNameTextView.text = characters.name.ifBlank { characters.aliases[0] }
        itemDetailTextView.text = "Books\n" + bookNameList.joinToString (", ")
        itemDetailTitleTextView1.text = culture
        itemDetailTitleTextView2.text = gender
        itemDetailTitleTextView3.text = if (characters.aliases.isEmpty()) "Aliases" else "Title"
        itemDetailValueTextView1.text = characters.culture.ifEmpty { "Unknown" }
        itemDetailValueTextView2.text =  characters.gender
        itemDetailValueTextView3.text = if (characters.aliases.isEmpty()) characters.titles.firstOrNull() ?: "None" else characters.aliases[0]

        // Close the dialog when the close button is clicked
        val closeButton = dialog.findViewById<TextView>(R.id.close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showItemDetails(characters: Characters){
        val bookNameList = mutableListOf<String>()
        val totalRequests = characters.books.size
        var completedRequests = 0

        for (book in characters.books){
            val path = common.getPath(book)
            if (path != null) {
                common.getResponseData(path){ responseBody, error ->
                    if (error != null) {
                        Log.e("CharacterFragment", "Error fetching data: $error")
                    } else {
                        if (responseBody != null){
                            val response = JSONObject(responseBody)
                            val getBook = response.getString("name")
                            Log.d("CharacterFragment", "Data fetched successfully: $getBook")
                            bookNameList.add(getBook)
                        } else
                            Log.d("CharacterFragment", "Response is empty")
                    }

                    // Check if all requests have completed
                    completedRequests++
                    if (completedRequests == totalRequests) {
                        // All requests have completed, update UI
                        updateDisplay(characters, bookNameList)
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}