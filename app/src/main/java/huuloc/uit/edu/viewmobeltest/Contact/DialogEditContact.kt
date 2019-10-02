package huuloc.uit.edu.viewmobeltest.Contact

import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import huuloc.uit.edu.viewmobeltest.R

class DialogEditContact(var contact: Contact, application: Application) : AppCompatDialogFragment() {

    lateinit var name: EditText
    lateinit var phone: EditText
    var like = contact.favorite
    private val repo: ContactRepository by lazy {
        ContactRepository(application)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var dialog = AlertDialog.Builder(context)
        var view = LayoutInflater.from(context).inflate(R.layout.edit_contact, null)

        name = view.findViewById(R.id.edtName)
        phone = view.findViewById(R.id.edtPhone)

        name.setText(contact.name)
        phone.setText(contact.phone)
        dialog.setView(view)
            .setTitle("Edit Contact")
            .setNegativeButton("cancel") { dialogInterface: DialogInterface, i: Int ->

            }
            .setPositiveButton("ok") { dialogInterface: DialogInterface, i: Int ->
                if (name.text.isNotEmpty() && phone.text.isNotEmpty()) {
                    repo.update(
                        Contact(
                            contact.id,
                            name.text.toString(),
                            phone.text.toString(),
                            contact.favorite
                        )
                    )
                }
                else
                    Toast.makeText(context,"Please fill full",Toast.LENGTH_SHORT).show()
            }
        return dialog.create()
    }
}