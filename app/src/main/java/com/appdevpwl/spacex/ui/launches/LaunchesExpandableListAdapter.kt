package com.appdevpwl.spacex.ui.launches

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.appdevpwl.spacex.R

class LaunchesExpandableListAdapter internal constructor(
    private val context: Context,
    private val groupTitle: List<String>,
    private val dataList: HashMap<String, MutableList<List<String>>>,
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return this.groupTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.dataList[this.groupTitle[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.groupTitle[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.dataList[this.groupTitle[groupPosition]]!![childPosition]

    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    @SuppressLint("InflateParams")
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        var convertView = convertView
        val groupTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.layout_expandable_group, null)
        }

        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.launches_expandable_title)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = groupTitle
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        var convertView = convertView
        val expandableItem = getChild(groupPosition, childPosition) as List<*>
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.core_single_item_rv, null)
        }
        val coresSerial = convertView!!.findViewById<TextView>(R.id.core_serial_type)
        val coresStatus = convertView.findViewById<TextView>(R.id.core_status_tv)
        val coresBlocks = convertView.findViewById<TextView>(R.id.core_blocks_tv)
        val coresReuseCount = convertView.findViewById<TextView>(R.id.core_reuse_count_tv)
        val coresLastUpdate = convertView.findViewById<TextView>(R.id.cores_last_update)
        coresSerial.setTypeface(null, Typeface.BOLD)
        coresSerial.text = expandableItem[0].toString()
        coresStatus.text = expandableItem[1].toString()
        coresBlocks.text = expandableItem[2].toString()
        coresReuseCount.text = expandableItem[3].toString()
        coresLastUpdate.text = expandableItem[4].toString()

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}