package com.valentino.questionbank.shared.questions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valentino.questionbank.R
import com.valentino.questionbank.model.Question
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionsAdapter(private val questions: List<Question>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var questionData = questions.toMutableList()

    override fun getItemCount(): Int {
        return  questionData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionsViewHolder(root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = questionData[position]
        val placeHolder = holder as QuestionsViewHolder
        placeHolder.bindData(data, position)
    }

    fun addItems(items: List<Question>) {
        questionData.clear()
        questionData.addAll(items)
        notifyDataSetChanged()
    }

    class QuestionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView

        fun bindData(question: Question, position: Int) {
            view.titleTextView.text = String.format("Question %d:", position + 1)
            view.contentTextView.text = question.content
            view.tagsTextView.text = String.format("Tags: %s", question.tags.joinToString(", "))
        }
    }
}