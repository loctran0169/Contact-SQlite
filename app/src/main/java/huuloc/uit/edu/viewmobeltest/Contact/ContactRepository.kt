package huuloc.uit.edu.viewmobeltest.Contact

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import huuloc.uit.edu.viewmobeltest.AppDataBase

class ContactRepository(application: Application) {

    private val contactDAO: ContactDAO? = AppDataBase.get(application)?.contactDAO()

    fun insert(contact: List<Contact>) {
        AsyncTaskProcessInsert(contactDAO).execute(*contact.toTypedArray())
    }

    fun getAllContact(): LiveData<List<Contact>>? {
        return contactDAO?.getAllContact()
    }

    fun getAllFavoriteContact(): LiveData<List<Contact>>? {
        return contactDAO?.getAllFavoriteContact()
    }

    fun remove(contact: Contact) {
        AsyncTaskProcessDelete(contactDAO).execute(contact)
    }

    fun update(contact: Contact) {
        AsyncTaskProcessUpdate(contactDAO).execute(contact)
    }

    fun deleteAll() {
        contactDAO?.removeAll()
    }


    private class AsyncTaskProcessInsert internal constructor(val dao: ContactDAO?) : AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contact: Contact): Void? {
            dao?.insert(*(contact.toMutableList()).toTypedArray())
            return null
        }
    }

    private class AsyncTaskProcessDelete internal constructor(val dao: ContactDAO?) : AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contact: Contact): Void? {
            dao?.remove(contact[0])
            return null
        }
    }

    private class AsyncTaskProcessUpdate internal constructor(val dao: ContactDAO?) : AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contact: Contact): Void? {
            dao?.update(contact[0])
            return null
        }
    }
}