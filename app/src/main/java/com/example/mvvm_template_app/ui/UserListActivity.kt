package com.example.mvvm_template_app.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_template_app.R
import com.example.mvvm_template_app.adapters.RecyclerAdapter
import com.example.mvvm_template_app.databinding.ActivityMainBinding
import com.example.mvvm_template_app.databinding.ActivityUserListBinding

import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_list.view.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [UserDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    private lateinit var mAdapter: RecyclerAdapter
    private lateinit var viewModel: MainViewModel
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var binding : ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)


        setupToolbar()

        /*binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/


        if (findViewById<NestedScrollView>(R.id.user_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getUsers()?.observe(this,
            Observer<MutableList<User>> {
                setupRecyclerView(findViewById(R.id.user_list), it)
            })


        viewModel.getIsUpdating()?.observe(this,
            Observer {
                if(it!!){
                    //showProgressBar()
                } else{
                    //hideProgressBar()
                    binding.frameLayout.user_list
                        .smoothScrollToPosition(
                            viewModel
                                .getUsers()!!.value!!.size -1)
                }
            })


        binding.fab.setOnClickListener {
            viewModel.addNewValue(User())
        }
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, users: MutableList<User>) {
        recyclerView.adapter =
            RecyclerAdapter(
                this,
                users,
                twoPane
            )
    }
}