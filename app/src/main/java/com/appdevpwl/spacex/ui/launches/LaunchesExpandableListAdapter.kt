package com.appdevpwl.spacex.ui.launches

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
    private val dataList: HashMap<String, MutableList<List<String>>>
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

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val groupTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val expandableItem = getChild(groupPosition,childPosition) as List<*>
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.layout_launches_expandable_item, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.launches_expandable_item_name)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = expandableItem[0].toString()
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}