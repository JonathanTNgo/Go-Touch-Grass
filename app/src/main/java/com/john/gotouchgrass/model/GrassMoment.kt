package com.john.gotouchgrass.model

import android.graphics.Bitmap
import android.net.Uri

/**
 * This data type will be used to in the RecyclerView that will display past grass moments.
 */
data class GrassMoment(
    // The time a user spent outside.
    val timeSpentOutside: Double,
    // The user's written description of their time outside.
    val description: String,
    // The user's activity image
    val activityImage: Uri?
)
