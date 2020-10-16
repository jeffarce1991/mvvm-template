package com.example.mvvm_template_app.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_template_app.R
import com.example.mvvm_template_app.adapters.RecyclerAdapter
import com.example.mvvm_template_app.databinding.ActivityUserListBinding
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.utils.hide
import com.example.mvvm_template_app.utils.show
import com.example.mvvm_template_app.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_list.view.*
import java.security.AccessController.getContext


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

        initializeRecyclerView()

        viewModel.getUsers()?.observe(this,
            Observer<MutableList<User>> {
                mAdapter.updateList(it)
                viewModel.setIsUpdating(false)
            })


        viewModel.getIsUpdating()?.observe(this,
            Observer {
                if(it!!){
                    showProgressBar()
                } else{
                    hideProgressBar()
                    binding.userList.smoothScrollToPosition(
                        viewModel.getUsers()!!.value!!.size -1)
                }
            })


        binding.fab.setOnClickListener {
            viewModel.addNewValue(User())
        }

    }

    private fun showProgressBar() {
        binding.progressBar.show()
    }

    private fun hideProgressBar() {
        binding.progressBar.hide()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    private fun initializeRecyclerView() {
        binding.userList.layoutManager = LinearLayoutManager(this)
        mAdapter = RecyclerAdapter(this, listOf(), twoPane)
        binding.userList.adapter = mAdapter
    }
}