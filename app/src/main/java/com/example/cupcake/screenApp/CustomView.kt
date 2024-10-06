package com.example.cupcake.screenApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.ui.theme.CupCakeTheme

class CustomView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CupCakeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ReverseFlowRow(
    mainAxisSpacing: Dp,
    crossAxisSpacing: Dp,
    content: @Composable () -> Unit
) = Layout(content) { measurables, constraints ->
    // 1. The measuring phase.
    val placeables = measurables.map { measurable ->
        measurable.measure(constraints)
    }

    // 2. The sizing phase.
    layout(constraints.maxWidth, constraints.maxHeight) {
        // 3. The placement phase.
        var yPosition = 0
        var xPosition = 0 //constraints.maxWidth

        placeables.forEach { placeable ->
            placeable.placeRelative(xPosition, yPosition)
            if (constraints.maxWidth>(placeable.width + xPosition + mainAxisSpacing.roundToPx())) {
                xPosition += (placeable.width + mainAxisSpacing.roundToPx())
                yPosition += (placeable.height + crossAxisSpacing.roundToPx())
            } else {
                yPosition += (placeable.height + crossAxisSpacing.roundToPx())
                xPosition = 0//constraints.maxWidth - placeable.width - mainAxisSpacing.roundToPx()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    ReverseFlowRow(mainAxisSpacing = 16.dp, crossAxisSpacing = 16.dp) {
        Text("First", fontSize = 20.sp, style = TextStyle(background = Color.Red))
        Text("Second", fontSize = 20.sp, style = TextStyle(background = Color.LightGray))
        Text("Third", fontSize = 20.sp, style = TextStyle(background = Color.Blue))
        Text("Fourth", fontSize = 20.sp, style = TextStyle(background = Color.Green))
        Text("Fifth", fontSize = 20.sp, style = TextStyle(background = Color.Gray))
        Text("Sixth", fontSize = 20.sp, style = TextStyle(background = Color.Yellow))
        Text("Seventh", fontSize = 20.sp, style = TextStyle(background = Color.Cyan))
        Text("Eight", fontSize = 20.sp, style = TextStyle(background = Color.Magenta))
        Text("Ninth", fontSize = 20.sp, style = TextStyle(background = Color.DarkGray))
    }
}

@Preview(showBackground = true)
@Composable
fun ReverseFlowRowPreview() {
    var elementItems = mutableListOf<String>()
    repeat(6) { it ->
        elementItems.add("Item:$it")
    }
    ReverseFlowRow(mainAxisSpacing = 8.dp, crossAxisSpacing = 8.dp) {
        elementItems.forEach {
            Text(it, fontSize = 20.sp)
        }
    }
}