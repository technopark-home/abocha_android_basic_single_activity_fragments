package com.example.cupcake.ui.itemViews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cupcake.R

@Composable
fun TotalResult( total : String) {
    HorizontalDivider(
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.side_margin)),
    )

    Text(text = String.format( stringResource(R.string.subtotal_price), total),
        modifier = Modifier.fillMaxWidth(),
        textAlign =  TextAlign.Right,
        style = MaterialTheme.typography.bodyLarge)
}