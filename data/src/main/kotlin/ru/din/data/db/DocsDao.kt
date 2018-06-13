package ru.din.data.db

import android.arch.persistence.room.*
import ru.din.data.entities.getAll.DocDTO

@Dao
interface DocsDao {
  @Query("SELECT * FROM docs")
  fun getDocs(): List<DocDTO>

  @Query("SELECT * FROM docs WHERE id=:docId")
  fun get(docId: Int): DocDTO?

  @Query("SELECT * FROM docs WHERE title LIKE :query")
  fun search(query: String): List<DocDTO>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveDoc(doc: DocDTO)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveAllDocs(docs: List<DocDTO>)

  @Delete
  fun removeDoc(doc: DocDTO)

  @Query("DELETE FROM docs")
  fun clear()
}
