package com.example.cupcake.screenApp


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

@Composable
fun SummaryScreen(viewModel: OrderViewModel, onCancel : () -> Unit) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.side_margin))
            .wrapContentWidth()
            .fillMaxHeight()
    ) {
        RowOrder(
            stringResource(id = R.string.quantity),
            viewModel.quantityContent.value.toString()
        )
        RowOrder(
            stringResource(id = R.string.flavor),
            viewModel.flavor.value
        )
        RowOrder(
            stringResource(id = R.string.pickup_date),
            viewModel.date.value,
            bottomMargin = dimensionResource(R.dimen.margin_between_elements)
        )
        Text( text =
        String.format( stringResource(R.string.total_price).uppercase(),
            viewModel.price.value
        ),
            modifier = Modifier.fillMaxWidth(),
            textAlign =  TextAlign.Right,
            style = MaterialTheme.typography.titleLarge
        )

        val localContextCurrent = LocalContext.current

        Button(
            onClick = { sendOrder(viewModel, localContextCurrent) },
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp)
        )
        { Text(stringResource(id = R.string.send).uppercase(),
            style = MaterialTheme.typography.labelMedium) }

        OutlinedButton(onClick = onCancel,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp)
        )
        { Text(stringResource(R.string.cancel).uppercase(),
            style = MaterialTheme.typography.labelMedium) }
    }
}

@Composable
fun RowOrder( name: String, value: String, bottomMargin: Dp = dimensionResource(R. dimen. side_margin)) {
    Text(text = name.uppercase())
    Text(text = value,
        style = MaterialTheme.typography.labelMedium )
    SummaryDivider(bottomMargin = bottomMargin)
}

@Composable
fun SummaryDivider(
    topMargin: Dp = dimensionResource(R.dimen.side_margin),
    bottomMargin: Dp = dimensionResource(R.dimen.side_margin)
) {
    HorizontalDivider(
        modifier = Modifier
            .padding(
                top = topMargin,
                bottom = bottomMargin
            ),
        thickness = 1.dp
    )
}

/**
 * Submit the order by sharing out the order details to another app via an implicit intent.
 */
fun sendOrder(viewModel: OrderViewModel, localContextCurrent: Context) {
    // Construct the order summary text with information from the view model
    val numberOfCupcakes = viewModel.quantityContent.value
    val orderSummary = localContextCurrent.getString(
        R.string.order_details,
        localContextCurrent.resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        viewModel.flavor.value,
        viewModel.date.value,
        viewModel.price.value
    )

    // Create an ACTION_SEND implicit intent with order details in the intent extras
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, localContextCurrent.getString(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)
    // Check if there's an app that can handle this intent before launching it
    if (localContextCurrent.packageManager?.resolveActivity(intent, 0) != null) {
        // Start a new activity with the given intent (this may open the share dialog on a
        // device if multiple apps can handle this intent)
        localContextCurrent.startActivity(intent)
    }

}