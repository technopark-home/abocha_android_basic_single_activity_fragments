package com.example.cupcake.screenApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun StartScreen(navHostController: NavHostController, viewModel: OrderViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.side_margin))
            .wrapContentWidth()
            .fillMaxHeight()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.cupcake),
            contentDescription = "cupcake",
            modifier = Modifier.size(
                dimensionResource(R.dimen.image_size),
                dimensionResource(R.dimen.image_size)
            ).padding(top = dimensionResource(R.dimen.margin_between_elements))
                .align(alignment = Alignment.CenterHorizontally),
            alignment = Alignment.Center,
            contentScale = ContentScale.Inside
        )
        Text(
            stringResource(R.string.order_cupcakes),
            modifier = Modifier.wrapContentSize()
                .align(alignment = Alignment.CenterHorizontally),
            // material_on_background_emphasis_medium
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 34.sp,
        )
        val defFlavor = stringResource(R.string.vanilla)
        val listButtons = listOf(
            Pair( stringResource(R.string.one_cupcake)) {
                orderCupcake(1, defFlavor, navHostController, viewModel)
            },
            Pair( stringResource(R.string.six_cupcakes)) {
                orderCupcake(6, defFlavor, navHostController, viewModel)
            },
            Pair( stringResource(R.string.twelve_cupcakes)) {
                orderCupcake(12, defFlavor, navHostController, viewModel)
            })
        listButtons.forEach {
            ButtonsPanel( it.first, it.second )
        }
    }
}

@Composable
fun ButtonsPanel(textButton: String, onClickButton: () -> Unit ) {
    Column( modifier = Modifier
        .padding(top = dimensionResource(R.dimen.margin_between_elements))
        .wrapContentHeight()
        .fillMaxWidth()
    ) {
        Button(
            onClick = onClickButton,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier.wrapContentSize()
                .align(alignment = Alignment.CenterHorizontally)
                .defaultMinSize(minWidth = dimensionResource(R.dimen.order_cupcake_button_width))
        )
        { Text(textButton.uppercase(),
            style = MaterialTheme.typography.labelMedium) }
    }
}

fun orderCupcake(quantity :Int, defFlavor: String, navHostController: NavHostController, viewModel: OrderViewModel) {
    // Update the view model with the quantity
    viewModel.setQuantity(quantity)

    // If no flavor is set in the view model yet, select vanilla as default flavor
    if (viewModel.hasNoFlavorSet()) {
        viewModel.setFlavor(defFlavor)
    }

    // Navigate to the next destination to select the flavor of the cupcakes
    navHostController.navigate(CupcakeRoute.FLAVOR_ROUTE)
}