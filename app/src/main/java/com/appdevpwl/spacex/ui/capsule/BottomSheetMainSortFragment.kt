package com.appdevpwl.spacex.ui.capsule

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.CapsulesSortType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BottomSheetMainSortFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomSheetMainSortFragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var linearSerialAsc: LinearLayout
    lateinit var linearSerialDesc: LinearLayout
    lateinit var linearTimeAsc: LinearLayout
    lateinit var linearTimeDesc: LinearLayout
    var checkedSortTypeOption: Int = 0


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


        return inflater.inflate(R.layout.capsule_sort_bottom_sheet_main, container, false)
    }

    private fun setSelectedOption(capsulesSortType: CapsulesSortType) {

        val capsuleSortTypeName = capsulesSortType.name

        val navController = findNavController()
        navController.previousBackStackEntry?.savedStateHandle?.set("sort_by", capsuleSortTypeName)
        dismiss()

    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.capsule_sort_bottom_sheet_main, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearSerialAsc = view.findViewById(R.id.linear_serial_asc)
        linearSerialDesc = view.findViewById(R.id.linear_serial_desc)
        linearTimeAsc = view.findViewById(R.id.linear_time_asc)
        linearTimeDesc = view.findViewById(R.id.linear_time_desc)


        val clickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.linear_serial_asc -> {
                    setSelectedOption(CapsulesSortType.TYPE_ASC)
                }
                R.id.linear_serial_desc -> {
                    setSelectedOption(CapsulesSortType.TYPE_DESC)
                }
                R.id.linear_time_asc -> {
                    setSelectedOption(CapsulesSortType.TIME_ASC)
                }
                R.id.linear_time_desc -> {
                    setSelectedOption(CapsulesSortType.TIME_DESC)
                }
            }
        }


        linearSerialAsc.setOnClickListener(clickListener)
        linearSerialDesc.setOnClickListener(clickListener)
        linearTimeAsc.setOnClickListener(clickListener)
        linearTimeDesc.setOnClickListener(clickListener)


    }

    companion object {
        /**


         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BottomSheetMainSortFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BottomSheetMainSortFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
