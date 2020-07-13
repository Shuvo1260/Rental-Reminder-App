package org.binaryitplanet.rentalreminderapp.Features.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_particulars_item.view.*
import org.binaryitplanet.rentalreminderapp.Features.View.ParticularView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils

class ParticularListAdapter(
    val context: Context,
    val particularList: ArrayList<ParticularUtils>,
    val isNew: Boolean,
    var listener: ParticularView
):  RecyclerView.Adapter<ParticularListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParticularListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(
                    R.layout.view_particulars_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = particularList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        view.paymentType.text = particularList[position].paymentType
        view.monthYear.text = particularList[position].month + ", " +
                particularList[position].year
        view.date.text = particularList[position].date
        view.amount.text = particularList[position].amount.toString()
        view.rupeeSign.text = context.resources.getString(R.string.rupeeSign)
        view.remark.text = particularList[position].remark

        if (particularList[position].transactionType == "Debit") {
            view.amount.setTextColor(
                ContextCompat.getColor(context, R.color.red)
            )
            view.rupeeSign.setTextColor(
                ContextCompat.getColor(context, R.color.red)
            )
        }

        if (!isNew) {
            view.delete.visibility = View.GONE
        } else {
            view.delete.visibility = View.VISIBLE

            view.delete.setOnClickListener {
                listener.onParticularDeleteClick(position)
            }
        }

        if (position == particularList.size -1) {
            view.bottomMargin.visibility = View.VISIBLE
        }
    }

    // Holding the view
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {}

}