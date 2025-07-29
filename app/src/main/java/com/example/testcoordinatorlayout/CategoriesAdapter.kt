package com.example.testcoordinatorlayout

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.updateMarginsRelative
import androidx.core.view.updatePaddingRelative
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(
    private val onClick: (Int) -> Unit
): ListAdapter<Int, CategoriesAdapter.Holder>(diffUtil) {

    inner class Holder(private val view: TextView): RecyclerView.ViewHolder(view) {

        fun bind(value: Int) {
            view.text = "Значение = $value"
            view.setOnClickListener {
                onClick(value)
            }
        }
    }



    companion object {
        private val diffUtil = object: DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
                return p0 == p1
            }

            override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
                return p0 == p1
            }

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        return Holder(TextView(p0.context).apply {
            layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT).apply {
                updateMarginsRelative(start = 30, end = 30)
            }
            updatePaddingRelative(start = 40, top = 20, bottom = 20)
            setBackgroundColor(ContextCompat.getColor(p0.context, R.color.categories))
            setTextColor(ContextCompat.getColor(p0.context, R.color.white))

        })
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(getItem(p1))
    }
}