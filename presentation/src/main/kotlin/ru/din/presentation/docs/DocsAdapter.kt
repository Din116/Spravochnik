package ru.din.presentation.docs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.docs_adapter_cell.view.*
import ru.din.presentation.R
import ru.din.presentation.common.ImageLoader
import ru.din.presentation.entities.Doc
import java.text.DateFormatSymbols
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class DocsAdapter constructor(private val imageLoader: ImageLoader,
                              private val onDocSelected: (Doc, View) -> Unit) : RecyclerView.Adapter<DocsAdapter.DocCellViewHolder>() {

  private val docs: MutableList<Doc> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocCellViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(
        R.layout.docs_adapter_cell,
        parent,
        false)
    return DocCellViewHolder(view)
  }

  override fun getItemCount(): Int {
    return docs.size
  }

  override fun onBindViewHolder(holder: DocCellViewHolder, position: Int) {
    val doc = docs[position]
    holder.bind(doc, imageLoader, onDocSelected)
  }

  fun addDoc(docs: List<Doc>) {
    this.docs.clear()
    this.docs.addAll(docs.sortedBy { it.date })
    notifyDataSetChanged()
  }

  class DocCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private fun getFileSize(size: Long?): String {
      if (size == null || size <= 0)
        return "0"
      val units = arrayOf("B", "KB", "MB", "GB", "TB")
      val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
      return DecimalFormat("#,##0.#").format(size / Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups]
    }

    fun bind(doc: Doc, imageLoader: ImageLoader, listener: (Doc, View) -> Unit) = with(itemView) {
      docCellTitle.text = doc.title
      val dfs = DateFormatSymbols()
      dfs.months = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
      docCellDate.text = doc.date?.let {
        SimpleDateFormat("dd MMMM yyyy", dfs).format(Date(it.toLong()*1000)) +
            " в " +
            SimpleDateFormat("HH:mm", dfs).format(Date(it.toLong()*1000)) }
      docCellSize.text = getFileSize(doc.size?.toLong())
      docCellImage.setImageResource(R.drawable.logo_24dp)
      doc.preview?.let { imageLoader.load(it, docCellImage) }
      setOnClickListener { listener(doc, itemView) }
    }
  }
}