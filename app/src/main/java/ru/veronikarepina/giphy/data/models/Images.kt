package ru.veronikarepina.giphy.data.models

import androidx.room.Embedded

data class Images(
    @Embedded
    val downsized: Downsized,
    @Embedded
    val downsized_large: DownsizedLarge,
    @Embedded
    val downsized_medium: DownsizedMedium,
    @Embedded
    val original: Original,
)
