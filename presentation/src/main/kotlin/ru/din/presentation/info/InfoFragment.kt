package ru.din.presentation.info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluejamesbond.text.style.TextAlignment
import kotlinx.android.synthetic.main.fragment_info.*
import ru.din.presentation.R


class InfoFragment : Fragment() {
  var listAdapter: ExpandableListAdapter? = null
  var listDataHeader: List<String>? = null
  var listDataChild: HashMap<String, List<Spanned>>? = null


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return layoutInflater.inflate(R.layout.fragment_info, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    prepareListData()
    listAdapter = ExpandableListAdapter(this@InfoFragment.context!!, listDataHeader!!, listDataChild!!)
    lvExp.setAdapter(listAdapter)
  }

  fun prepareListData() {
    listDataHeader = arrayListOf(getString(R.string.assembler_title),
                                 getString(R.string.java_title),
                                 getString(R.string.csharp_title),
                                 getString(R.string.dart_title),
                                 getString(R.string.delphi_title),
                                 getString(R.string.go_title),
                                 getString(R.string.javascript_title),
                                 getString(R.string.matlab_title),
                                 getString(R.string.objectivec_title),
                                 getString(R.string.perl_title),
                                 getString(R.string.php_title),
                                 getString(R.string.plsql_title),
                                 getString(R.string.python_title),
                                 getString(R.string.r_title),
                                 getString(R.string.ruby_title),
                                 getString(R.string.swift_title),
                                 getString(R.string.dotnet_title),
                                 getString(R.string.visualbasic_title),
                                 getString(R.string.cplusplus_title),
                                 getString(R.string.c_title))
    listDataChild = hashMapOf(Pair((listDataHeader as ArrayList<String>)[0], arrayListOf(Html.fromHtml(getString(R.string.assembler_text)))),
        Pair((listDataHeader as ArrayList<String>)[1], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[2], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[3], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[4], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[5], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[6], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[7], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[8], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[9], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[10], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[11], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[12], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[13], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[14], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[15], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[16], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[17], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[18], arrayListOf(Html.fromHtml(getString(R.string.java_text)))),
        Pair((listDataHeader as ArrayList<String>)[19], arrayListOf(Html.fromHtml(getString(R.string.java_text)))))
  }
}