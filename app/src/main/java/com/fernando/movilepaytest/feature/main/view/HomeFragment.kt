package com.fernando.movilepaytest.feature.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.movilepaytest.R
import com.fernando.movilepaytest.databinding.FragmentHomeBinding
import com.fernando.movilepaytest.feature.credit.constants.CreditConstants
import com.fernando.movilepaytest.feature.main.constants.HomeConstants
import com.fernando.movilepaytest.feature.main.model.SimpleWidget
import com.fernando.movilepaytest.feature.main.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var bindingView: FragmentHomeBinding
    private lateinit var navController: NavController
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter(requireContext(), this::onClick) }
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingView = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initObservers()
        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initRecyclerView()
        homeViewModel.fetchHomeWidgets()
    }

    private fun initObservers() {
        homeViewModel.homeState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is HomeState.WelcomeWidgetState -> setWelcomeMessage(it.homeWelcome)
                is HomeState.HomeErrorRequest -> {
                    navController.navigate(R.id.action_homeFragment_to_responseErrorFragment)
                }
                else -> setWelcomeMessage(getString(R.string.default_welcome))
            }
        })

        homeViewModel.widgetState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is HomeState.WidgetState -> {
                    homeAdapter.updateWidgets(it.widgetList)

                }
                is HomeState.HomeErrorRequest -> {
                    navController.navigate(R.id.action_homeFragment_to_responseErrorFragment)
                }
            }
        })
    }

    private fun initRecyclerView() {
        bindingView.rvHome.apply {
            layoutManager = LinearLayoutManager(view?.context)
            adapter = homeAdapter
        }
    }

    private fun onClick(widget: SimpleWidget?) {
        val bundle = bundleOf()
        widget?.buttonContent.let {
            bundle.putString(CreditConstants.KEY_WIDGET_ID, it)
        }
        when (widget?.buttonAction) {
            HomeConstants.CARD_SCREEN -> {
                navController.navigate(R.id.action_homeFragment_to_CreditCardFragment, bundle)
            }
            HomeConstants.STATEMENT_SCREEN -> {
                navController.navigate(R.id.action_homeFragment_to_statementFragment, bundle)
            }
        }
    }

    private fun setWelcomeMessage(msg: String?) {
        bindingView.tvWelcome.text = msg
    }
}