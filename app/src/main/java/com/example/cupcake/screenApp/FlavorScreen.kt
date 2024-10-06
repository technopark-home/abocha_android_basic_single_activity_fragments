package com.example.cupcake.screenApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.itemViews.ButtonBox
import com.example.cupcake.ui.itemViews.RadioGroupPanel
import com.example.cupcake.ui.itemViews.TotalResult

@Composable
fun FlavorScreen(
    viewModel: OrderViewModel = viewModel(),
    navHostController: NavHostController
) {
    Column( modifier = Modifier
        .padding(dimensionResource(R.dimen.side_margin))
        .fillMaxSize()) {

        val listRadioButton = listOf(
            stringResource(R.string.vanilla),
            stringResource(R.string.chocolate),
            stringResource(R.string.red_velvet),
            stringResource(R.string.salted_caramel),
            stringResource(R.string.coffee)
        )
        val selectedOption by viewModel.flavor
        val price by viewModel.price

        RadioGroupPanel(listRadioButton, selectedOption) { text -> viewModel.setFlavor(text) }
        val price0 by remember { mutableStateOf(viewModel.price) }

        println("Select:${selectedOption}")
        println("price0:${price0}")
        println("price:${price}")

        TotalResult( price )

        ButtonBox({ navHostController.navigate(CupcakeRoute.PICKUP_ROUTE) }, {
            viewModel.resetOrder()
            navHostController.popBackStack(CupcakeRoute.START_ROUTE, inclusive = false) } )
    }
}