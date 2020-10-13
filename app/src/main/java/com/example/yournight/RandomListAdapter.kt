package com.example.yournight

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_random.*
import java.util.*

class RandomListAdapter(
    possibility: ArrayList<String>,
    context: Context
) :
    RecyclerView.Adapter<RandomListAdapter.ViewHolder>() {
    private val mData: ArrayList<String>
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
        val mItem: String = mData[position]
        val eliminaButton =
            holder.viewGroup.findViewById<Button>(R.id.eliminaButton)
        eliminaButton.setOnClickListener { removeItem(position) }
        val titleView = holder.viewGroup.findViewById<EditText>(R.id.titleView)
        titleView.addTextChangedListener(
            object: TextWatcher {
                override fun afterTextChanged(s: Editable) {
                }

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    mData[position] = titleView.text.toString()
                }
            }
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun removeItem(position: Int) {
        mData[position] = ""
        mData.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mData.size)
    }

    init {
        mData = possibility
        mcontext = context
    }
}
