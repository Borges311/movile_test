package com.fernando.movilepaytest.feature.error.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fernando.movilepaytest.databinding.FragmentResponseErrorBinding

class ResponseErrorFragment : Fragment() {
    private lateinit var bindViews: FragmentResponseErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(false) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindViews = FragmentResponseErrorBinding.inflate(layoutInflater, container, false)
        return bindViews.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews.imgClose.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}