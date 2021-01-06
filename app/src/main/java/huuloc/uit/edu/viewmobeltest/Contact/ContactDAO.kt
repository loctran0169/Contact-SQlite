package huuloc.uit.edu.viewmobeltest.Contact

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {
    @Insert
    fun insert(vararg contact: Contact)

    @Query("select *from Contact order by phone asc")
    fun getAllContact(): LiveData<List<Contact>>

    @Query("select *from Contact where favorite = 1 order by phone asc")
    fun getAllFavoriteContact(): LiveData<List<Contact>>

    @Query("select *from Contact where :key = '' or name COLLATE UTF8CI like :key or phone COLLATE UTF8CI like :key order by phone asc")
    fun findContact(key: String): LiveData<List<Contact>>

    @Query("select *from Contact where favorite = 1 and( :key = '' or name COLLATE UTF8CI like :key or phone COLLATE UTF8CI like :key) order by phone asc")
    fun findContactFavorite(key: String): LiveData<List<Contact>>

    @Update
    fun update(contact: Contact)

    @Delete
    fun remove(contact: Contact)

    @Query("delete from Contact")
    fun removeAll()

}