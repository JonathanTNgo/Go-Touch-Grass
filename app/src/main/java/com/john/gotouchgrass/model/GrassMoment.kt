package com.john.gotouchgrass.model

import java.util.*

/**
 * This data type will be used to in the RecyclerView that will display past grass moments.
 */
data class GrassMoment(
    // The time a user spent outside.
    val timeSpentOutside: Int,
    // The user's written description of their time outside.
    val description: String,
    // The rating a user gave their time outside (OKAY, BAD, GREAT).
    val rating: String,
)
