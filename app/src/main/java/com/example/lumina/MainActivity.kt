package com.example.lumina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lumina.ui.screens.GalleryScreen
import com.example.lumina.ui.screens.PhotoDetailScreen
import com.example.lumina.ui.theme.LuminaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuminaTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    val bgImage = if (isSystemInDarkTheme()) {
                        R.drawable.dark_nebula_bg
                    } else {
                        R.drawable.light_crystal_bg
                    }
                    Image(
                        painter = painterResource(id = bgImage),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f) // Glass effect
                    ) {
                        LuminaApp()
                    }
                }
            }
        }
    }
}

@Composable
fun LuminaApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "gallery", modifier = Modifier.fillMaxSize()) {
        composable("gallery") {
            GalleryScreen(
                onPhotoClick = { photoId ->
                    navController.navigate("photo_detail/$photoId")
                }
            )
        }
        composable(
            route = "photo_detail/{photoId}",
            arguments = listOf(navArgument("photoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val photoId = backStackEntry.arguments?.getInt("photoId") ?: return@composable
            PhotoDetailScreen(
                photoId = photoId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}