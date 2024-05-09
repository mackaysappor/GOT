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
import com.example.got.adapter.BookAdapter
import com.example.got.models.Books
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val BOOKS_URL = "https://anapioficeandfire.com/api/"


/**
 * A simple [Fragment] subclass.
 * Use the [BooksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BooksFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var booksRecyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        getBookData { books ->
            bookAdapter = BookAdapter(books) {
                clickedItem -> showItemDetails(clickedItem)
            }
            booksRecyclerView.adapter = bookAdapter
            booksRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        booksRecyclerView = view.findViewById(R.id.booksRecyclerView)
        booksRecyclerView.setHasFixedSize(true)
    }

    private fun getBookData(callback: (List<Books>) -> Unit){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BOOKS_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getBooks()
        retrofitData.enqueue(object : Callback<List<Books>?> {
            override fun onResponse(call: Call<List<Books>?>, response: Response<List<Books>?>) {
                val responseBody = response.body() ?: emptyList()
                callback(responseBody)
            }

            override fun onFailure(call: Call<List<Books>?>, t: Throwable) {
                Log.d("BooksFragment", "onFailure: "+t.message)
            }
        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showItemDetails(book: Books) {
        val released = "Released"
        val pages = "Number of Pages"
        val publisher = "Publisher"
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

        itemImageView.setImageDrawable(itemImageView.context.getDrawable(R.drawable.book))
        titleNameTextView.text = book.name
        itemDetailTextView.text = book.authors[0]
        itemDetailTitleTextView1.text = released
        itemDetailTitleTextView2.text = pages
        itemDetailTitleTextView3.text = publisher
        itemDetailValueTextView1.text = book.released
        itemDetailValueTextView2.text = book.numberOfPages
        itemDetailValueTextView3.text = book.publisher

        // Close the dialog when the close button is clicked
        val closeButton = dialog.findViewById<TextView>(R.id.close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BooksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BooksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}