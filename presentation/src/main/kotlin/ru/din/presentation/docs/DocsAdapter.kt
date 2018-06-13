package ru.din.presentation.docs

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.docs_adapter_cell.view.*
import ru.din.presentation.R
import ru.din.presentation.common.ImageLoader
import ru.din.presentation.entities.Doc


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
    this.docs.addAll(docs)
    notifyDataSetChanged()
  }

  class DocCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(doc: Doc, imageLoader: ImageLoader, listener: (Doc, View) -> Unit) = with(itemView) {
      docCellTitle.text = doc.title
/*
      val myDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        resources.getDrawable(R.drawable.logo_24dp, context.theme)
      } else {
        resources.getDrawable(R.drawable.logo_24dp)
      }
      docCellImage.setImageDrawable(myDrawable)
*/
      docCellImage.setImageResource(R.drawable.logo_24dp)
      doc.preview?.let { imageLoader.load(it, docCellImage) }
      setOnClickListener { listener(doc, itemView) }
    }

  }
}