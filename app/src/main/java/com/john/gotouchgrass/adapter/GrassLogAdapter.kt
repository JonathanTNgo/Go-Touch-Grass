package com.john.gotouchgrass.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.john.gotouchgrass.R
import com.john.gotouchgrass.data.DataSource
import com.john.gotouchgrass.model.GrassMoment
import java.util.*
import kotlin.math.roundToInt

class GrassLogAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<GrassLogAdapter.GrassViewHolder>() {

    // The global grass moment list is initialized.
    private val grassList: List<GrassMoment> = DataSource.grassMoments

    /**
     * Initialize view elements
     */
    class GrassViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val timeOutside: TextView? = view?.findViewById(R.id.outside_time)
        val describeOutside: TextView? = view?.findViewById(R.id.outside_description)
        val imageOutside: ImageView? = view?.findViewById(R.id.activity_image)
        val dateOutside: TextView? = view?.findViewById(R.id.date_outside)
    }

    /**
     * The onCreateViewHolder will initialize the DogCardViewHolder based on the layout
     * view selected by the user (whatever button the user clicks will be the view).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrassViewHolder {
        // The layout is inflated with the selected layout.
        val adaptedLayout = LayoutInflater.from(parent.context).inflate(R.layout.log_screen_item, parent, false)

        // The ViewHolder is created and returned.
        return GrassViewHolder(adaptedLayout)
    }

    // This function will return the size of the dataset.
    override fun getItemCount() = grassList.size

    /**
     * The elements being displayed in the View are changed depending on the
     * current position in the global array.
     */
    override fun onBindViewHolder(holder: GrassViewHolder, position: Int) {
        // This is the grass item at the current position.
        val item = grassList[position]
        val res = context?.resources
        // The time outside is set.
        holder.timeOutside?.text = res?.getString(R.string.time_spent, getTimeSpent(item.timeSpentOutside))
        // The outside description is set.
        holder.describeOutside?.text = item.description
        // The image is set.
        holder.imageOutside?.setImageURI(item.activityImage)
        // The date outside is set. The month returned is zero-indexed, so 1 is added to it.
        holder.dateOutside?.text = res?.getString(R.string.date_string, item.date?.get(Calendar.MONTH)?.plus(1), item.date?.get(Calendar.DATE), item.date?.get(Calendar.YEAR))
    }

    private fun getTimeSpent(timeSeconds: Double): String {
        if (timeSeconds <= 59.0) {
            val sec = String.format("%.0f", timeSeconds)
            return "$sec seconds"
        } else if (timeSeconds == 60.0) {
            return "${timeSeconds as Int / 60} minute"
        } else if (timeSeconds > 60.0 && timeSeconds <= 3599.0) {
            val min = String.format("%f", timeSeconds / 60)
            val sec = String.format("%.2f", timeSeconds % 60)
            return "$min minutes and $sec second(s)"
        } else if (timeSeconds == 3600.00) {
            val hr = String.format("%.2f", timeSeconds / 60 / 60)
            return "$hr hour"
        }

        val hr = String.format("%.2f", timeSeconds / 60 / 60)
        val min = String.format("%.2f", timeSeconds / 60 % 60)
        return "$hr hours and $min minute(s)"
    }
}