package com.example.cupcake.screenApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun OffsetRowElements(
    content: @Composable () -> Unit
) = Layout(content) { measurePolicy, constraints ->
    val placeable = measurePolicy.map { measurable ->
        measurable.measure(constraints)
    }

    layout(constraints.maxWidth, constraints.maxHeight) {
        var yPosition = 0
        var xPosition = 0

        placeable.forEach { placeable ->
            placeable.placeRelative(xPosition, yPosition)
            if (constraints.maxWidth>(placeable.width + xPosition)) {
                xPosition += (placeable.width)
                yPosition += (placeable.height)
            } else {
                yPosition += (placeable.height)
                xPosition = 0
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OffsetRowElementsPreview() {
    val elementItems = remember {
        mutableStateListOf(
            "Sharik", "Bobik", "Gena", "Buka", "Buba"
        )
    }
    OffsetRowElements {
        elementItems.forEach {
            Text(it, fontSize = 20.sp)
        }
    }
}