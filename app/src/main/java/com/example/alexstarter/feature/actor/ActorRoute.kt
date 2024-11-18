package com.example.alexstarter.feature.actor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.alexstarter.designsystem.AppScaffold
import com.example.alexstarter.designsystem.Spacer
import com.example.alexstarter.designsystem.appbar.TopBar
import com.example.alexstarter.designsystem.message.ErrorMessage
import com.example.alexstarter.designsystem.text.Text
import com.example.alexstarter.ui.theme.DarkBlue

@Composable
fun ActorRoute(
    actorId: String,
    onBackClick: () -> Unit,
    viewModel: ActorViewModel = hiltViewModel(),
){
    val actorState by viewModel.actorState.collectAsStateWithLifecycle()
    ActorScreen(
        onBackClick = onBackClick,
        actorState = actorState
    )

    LaunchedEffect(actorId) {
        viewModel.fetchActorDetails(actorId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActorScreen(
    onBackClick: () -> Unit,
    actorState: ActorState
){
    AppScaffold(
        topBar = { TopBar(
            onBackClick = onBackClick
        ) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = DarkBlue)
        ) {
            when(actorState){
                is ActorState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorMessage(text = "Oh no something went wrong !")
                    }
                }
                is ActorState.Loaded -> {
                    val actor = actorState.actor
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Log.d("ACTORROUTE", "${actor.profilePath}")
                        AsyncImage(
                            modifier = Modifier
                                .height(350.dp)
                                .graphicsLayer { alpha = 0.99f }
                                .drawWithContent {
                                    val colors = listOf(
                                        Color.Black,
                                        Color.Transparent
                                    )
                                    drawContent()
                                    drawRect(
                                        brush = Brush.verticalGradient(colors),
                                        blendMode = BlendMode.DstIn
                                    )
                                },
                            model = actor.profilePath,
                            contentScale = ContentScale.Crop,
                            contentDescription = "image of ${actor.name}"
                        )
                    }

                    Column(modifier = Modifier.padding(8.dp)) {

                        Spacer.Vertical.Default()
                        Text.Small(text = actor.biography)
                        Spacer.Vertical.Default()
                        Text.Default(text = "Date d'anniversaire : ${actor.birthday}")
                        Spacer.Vertical.Default()
                        Text.Default(text = "Status : ${actor.gender}")
                        Spacer.Vertical.Default()

                    }

                }
                ActorState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ActorRoutePreview(){
    ActorScreen(
        onBackClick = {},
        actorState = ActorState.Loading
    )
}