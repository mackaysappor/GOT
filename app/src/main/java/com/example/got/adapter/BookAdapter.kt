package com.example.got.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.Books

class BookAdapter(private val bookList: List<Books>): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var bookName: TextView
        var bookAuthor: TextView

        init {
            bookName = itemView.findViewById(R.id.txt_book_name)
            bookAuthor = itemView.findViewById(R.id.txt_book_author)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.books_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var books = bookList[position]
        holder.bookName.text = books.name
        holder.bookAuthor.text = books.authors.toString()
    }

    override fun getItemCount(): Int {
        return bookList.size
    }


}