package com.raghav.onboardingscreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

val pages = listOf(
    OnBoardingPageData("page-1", R.drawable.ic_launcher_background, false),
    OnBoardingPageData("page-2", R.drawable.ic_launcher_foreground, false),
    OnBoardingPageData("page-3", R.drawable.ic_launcher_background, true),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier, onBoardingCompleted: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        val pagerState = rememberPagerState { pages.size }
        HorizontalPager(state = pagerState) { page ->
            OnBoardingPage(data = pages[page], modifier = Modifier.fillMaxSize()) {
                onBoardingCompleted()
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp),
                )
            }
        }
    }
}

@Composable
fun OnBoardingPage(
    data: OnBoardingPageData,
    modifier: Modifier = Modifier,
    onBoardingCompleted: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = data.title)
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(id = data.image), contentDescription = null)
        if (data.isLast) {
            Button(onClick = { onBoardingCompleted() }) {
                Text(text = "Finish")
            }
        }
    }
}

data class OnBoardingPageData(
    val title: String,
    @DrawableRes
    val image: Int,
    val isLast: Boolean,
)
