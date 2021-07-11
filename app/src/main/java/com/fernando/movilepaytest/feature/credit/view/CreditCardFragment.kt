package com.fernando.creditcard.feature.home.view

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
import com.fernando.creditcard.feature.home.model.CreditCardInfo
import com.fernando.creditcard.feature.home.viewmodel.CreditCardViewModel
import com.fernando.movilepaytest.MainActivity
import com.fernando.movilepaytest.R
import com.fernando.movilepaytest.databinding.FragmentCreditCardBinding
import com.fernando.movilepaytest.feature.credit.constants.CreditConstants
import com.fernando.movilepaytest.feature.credit.view.CreditCardState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreditCardFragment : Fragment() {
    private lateinit var bindViews: FragmentCreditCardBinding
    private lateinit var navController: NavController
    private val viewModel: CreditCardViewModel by viewModel()
    private var widgetArgs: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                findNavController().navigateUp()
                }
            }
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindViews = FragmentCreditCardBinding.inflate(layoutInflater, container , false)
        return bindViews.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        navController = Navigation.findNavController(view)
        widgetArgs = arguments?.getString(CreditConstants.KEY_WIDGET_ID)

        viewModel.getCreditDetails(widgetArgs)
    }

    private fun initObservers(){
        viewModel.creditCardState.observe(viewLifecycleOwner, Observer {
            when(it){
                is CreditCardState.CredicardStateSucess -> populateView(it.info)
                is CreditCardState.CredicardStateError -> {
                    findNavController().navigateUp()
                    (activity as MainActivity).onPressBackArrow(R.id.action_homeFragment_to_responseErrorFragment)
                }
            }
        })
    }

    private fun populateView(info: CreditCardInfo?) {
        info?.let {
            bindViews.apply {
                tvCardNumber.text = it.cardNumber
                tvCardName.text = it.cardName
                tvExpiration.text = CreditConstants.EXPIRATION_DATE.format(it.expirationDate)
                tvAvailableLimit.text = CreditConstants.AVAILABLE_LIMIT.format(it.availableLimit)
                tvTotalLimit.text = CreditConstants.TOTAL_LIMIT.format(it.totalLimit)
            }
        }
    }
}