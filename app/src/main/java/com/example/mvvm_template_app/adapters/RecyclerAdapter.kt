package com.example.mvvm_template_app.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_template_app.R
import com.example.mvvm_template_app.databinding.ItemNicePlaceBinding
import com.example.mvvm_template_app.databinding.UserListContentBinding
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.ui.UserDetailActivity
import com.example.mvvm_template_app.ui.UserDetailFragment
import com.example.mvvm_template_app.ui.UserListActivity

internal class RecyclerAdapter(
    private val parentActivity: UserListActivity,
    private val values: List<User>,
    private val twoPane: Boolean
) : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as User
            if (twoPane) {
                val fragment = UserDetailFragment()
                    .apply {
                        arguments = Bundle().apply {
                            putString(UserDetailFragment.ARG_USER_ID, item.id.toString())
                        }
                    }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.user_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, UserDetailActivity::class.java).apply {
                    putExtra(UserDetailFragment.ARG_USER_ID, item.id.toString())
                }
                v.context.startActivity(intent)
            }
        }
    }

    internal inner class CustomViewHolder(binding: UserListContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.idText
        val contentView: TextView = binding.content

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val binding = DataBindingUtil.inflate<UserListContentBinding>(
            LayoutInflater.from(parent.context),
            R.layout.user_list_content,
            parent,
            false)

        return CustomViewHolder(binding)
    }


    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.name

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }

        /*Glide
            .with(context)
            .load(item.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(holder.image)*/
    }
}