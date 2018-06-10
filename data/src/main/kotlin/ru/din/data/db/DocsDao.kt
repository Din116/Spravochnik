package ru.din.data.db

import android.arch.persistence.room.*
import ru.din.data.entities.getAll.DocItem

@Dao
interface DocsDao {
  @Query("SELECT * FROM docs")
  fun getDocs(): List<DocItem>

  @Query("SELECT * FROM docs WHERE id=:docId")
  fun get(docId: Int): DocItem?

  @Query("SELECT * FROM docs WHERE title LIKE :query")
  fun search(query: String): List<DocItem>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveDoc(doc: DocItem)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveAllDocs(docs: List<DocItem>)

  @Delete
  fun removeDoc(doc: DocItem)

  @Query("DELETE FROM docs")
  fun clear()
}
