package com.dicoding.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.databinding.FragmentFollowBinding
import com.dicoding.githubuser.viewmodel.FollowFragmentViewModel

class FollowFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListUserAdapter()
        binding?.rvUsers?.adapter = adapter

        var position = 0
        var username = ""
        val followFragmentViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowFragmentViewModel::class.java)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME) ?: ""
        }
        if (position == 1){
            followFragmentViewModel.findListFollowers(username)
            followFragmentViewModel.listFollowers.observe(viewLifecycleOwner){followers ->
                adapter.submitList(followers)
            }
            followFragmentViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        } else {
            followFragmentViewModel.findListFollowing(username)
            followFragmentViewModel.listFollowing.observe(viewLifecycleOwner){following ->
                adapter.submitList(following)
            }
            followFragmentViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }

        showRec()
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    private fun showRec() {
        val layoutManager = LinearLayoutManager(requireActivity())
        binding?.rvUsers?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding?.rvUsers?.addItemDecoration(itemDecoration)
    }

}