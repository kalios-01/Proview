package com.kaliostech.proview.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.kaliostech.proview.R
import androidx.viewpager2.widget.ViewPager2

class FirstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.findViewById<Button>(R.id.fsnext).setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }

}