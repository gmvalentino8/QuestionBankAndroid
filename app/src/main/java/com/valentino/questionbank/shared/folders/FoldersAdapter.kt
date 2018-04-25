package com.valentino.questionbank.shared.folders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valentino.questionbank.R
import com.valentino.questionbank.model.Folder
import kotlinx.android.synthetic.main.item_folder.view.*

class FoldersAdapter(private val folders: List<Folder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return  folders.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false)
        return FoldersViewHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = folders[position]
        val placeHolder = holder as FoldersViewHolder
        placeHolder.bindData(data)
    }

    class FoldersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView

        fun bindData(folder: Folder) {
            view.nameTextView.text = folder.name
            view.descriptionTextView.text = folder.description
        }
    }
}