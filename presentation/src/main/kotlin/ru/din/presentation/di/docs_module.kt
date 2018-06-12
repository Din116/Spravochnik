package ru.din.presentation.di

import com.squareup.picasso.Picasso
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import ru.din.domain.usecases.GetDocs
import ru.din.presentation.common.ASyncTransformer
import ru.din.presentation.common.ImageLoader
import ru.din.presentation.common.PicassoImageLoader
import ru.din.presentation.docs.DocsViewModel

val docsModule = applicationContext {
  bean { GetDocs(ASyncTransformer(), get()) }
  viewModel { DocsViewModel(get(), get("DocEntityDocMapper")) }
  bean { PicassoImageLoader(Picasso.with(androidApplication())) as ImageLoader }
}