package com.example.got.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.got.R
import com.example.got.adapter.Adapter
import com.example.got.fragments.houses.*
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HouseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HouseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var houseView: View
    private lateinit var houseViewPager: ViewPager
    private lateinit var houseTabs: TabLayout
    private lateinit var hseAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        houseView = inflater.inflate(R.layout.fragment_house, container, false)
        initViewComponents()
        return houseView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager(houseViewPager)
        houseTabs.setupWithViewPager(houseViewPager)
        houseTabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager) {
        hseAdapter = Adapter(childFragmentManager)
        hseAdapter.addFragments(HousesPageFragment1())
        hseAdapter.addFragments(HousesPageFragment2())
        hseAdapter.addFragments(HousesPageFragment3())
        hseAdapter.addFragments(HousesPageFragment4())
        hseAdapter.addFragments(HousesPageFragment5())
        hseAdapter.addFragments(HousesPageFragment6())
        hseAdapter.addFragments(HousesPageFragment7())
        hseAdapter.addFragments(HousesPageFragment8())
        hseAdapter.addFragments(HousesPageFragment9())
        houseViewPager.adapter = hseAdapter
    }

    private fun initViewComponents() {
        houseViewPager = houseView.findViewById(R.id.house_view_pager)
        houseTabs = houseView.findViewById(R.id.house_tabs)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HouseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HouseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}