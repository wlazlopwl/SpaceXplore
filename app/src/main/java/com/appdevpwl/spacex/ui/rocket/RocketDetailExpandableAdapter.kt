package com.appdevpwl.spacex.ui.rocket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.appdevpwl.spacex.R

class RocketDetailExpandableAdapter(
    var context: Context?,
    private val groupList: List<String>,
    private val childList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int {
        return groupList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childList[groupList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childList[groupList[groupPosition]]!![childPosition]
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

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val groupTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.rocket_details_expandable_group, null)
        }
        var groupText = convertView!!.findViewById<TextView>(R.id.rocket_expandable_group)
        groupText.text = groupTitle
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val childTitle = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.rocket_details_expandable_child, null)
        }
        var childText = convertView!!.findViewById<TextView>(R.id.rocket_expandable_child)
        childText.text = childTitle
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}