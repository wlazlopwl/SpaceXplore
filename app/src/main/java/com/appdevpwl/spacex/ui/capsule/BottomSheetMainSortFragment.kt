package com.appdevpwl.spacex.ui.capsule

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.CapsulesSortType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetMainSortFragment : BottomSheetDialogFragment() {

    lateinit var linearSerialAsc: LinearLayout
    lateinit var linearSerialDesc: LinearLayout
    lateinit var linearTimeAsc: LinearLayout
    lateinit var linearTimeDesc: LinearLayout
    var checkedSortTypeOption: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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


        val clickListener = View.OnClickListener {
            when (it.id) {
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

}
