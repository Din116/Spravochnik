package ru.din.presentation.info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.din.presentation.R

class InfoFragment: Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return layoutInflater.inflate(R.layout.fragment_info, container, false)
  }
}