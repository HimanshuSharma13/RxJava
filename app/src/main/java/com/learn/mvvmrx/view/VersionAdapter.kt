package com.learn.mvvmrx.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.learn.mvvmrx.R
import com.learn.mvvmrx.databinding.ItemVersionLayoutBinding
import com.learn.mvvmrx.model.Datum
import com.learn.mvvmrx.view.VersionAdapter.VersionAdapterViewHolder

class VersionAdapter : RecyclerView.Adapter<VersionAdapterViewHolder>() {
    private var mList: List<Datum?>? = null
    private var mInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionAdapterViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.context)
        }
        val binding: ItemVersionLayoutBinding = DataBindingUtil.inflate(mInflater!!, R.layout.item_version_layout, parent, false)
        return VersionAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VersionAdapterViewHolder, position: Int) {
        holder.binding.singleVersion = mList!![position]
    }

    override fun getItemCount(): Int {
        return if (mList != null && mList!!.size > 0) mList!!.size else 0
    }

    class VersionAdapterViewHolder(val binding: ItemVersionLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    fun showList(noteList: List<Datum?>?) {
        mList = noteList
        notifyDataSetChanged()
    }
}