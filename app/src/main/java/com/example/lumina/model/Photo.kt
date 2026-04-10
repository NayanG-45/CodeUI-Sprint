package com.example.lumina.model

data class Photo(
    val id: Int,
    val imageUrl: String,
    val category: String,
    val deviceName: String,
    val photographer: String,
    val iso: String,
    val shutter: String,
    val aperture: String,
    val editRecipe: String
)

val mockPhotos = listOf(
    Photo(1, "https://images.unsplash.com/photo-1472214103451-9374bd1c798e?auto=format&fit=crop&q=80&w=1000", "Nature", "Sony A7IV", "Emily Chen", "100", "1/200s", "f/2.8", "Contrast +12\nHighlights -20\nShadows +15\nSaturation -5"),
    Photo(2, "https://images.unsplash.com/photo-1550684848-fac1c5b4e853?auto=format&fit=crop&q=80&w=1000", "Abstract", "Fujifilm X-T4", "Marcus Webb", "400", "1/60s", "f/4.0", "Film Simulation: Classic Chrome\nGrain: Strong\nClarity +5"),
    Photo(3, "https://images.unsplash.com/photo-1449824913935-59a10b8d2000?auto=format&fit=crop&q=80&w=1000", "Urban", "Leica Q2", "Julia Frost", "200", "1/500s", "f/1.7", "B&W High Contrast\nClarity +10\nShadows -10"),
    Photo(4, "https://images.unsplash.com/photo-1495616811223-4d98c6e9c869?auto=format&fit=crop&q=80&w=1000", "Sunset", "Canon EOS R5", "Alex Morgan", "100", "1/100s", "f/8.0", "Temperature: Warm\nShadows +30\nVibrance +15\nHighlights -10"),
    Photo(5, "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&q=80&w=1000", "Architecture", "Sony A7SIII", "David Li", "800", "1/125s", "f/5.6", "Highlights -40\nGeometry Correction: Auto\nWhites +10"),
    Photo(6, "https://images.unsplash.com/photo-1469474968028-56623f02e42e?auto=format&fit=crop&q=80&w=1000", "Landscape", "Nikon Z9", "Sarah Jones", "64", "1/1000s", "f/4.0", "Dehaze +15\nSaturation +10\nTexture +5")
)
