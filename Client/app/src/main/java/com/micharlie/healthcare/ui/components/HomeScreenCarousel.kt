package com.micharlie.healthcare.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.micharlie.healthcare.R



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenCarousel(){
    val sliderList = listOf(
        HomeScreenCard(
            text1 = stringResource(R.string.home_title1),
            text2 = stringResource(R.string.home_content1),
            imageId = R.drawable.placeholder ),
        HomeScreenCard(
            text1 = stringResource(R.string.home_title2),
            text2 = stringResource(R.string.home_content2),
            imageId = R.drawable.placeholder ),
        HomeScreenCard(
            text1 = stringResource(R.string.home_title3),
            text2 = stringResource(R.string.home_content3),
            imageId = R.drawable.placeholder )

    )
    pagerState = rememberPagerState(1,0f) {

    }
    HorizontalPager(state = ) {
        
    }
}
@Composable
@Preview
fun HomeScreenCarouselPreview()
{
    HomeScreenCarousel()
}