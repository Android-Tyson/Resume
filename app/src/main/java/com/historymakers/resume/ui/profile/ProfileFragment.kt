package com.historymakers.resume.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.historymakers.resume.R
import com.historymakers.resume.model.Profile
import com.historymakers.resume.ui.profile.viewmodel.ProfileViewModel
import com.historymakers.resume.ui.profile.viewmodel.ProfileViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        viewModel.loader.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> progress_bar.visibility = View.VISIBLE
                else -> {
                    imageView_developer.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                }
            }
        }
        viewModel.profileLiveData.observe(viewLifecycleOwner) { profile ->
            if (profile.getOrNull() != null) {
                updateDetails(profile)
            } else {
                Snackbar.make(profile_root, R.string.generic_error, Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun updateDetails(profile: Result<Profile>) {
        textView_name.text = profile.getOrNull()!!.name
        textView_title.text = profile.getOrNull()!!.title
        textView_description.text = profile.getOrNull()!!.description
        glide.load(profile.getOrNull()!!.profile_image).into(imageView_developer)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }
}