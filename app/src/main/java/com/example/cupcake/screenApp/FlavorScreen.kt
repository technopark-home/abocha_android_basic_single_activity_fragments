package com.example.cupcake.screenApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel
import com.example.cupcake.ui.itemViews.ButtonBox
import com.example.cupcake.ui.itemViews.RadioGroupPanel
import com.example.cupcake.ui.itemViews.TotalResult

@Composable
fun FlavorScreen(
    viewModel: OrderViewModel,
    onCancel : () -> Unit,
    onNext: () -> Unit
) {
    Column( modifier = Modifier
        .padding(dimensionResource(R.dimen.side_margin))
        .fillMaxSize()) {
        val localContextCurrent = LocalContext.current
        val listRadioButton = remember {
            mutableStateListOf(
                localContextCurrent.getString(R.string.vanilla),
                localContextCurrent.getString(R.string.chocolate),
                localContextCurrent.getString(R.string.red_velvet),
                localContextCurrent.getString(R.string.salted_caramel),
                localContextCurrent.getString(R.string.coffee)
            )
        }
        val selectedOption by viewModel.flavor
        val price by viewModel.price

        RadioGroupPanel(listRadioButton, selectedOption) { text -> viewModel.setFlavor(text) }

        TotalResult( price )

        ButtonBox( onNext, onCancel )
    }
}