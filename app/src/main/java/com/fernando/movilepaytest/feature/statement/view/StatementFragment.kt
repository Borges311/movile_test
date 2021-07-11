package com.fernando.movilepaytest.feature.statement.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.movilepaytest.MainActivity
import com.fernando.movilepaytest.R
import com.fernando.movilepaytest.databinding.FragmentStatementBinding
import com.fernando.movilepaytest.feature.statement.constants.StatementConstants
import com.fernando.movilepaytest.feature.statement.model.Balance
import com.fernando.movilepaytest.feature.statement.viewmodel.StatementViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatementFragment : Fragment() {
    private lateinit var bindViews: FragmentStatementBinding
    private var widgetArgs: String? = null
    private val viewModel: StatementViewModel by viewModel()
    private val statementAdapter: StatementAdapter by lazy { StatementAdapter(requireContext()) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true) {
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
        bindViews = FragmentStatementBinding.inflate(layoutInflater, container, false)
        initObservers()
        return bindViews.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        widgetArgs = arguments?.getString(StatementConstants.KEY_WIDGET_ID)
        viewModel.getStatement(widgetArgs)
        initRecyclerView()
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is StatementState.StatementSuccess -> {
                    populateBalance(state.data?.balance)
                    state.data?.let { statementAdapter.updateTransaction(it.transactions) }

                }
                is StatementState.StatementError -> {
                    findNavController().navigateUp()
                    (activity as MainActivity).onPressBackArrow(R.id.action_homeFragment_to_responseErrorFragment)
                }
            }
        })
    }

    private fun initRecyclerView() {
        bindViews.rvTransaction.apply {
            layoutManager = LinearLayoutManager(view?.context)
            adapter = statementAdapter
        }
    }

    private fun populateBalance(balance: Balance?) {
        bindViews.apply {
            this.tvAvaliableBalance.text = balance?.label
            this.tvBalanceValue.text = balance?.value
        }
    }
}