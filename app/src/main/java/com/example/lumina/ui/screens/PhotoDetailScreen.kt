package com.example.lumina.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lumina.model.mockPhotos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(
    photoId: Int,
    onBackClick: () -> Unit
) {
    val photo = mockPhotos.find { it.id == photoId } ?: return
    val context = LocalContext.current
    var isRecipeExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { 
                    Toast.makeText(context, "Image saved to Analog Roll", Toast.LENGTH_SHORT).show() 
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "SAVE PHOTO",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.85f)
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
                    .padding(24.dp)
            ) {
                // Title Section
                Text(
                    text = photo.category.uppercase(),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Shot on ${photo.deviceName}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.Serif,
                    fontSize = 28.sp
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Specs Grid
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        SpecItem(label = "ISO", value = photo.iso)
                        Spacer(modifier = Modifier.height(24.dp))
                        SpecItem(label = "APERTURE", value = photo.aperture)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        SpecItem(label = "SHUTTER", value = photo.shutter)
                        Spacer(modifier = Modifier.height(24.dp))
                        SpecItem(label = "DEVICE", value = photo.deviceName)
                    }
                }
                
                Spacer(modifier = Modifier.height(40.dp))
                
                // Edit Recipe Dropdown
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isRecipeExpanded = !isRecipeExpanded },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Edit Recipe",
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Icon(
                                imageVector = if (isRecipeExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "Toggle Recipe",
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                        
                        AnimatedVisibility(
                            visible = isRecipeExpanded,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            Text(
                                text = photo.editRecipe,
                                fontSize = 14.sp,
                                lineHeight = 22.sp,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(100.dp)) // padding for FAB
            }
        }
    }
}

@Composable
fun SpecItem(label: String, value: String) {
    Column {
        Text(
            text = label.uppercase(),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
