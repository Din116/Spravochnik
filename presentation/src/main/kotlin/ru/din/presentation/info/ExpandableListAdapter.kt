package ru.din.presentation.info

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.bluejamesbond.text.DocumentView
import kotlinx.android.synthetic.main.info_group.view.*
import kotlinx.android.synthetic.main.info_item.view.*
import ru.din.presentation.R
import android.text.Spanned
import com.bluejamesbond.text.style.TextAlignment

class ExpandableListAdapter(context: Context, private val listDataHeader: List<String>, val listChildData: HashMap<String, List<Spanned>>) : BaseExpandableListAdapter() {
  var layoutInflater: LayoutInflater? = null

  init {
    layoutInflater = LayoutInflater.from(context)
  }

  override fun getGroup(groupPosition: Int): Any {
    return listDataHeader[groupPosition]
  }

  override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
    return true
  }

  override fun hasStableIds(): Boolean {
    return false
  }

  override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
    val headerTitle = getGroup(groupPosition) as String
    var localConvertView = convertView
    if (localConvertView == null) {
      localConvertView = layoutInflater!!.inflate(R.layout.info_group, parent, false)
    }
    val lblListHeader: TextView = localConvertView!!.lblListHeader
    lblListHeader.text = headerTitle
    return localConvertView
  }

  override fun getChildrenCount(groupPosition: Int): Int {
    return listChildData[listDataHeader[groupPosition]]!!.size
  }

  override fun getChild(groupPosition: Int, childPosition: Int): Any {
    return listChildData[listDataHeader[groupPosition]]!![childPosition]
  }

  override fun getGroupId(groupPosition: Int): Long {
    return groupPosition.toLong()
  }

  override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
    val childText = getChild(groupPosition, childPosition) as Spanned
    var localConvertView = convertView
    if (localConvertView == null) {
      localConvertView = layoutInflater!!.inflate(R.layout.info_item, parent, false)
    }

    val txtListChild = localConvertView!!.lblListItem
    val imageView2 = localConvertView.imageView2
    imageView2.setImageResource(R.drawable.logo_24dp)
    txtListChild.text = childText
    return localConvertView
  }

  override fun getChildId(groupPosition: Int, childPosition: Int): Long {
    return childPosition.toLong()
  }

  override fun getGroupCount(): Int {
    return listDataHeader.size
  }

}
