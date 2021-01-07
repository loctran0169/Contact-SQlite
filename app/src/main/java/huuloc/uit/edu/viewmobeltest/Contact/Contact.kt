package huuloc.uit.edu.viewmobeltest.Contact

import androidx.room.ColumnInfo
import  androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Contact")
class Contact(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name", collate = ColumnInfo.NOCASE)
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean
)