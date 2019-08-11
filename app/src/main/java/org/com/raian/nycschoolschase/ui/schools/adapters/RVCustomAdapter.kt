package org.com.raian.nycschoolschase.ui.schools.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.com.raian.nycschoolschase.R
import org.com.raian.nycschoolschase.constants.GlobalConstants
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.arrCities
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.arrImagesCities
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.latAndLong
import org.com.raian.nycschoolschase.database.model.UIDataClass
import org.com.raian.nycschoolschase.ui.schools.fragments.MapsFragment
import java.util.logging.Logger

class RVCustomAdapter(
    private var context: Context,
    private var lstRes: LiveData<List<UIDataClass>>?,
    private var fragmentManager: FragmentManager?
) :
    RecyclerView.Adapter<RVCustomAdapter.ViewHolder>() {

    private var TAG = RVCustomAdapter::class.java.simpleName
    private var logger: Logger

    init {
        logger = Logger.getLogger(TAG)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_city, p0, false))
    }

    override fun getItemCount(): Int {

        return lstRes?.value?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        lstRes?.value?.get(position)?.let { viewHolder.binData(it) }
        //This is just for displaying the details
        viewHolder.mButtonDetails.setOnClickListener {
            val expanded = lstRes?.value?.get(position)?.isExpanded
            expanded?.let {
                lstRes?.value?.get(position)?.isExpanded = !it
                notifyItemChanged(position)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mImageViewCity: ImageView
        private var mTextViewSchoolName: TextView
        private var mTextViewOverviewParagraph: TextView
        private var mTextViewCity: TextView
        private var mImageViewGotoLocation: ImageView
        private var mLinearLayoutContainerSubItem: View
        private var mTextViewCriticalReadingAvgScore: TextView
        private var mTextViewMathAvgScore: TextView
        private var mTextViewWritingAvgScore: TextView
        internal var mButtonDetails: Button

        init {
            itemView.let {
                mImageViewCity = it.findViewById(R.id.mImageViewCity)
                mTextViewSchoolName = it.findViewById(R.id.mTextViewSchoolName)
                mTextViewOverviewParagraph = it.findViewById(R.id.mTextViewOverviewParagraph)
                mTextViewCity = it.findViewById(R.id.mTextViewCity)
                mImageViewGotoLocation = it.findViewById(R.id.mImageViewGotoLocation)
                mLinearLayoutContainerSubItem = it.findViewById(R.id.mLinearLayoutContainerSubItem)
                mButtonDetails = it.findViewById(R.id.mButtonDetails)
                mTextViewCriticalReadingAvgScore = it.findViewById(R.id.mTextViewCriticalReadingAvgScore)
                mTextViewMathAvgScore = it.findViewById(R.id.mTextViewMathAvgScore)
                mTextViewWritingAvgScore = it.findViewById(R.id.mTextViewWritingAvgScore)

                mImageViewGotoLocation.setOnClickListener { v: View ->
                    val position: Int = adapterPosition
                    lstRes?.value?.get(position)?.location
                    Snackbar.make(
                        v, "${GlobalConstants.textSnackBar}:: " +
                                "${lstRes?.value?.get(position)?.latitude.toString()}, ${lstRes?.value?.get(position)?.longitude.toString()}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null).show()
                    val mapsFragment = MapsFragment()
                    val bundle = Bundle()

                    bundle.putString(latAndLong[0], lstRes?.value?.get(position)?.latitude.toString())
                    bundle.putString(latAndLong[1], lstRes?.value?.get(position)?.longitude.toString())
                    mapsFragment.arguments = bundle

                    fragmentManager?.beginTransaction()
                        ?.add(R.id.mFrameLayoutSchoolsContainer, mapsFragment, MapsFragment::class.java.simpleName)
                        ?.addToBackStack(MapsFragment::class.java.simpleName)?.commit()
                }
            }
        }

        fun binData(uiDataClass: UIDataClass) {
            val expanded = uiDataClass.isExpanded
            mLinearLayoutContainerSubItem.visibility = if (expanded) View.VISIBLE else View.GONE

            try {
                when (uiDataClass.city) {
                    arrCities[0] -> {
                        Picasso.get()
                            .load(arrImagesCities[0])
                            .into(mImageViewCity)
                    }
                    arrCities[1] -> {
                        Picasso.get()
                            .load(arrImagesCities[1])
                            .into(mImageViewCity)
                    }
                    arrCities[2] -> {
                        Picasso.get()
                            .load(arrImagesCities[2])
                            .into(mImageViewCity)
                    }
                    else -> {
                        Picasso.get()
                            .load(arrImagesCities[3])
                            .into(mImageViewCity)
                    }
                }

                mTextViewSchoolName.text = uiDataClass.schoolName
                mTextViewOverviewParagraph.text = uiDataClass.overviewParagraph
                mTextViewCity.text = uiDataClass.city

                mTextViewCriticalReadingAvgScore.text = uiDataClass.readingScore
                mTextViewMathAvgScore.text = uiDataClass.mathScore
                mTextViewWritingAvgScore.text = uiDataClass.writingScore

            } catch (npe: NullPointerException) {
                logger.severe("$TAG::onBindViewHolder::${npe.message}")
            } catch (e: Exception) {
                logger.severe("$TAG::onBindViewHolder::${e.message}")
            }
        }

    }

}