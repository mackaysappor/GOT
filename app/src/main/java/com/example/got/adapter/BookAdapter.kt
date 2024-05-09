package com.example.got.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.Books

class BookAdapter(private val bookList: List<Books>, private val onItemClick: (Books) -> Unit): RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var bookName: TextView
        var bookAuthor: TextView

        init {
            bookName = itemView.findViewById(R.id.txt_book_name)
            bookAuthor = itemView.findViewById(R.id.txt_book_author)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                onItemClick(bookList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val books = bookList[position]
        holder.bookName.text = books.name
        holder.bookAuthor.text = books.authors[0]

    }

    override fun getItemCount(): Int {
        return bookList.size
    }


}