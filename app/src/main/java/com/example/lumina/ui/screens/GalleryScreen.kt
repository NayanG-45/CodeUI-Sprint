package com.example.lumina.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lumina.model.Photo
import com.example.lumina.model.mockPhotos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    onPhotoClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LUMINA",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 6.sp,
                        fontSize = 30.sp,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        style = androidx.compose.ui.text.TextStyle(
                            shadow = androidx.compose.ui.graphics.Shadow(
                                color = Color.Black.copy(alpha = 0.5f),
                                offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                                blurRadius = 8f
                            )
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        val pagerState = rememberPagerState(pageCount = { mockPhotos.size })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                pageSpacing = 16.dp,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val photo = mockPhotos[page]
                FilmSlideCard(photo = photo, onClick = { onPhotoClick(photo.id) })
            }
        }
    }
}

@Composable
fun FilmSlideCard(photo: Photo, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .clickable { onClick() }
            .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.graphics.painter.ColorPainter(Color.DarkGray),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                AsyncImage(
                    model = photo.imageUrl,
                    contentDescription = photo.category,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Category Tag in container
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                        .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = photo.category.uppercase(),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Shot on ${photo.deviceName}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
                
                Spacer(modifier = Modifier.height(6.dp))
                
                Text(
                    text = "By ${photo.photographer}",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
