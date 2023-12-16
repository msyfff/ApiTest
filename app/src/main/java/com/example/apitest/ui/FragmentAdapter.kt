package com.example.apitest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(activity: AppCompatActivity, users: String) : FragmentStateAdapter(activity) {

    private var username: String = users

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FolloFragment()
        fragment.arguments = Bundle().apply {
            putInt(FolloFragment.POSITION, position + 1)
            putString(FolloFragment.USERNAME, username)
        }
        return fragment
    }
}