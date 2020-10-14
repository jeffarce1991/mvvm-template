package com.example.mvvm_template_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_template_app.R
import com.example.mvvm_template_app.databinding.ActivityUserDetailBinding
import com.example.mvvm_template_app.databinding.UserDetailsBinding
import com.example.mvvm_template_app.models.User
import com.example.mvvm_template_app.viewmodels.MainViewModel

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a [UserListActivity]
 * in two-pane mode (on tablets) or a [UserDetailActivity]
 * on handsets.
 */
class UserDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: User? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var activityBinding: ActivityUserDetailBinding
    private lateinit var detailsBinding: UserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_USER_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = getUser()
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    it.getString(ARG_USER_ID)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.user_details, container, false)
        activityBinding = DataBindingUtil.inflate(
            inflater, R.layout.activity_user_detail, container, false);

        detailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.user_details, container, false);

        viewModel = activity?.run { ViewModelProvider(activity!!)[MainViewModel::class.java] }
            ?: throw Exception("DEBUG: Invalid Activity")


        viewModel.user.observe(viewLifecycleOwner, Observer {
            println("DEBUG: ${it.name}")
            setUserDetails(it)
            activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                it.username
        })

        viewModel.setUserId(arguments!!.getString(ARG_USER_ID)!!.toInt())

        return detailsBinding.root
    }

    private fun setUserDetails(user: User) {
        detailsBinding.name.text = user.name
        detailsBinding.email.text = user.email
        detailsBinding.phone.text = user.phone
        detailsBinding.location.text = String
            .format("${user.address.suite}, ${user.address.street}, ${user.address.city}" )
        detailsBinding.company.text = user.company.name
        detailsBinding.catchPhrase.text = user.company.catchPhrase
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_USER_ID = "user_id"
    }
}