package org.com.raian.nycschoolschase.ui.schools.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.com.raian.nycschoolschase.R
import org.com.raian.nycschoolschase.ui.schools.adapters.RVCustomAdapter
import org.com.raian.nycschoolschase.ui.base.BaseFragment
import org.com.raian.nycschoolschase.util.safeLet
import java.util.logging.Logger

class SchoolsFragment : BaseFragment() {

    private lateinit var mRecyclerViewSchools: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var rvCustomAdapter: RVCustomAdapter

    init {
        TAG = SchoolsFragment::class.java.simpleName
        logger = Logger.getLogger(TAG)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_schools, container, false)
        initView()
        setRecyclerViewItemTouchListener()
        return rootView
    }

    override fun initView() {
        mRecyclerViewSchools = rootView.findViewById(R.id.mRecyclerViewSchools)
        layoutManager = LinearLayoutManager(context)
        mRecyclerViewSchools.layoutManager = layoutManager

        safeLet(context, fragmentManager) { ctx, fragmentManager ->
            rvCustomAdapter = RVCustomAdapter(ctx, schoolsViewModel.getListOfSchoolsForUI(), fragmentManager)
            mRecyclerViewSchools.adapter = rvCustomAdapter
        }
    }

    override fun onResume() {
        super.onResume().also {
            prepareObservers()
        }
    }

    private fun prepareObservers() {
        schoolsViewModel.getListOfSchoolsForUI().observe(this, Observer {
            rvCustomAdapter.notifyDataSetChanged()
        })
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    viewHolder1: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    val position = viewHolder.adapterPosition
                    try {
                        mRecyclerViewSchools.adapter?.notifyItemRemoved(position)
                    } catch (indexOutOfBoundException: IndexOutOfBoundsException) {
                        logger.severe("$TAG::setRecyclerViewItemTouchListener::${indexOutOfBoundException.message}")
                    }
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerViewSchools)
    }
}
