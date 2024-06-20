package com.micharlie.healthcare.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micharlie.healthcare.R
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun HomeScreenSelector(navController: NavController) {
    var n by remember {
        mutableIntStateOf(1)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            n = Random.nextInt(1, 4)
        }
    }
    when (n) {
        1 -> {
            HomeScreenCard(
                navController = navController,
                text1 = stringResource(R.string.home_title1),
                text2 = stringResource(R.string.home_content1),
                imageId = R.drawable.homescreen_1
            )
        }
        2 -> {
            HomeScreenCard(
                navController = navController,
                text1 = stringResource(R.string.home_title2),
                text2 = stringResource(R.string.home_content2),
                imageId = R.drawable.homescreen_2
            )
        }
        else -> {
            HomeScreenCard(
                navController = navController,
                text1 = stringResource(R.string.home_title3),
                text2 = stringResource(R.string.home_content3),
                imageId = R.drawable.homescreen_3
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenCarouselSelector() {
    val navController = rememberNavController()
    HomeScreenSelector(navController)
}