package com.example.myapplication

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class ChildMemberAdapter(var memberData: List<MyJsonObject>) :
    RecyclerView.Adapter<ChildMemberAdapter.DataViewHolder>() {

    private var membersList: List<MyJsonObject> = ArrayList()

    init {
        this.membersList = memberData
    }


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
//                onItemClick?.invoke(membersList[adapterPosition].name)
            }
        }

    }

    class Category(private val title: String) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}