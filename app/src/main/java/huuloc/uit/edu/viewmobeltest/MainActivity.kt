package huuloc.uit.edu.viewmobeltest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import huuloc.uit.edu.viewmobeltest.Contact.Contact
import huuloc.uit.edu.viewmobeltest.Contact.ContactRepository
import huuloc.uit.edu.viewmobeltest.Fragment.MyFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val repo: ContactRepository by lazy {
        ContactRepository(application)
    }
    lateinit var name: EditText
    lateinit var phone: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpContact.adapter = MyFragmentAdapter(application, supportFragmentManager)
        tabMenu.setupWithViewPager(vpContact)
        btAdd.setOnClickListener {
            var view = LayoutInflater.from(this).inflate(R.layout.activity_add_contact, null)
            name = view.findViewById(R.id.etName)
            phone = view.findViewById(R.id.etPhone)
            var dialog = AlertDialog.Builder(this)
            dialog.setView(view)
                .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->

                }
                .setPositiveButton("Add") { dialogInterface: DialogInterface, i: Int ->
                    if (name.text.isNotEmpty() && phone.text.isNotEmpty()) {
                        val contact: MutableList<Contact> = mutableListOf()
                        contact.add(
                            Contact(
                                name = name.text.toString(),
                                phone = phone.text.toString(),
                                favorite = false
                            )
                        )
                        repo.insert(contact)
                    }
                    else Toast.makeText(this,"Please fill full",Toast.LENGTH_SHORT).show()
                }
            var dislay = dialog.create()
            dislay.setTitle("Add Contact")
            dislay.show()
        }
    }
}
