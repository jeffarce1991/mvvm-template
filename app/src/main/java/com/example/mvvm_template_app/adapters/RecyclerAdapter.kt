package com.example.mvvm_template_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_template_app.R
import com.example.mvvm_template_app.databinding.ItemNicePlaceBinding
import com.example.mvvm_template_app.models.User

internal class RecyclerAdapter(
    private val context: Context,
    private val dataList: List<User>?
) : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    internal inner class CustomViewHolder(binding: ItemNicePlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemLayout: ConstraintLayout = binding.itemLayout
        var title: TextView = binding.customRowTitle
        val image: ImageView = binding.image

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val binding = DataBindingUtil.inflate<ItemNicePlaceBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_nice_place,
            parent,
            false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = this.dataList!![position]
        holder.title.text = item.name

        /*Glide
            .with(context)
            .load(item.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(holder.image)*/
    }
}