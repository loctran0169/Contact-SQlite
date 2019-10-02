package huuloc.uit.edu.viewmobeltest.Fragment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huuloc.uit.edu.viewmobeltest.Contact.ContactAdapter
import huuloc.uit.edu.viewmobeltest.Contact.ContactRepository
import huuloc.uit.edu.viewmobeltest.R
import kotlinx.android.synthetic.main.all_contact.*

class FavoriteContact(val application: Application) : Fragment() {
    val repo: ContactRepository by lazy {
        ContactRepository(application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.all_contact, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var contactAdapter = ContactAdapter(
            activity as Context,
            (activity)!!.supportFragmentManager,
            application
        )
        rcvContact.run {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = contactAdapter
            addItemDecoration(SpaceItem(10))
        }
        repo.getAllFavoriteContact()?.observe(this, androidx.lifecycle.Observer {
            contactAdapter.updateDateSetChange(it)
            println("### farvorite")
        })
    }
}