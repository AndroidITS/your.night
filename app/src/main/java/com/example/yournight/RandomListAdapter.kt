package com.example.yournight

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RandomListAdapter(
    possibility: ArrayList<RandomList>,
    context: Context
) :
    RecyclerView.Adapter<RandomListAdapter.ViewHolder>() {
    private val mData: ArrayList<RandomList>
    private val mcontext: Context

    class ViewHolder(var viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_random_list, parent, false) as ViewGroup
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val mItem: RandomList = mData[position]
        val eliminaButton =
            holder.viewGroup.findViewById<Button>(R.id.eliminaButton)
        eliminaButton.setOnClickListener { removeItem(position) }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun removeItem(position: Int) {
        mData.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mData.size)
    }

    init {
        mData = possibility
        mcontext = context
    }
}
