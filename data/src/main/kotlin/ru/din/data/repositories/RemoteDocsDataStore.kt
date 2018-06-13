package ru.din.data.repositories

import com.vk.sdk.VKAccessToken
import io.reactivex.Observable
import ru.din.data.api.Api
import ru.din.data.entities.getAll.DocDTO
import ru.din.domain.DocsDataStore
import ru.din.domain.common.Mapper
import ru.din.domain.entities.DocVO

class RemoteDocsDataStore(private val api: Api,
                          private val docDataMapper: Mapper<DocDTO, DocVO>) : DocsDataStore {

  override fun getDocs(): Observable<List<DocVO>> {
    return api.getDocs(VKAccessToken.currentToken().userId, "5.78", VKAccessToken.currentToken().accessToken).map { results ->
      results.response.items.map { docDataMapper.mapFrom(it) }
    }
  }
}