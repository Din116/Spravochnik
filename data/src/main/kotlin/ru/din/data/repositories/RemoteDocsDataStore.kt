package ru.din.data.repositories

import com.vk.sdk.VKAccessToken
import io.reactivex.Observable
import ru.din.data.api.Api
import ru.din.data.entities.getAll.DocItem
import ru.din.domain.DocsDataStore
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocEntity

class RemoteDocsDataStore(private val api: Api,
                          private val docDataMapper: Mapper<DocItem, DocEntity>) : DocsDataStore {

  override fun getDocs(): Observable<List<DocEntity>> {
    return api.getDocs(VKAccessToken.currentToken().userId, VERSION_API, VKAccessToken.currentToken().accessToken).map { results ->
      results.items.map { docDataMapper.mapFrom(it) }
    }
  }

  companion object {
    private const val VERSION_API: String = "5.78"
  }

}