package com.example.apitest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitest.viewmodel.DetailViewModel
import com.example.apitest.viewmodel.FollowerViewModel
import com.example.apitest.viewmodel.FragmentViewModel
//import com.example.apitest.viewmodel.FollowerViewModel
//import com.example.apitest.viewmodel.FragmentViewModel
import com.example.testapi.databinding.FragmentFolloBinding
import kotlin.properties.Delegates

class FolloFragment : Fragment() {

    private var _binding: FragmentFolloBinding? = null
    private val binding get() = _binding!!
    private var position by Delegates.notNull<Int>()
    private val followViewModel by viewModels<FragmentViewModel>()
    private val followingViewModel by viewModels<FollowerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFolloBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var username = ""
        arguments?.let {
            position = it.getInt(POSITION)
            username = it.getString(USERNAME)!!
        }
        if (position == 1) {
            subscribe1()
        } else {
            subscribe2()
        }
    }

    companion object {
        const val POSITION = "position"
        const val USERNAME = "username"
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.memuat.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.memuat.visibility = View.GONE
        }
    }

    private fun subscribe1() {
        val fragmentAdapter = ApiAdapter()
        binding.rvHero.adapter = fragmentAdapter
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvHero.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvHero.addItemDecoration(itemDecoration)

        followViewModel.apiDatasss.observe(viewLifecycleOwner) {
            fragmentAdapter.submitList(it)
        }
        followViewModel.isLoadingsss.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        followViewModel.showErrorsss.observe(viewLifecycleOwner) {
            binding.sectionLabel.isVisible = it
        }
        followViewModel.showFailedsss.observe(viewLifecycleOwner) {
            binding.sectionLabelFailed.isVisible = it
        }
    }

    private fun subscribe2() {
        val fragmentAdapter = ApiAdapter()
        binding.rvHero.adapter = fragmentAdapter
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvHero.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvHero.addItemDecoration(itemDecoration)

        followingViewModel.apiDatass.observe(viewLifecycleOwner) {
            fragmentAdapter.submitList(it)
        }
        followingViewModel.isLoadingss.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        followingViewModel.showErrorss.observe(viewLifecycleOwner) {
            binding.difollow.isVisible = it
        }
        followingViewModel.showFailedss.observe(viewLifecycleOwner) {
            binding.sectionLabelFailed.isVisible = it
        }
    }

}