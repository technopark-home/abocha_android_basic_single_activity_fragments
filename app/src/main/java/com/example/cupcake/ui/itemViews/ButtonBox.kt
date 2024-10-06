package com.example.cupcake.ui.itemViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.cupcake.R


@Composable
fun ButtonBox(onNext: () -> Unit, onCancel: () -> Unit) {
    Row( modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = dimensionResource(R.dimen.side_margin)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.side_margin))
    ) {
        OutlinedButton(onClick = { onCancel() },
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )
        { Text(
            stringResource(R.string.cancel).uppercase(),
            style = MaterialTheme.typography.labelMedium) }

        Button(
            onClick = { onNext() },
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        )
        { Text(
            stringResource(R.string.next).uppercase(),
            style = MaterialTheme.typography.labelMedium) }
    }
}
