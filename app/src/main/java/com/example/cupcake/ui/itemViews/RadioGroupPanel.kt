package com.example.cupcake.ui.itemViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import com.example.cupcake.R


@Composable
fun RadioGroupPanel(languages : List<String>, selectedState : String, onClick : (text:String) -> Unit) {
    Column(
        Modifier
            .selectableGroup()
            .fillMaxWidth()
            .wrapContentHeight() ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        languages.forEach { text ->
            val isSelected = (text == selectedState)
            Row(modifier = Modifier
                .wrapContentSize()
                .padding(top = dimensionResource(R.dimen.side_margin))
                .selectable(
                    selected = isSelected,
                    onClick = { onClick(text) },
                    role = Role.RadioButton
                ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = null,
                    modifier = Modifier.
                    padding(horizontal = dimensionResource(R.dimen.radio_button_padding)),
                    colors = RadioButtonDefaults
                        .colors( selectedColor = MaterialTheme.colorScheme.secondaryContainer )
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}