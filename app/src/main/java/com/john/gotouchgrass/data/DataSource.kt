package com.john.gotouchgrass.data
import com.john.gotouchgrass.model.GrassMoment

/**
 * An object to generate a static list of grass moments.
 */
object DataSource {

    // This is a mutable list that will hold GrassMoments.
    val grassMoments: MutableList<GrassMoment> = mutableListOf()
}