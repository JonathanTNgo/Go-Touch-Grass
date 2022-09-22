package com.john.gotouchgrass.data
import com.john.gotouchgrass.model.GrassMoment

/**
 * An object to generate a static list of grass moments.
 */
object DataSource {

    val grassMoments: List<GrassMoment> = listOf(
        GrassMoment(
            10,
            "I got bit by a bug",
            "Okay"
        ),
        GrassMoment(
            100,
            "I had a fun time",
            "Good"
        ),
        GrassMoment(
            5,
            "I stepped in dog doo doo",
            "Bad"
        ),
        GrassMoment(
            20,
            "I had ice cream",
            "Good"
        ),
        GrassMoment(
            15,
            "I got sunburned",
            "Bad"
        ),
        GrassMoment(
            100,
            "I don't remember",
            "Okay"
        )
    )
}