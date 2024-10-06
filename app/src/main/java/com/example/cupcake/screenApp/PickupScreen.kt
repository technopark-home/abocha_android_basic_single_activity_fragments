package com.example.cupcake.screenApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.itemViews.ButtonBox
import com.example.cupcake.ui.itemViews.RadioGroupPanel
import com.example.cupcake.ui.itemViews.TotalResult

@Composable
fun PickupScreen(viewModel: OrderViewModel = viewModel(), navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.side_margin))
            .fillMaxSize()
    ) {
        val listRadioButton = listOf(
            viewModel.dateOptions[0],
            viewModel.dateOptions[1],
            viewModel.dateOptions[2],
            viewModel.dateOptions[3],
        )
        val selectedOption by viewModel.date
        val price by viewModel.price

        RadioGroupPanel(listRadioButton, selectedOption) { text -> viewModel.setDate(text) }
        TotalResult(price)
        ButtonBox({ navHostController.navigate(CupcakeRoute.SUMMARY_ROUTE) }, {
            viewModel.resetOrder()
            navHostController.popBackStack(CupcakeRoute.START_ROUTE, inclusive = false)
        } )
    }
}