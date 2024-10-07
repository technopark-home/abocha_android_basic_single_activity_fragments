package com.example.cupcake.screenApp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.R
import com.example.cupcake.model.OrderViewModel

object CupcakeRoute {
    const val START_ROUTE = "startScreen"
    const val FLAVOR_ROUTE = "flavorScreen"
    const val PICKUP_ROUTE = "pickupScreen"
    const val SUMMARY_ROUTE = "summaryScreen"
}

@Composable
fun AppCupcakeNavHost(viewModel: OrderViewModel) {
    val navController = rememberNavController()
    val enterTransitionScaleIn : (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
            = { scaleIn(
        tween(1000), 0f, TransformOrigin(0f, .0f)
    ) }
    val enterTransitionScaleOut : (AnimatedContentTransitionScope<NavBackStackEntry>) -> ExitTransition
            = { scaleOut(
        tween(1000), 0f, TransformOrigin(1f, .1f)
    ) }
    val enterTransitionFadeIn : (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
            = { fadeIn(tween(1000), 0f) }
    val enterTransitionFadeOut : (AnimatedContentTransitionScope<NavBackStackEntry>) -> ExitTransition
            = { fadeOut(tween(1000), 0f) }

    val enterTransitionExpandIn : (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)
            = { expandIn(tween(1000), Alignment.TopStart) { IntSize(0, 0) } }
    val enterTransitionShrinkOut : (AnimatedContentTransitionScope<NavBackStackEntry>) -> ExitTransition
            = { shrinkOut(tween(1000), Alignment.BottomEnd) { it / 10 } }

    val onHome : () -> Unit = {
        navController.popBackStack(CupcakeRoute.START_ROUTE, inclusive = false)
        UInt
    }
    val onCancel : () -> Unit = {
        viewModel.resetOrder()
        navController.popBackStack(CupcakeRoute.START_ROUTE, inclusive = false)
    }
    val onNextFlavor : () -> Unit = { navController.navigate(CupcakeRoute.PICKUP_ROUTE) }
    val onNextPickup : () -> Unit = { navController.navigate(CupcakeRoute.SUMMARY_ROUTE) }

    NavHost( navController = navController, startDestination = CupcakeRoute.START_ROUTE ) {
        composable(CupcakeRoute.START_ROUTE,
            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }
        ) {
            CupcakeMainScreen( titleLabel = stringResource(id = R.string.app_name) ) {
                StartScreen(
                    navHostController = navController,
                    viewModel = viewModel
                )
            }

        }
        composable(CupcakeRoute.FLAVOR_ROUTE,
            enterTransition = enterTransitionFadeIn,
            exitTransition = enterTransitionFadeOut
        ) {
            CupcakeMainScreen(
                onHome,
                titleLabel = stringResource(id = R.string.choose_flavor)
            ) {
                FlavorScreen(viewModel = viewModel, onCancel, onNextFlavor )
            }
        }
        composable(
            CupcakeRoute.PICKUP_ROUTE,
            enterTransition = enterTransitionExpandIn,
            exitTransition = enterTransitionShrinkOut
        ) {
            CupcakeMainScreen(
                onHome,
                stringResource(id = R.string.choose_pickup_date)
            ) {
                PickupScreen( viewModel = viewModel, onCancel, onNextPickup )
            }
        }
        composable(
            CupcakeRoute.SUMMARY_ROUTE,
            enterTransition = enterTransitionScaleIn,
            exitTransition = enterTransitionScaleOut
        ) {
            CupcakeMainScreen(
                onHome,
                stringResource(id = R.string.order_summary)
            ) {
                SummaryScreen( viewModel = viewModel, onCancel )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeMainScreen( onHome: (() -> Unit)? = null, titleLabel: String, content: @Composable () -> Unit) {
    val colors = TopAppBarColors (
        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
        actionIconContentColor = MaterialTheme.colorScheme.onSurface,
    )
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(titleLabel)},
                navigationIcon = {
                    if (onHome!=null) {
                        IconButton(onClick = onHome) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Back"
                            )}
                    }
                },
                colors = colors,
            )
        }
    ) {
            paddingValues ->
        Surface(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues),
            content = content
        )
    }
}