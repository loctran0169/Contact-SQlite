package huuloc.uit.edu.viewmobeltest.Contact

import android.Manifest
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import huuloc.uit.edu.viewmobeltest.MainActivity
import huuloc.uit.edu.viewmobeltest.R
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
    val context: Context,
    val fragment: FragmentManager,
    val application: Application
) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var items = emptyList<Contact>()
    private val repo: ContactRepository by lazy {
        ContactRepository(application)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateDateSetChange(list: List<Contact>) {
        items = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val p0 = items[position]
            var favorite = p0.favorite
            itemView.tvName.text = p0.name
            itemView.tvPhone.text = p0.phone
            itemView.btFavorite.isSelected = favorite
            itemView.btFavorite.setOnClickListener {
                favorite = !favorite
                itemView.btFavorite.isSelected = favorite
                repo.update(Contact(p0.id, p0.name, p0.phone, favorite))
            }
            itemView.btEdit.setOnClickListener {
                var dialog = DialogEditContact(p0, application)
                dialog.show(fragment, "Edit Contact")
            }
            itemView.btDelete.setOnClickListener {
                var dialog = AlertDialog.Builder(context)
                dialog.setMessage("You you want to remove it ?")
                    .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

                    }
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                        repo.remove(p0)
                    }
                var dislay = dialog.create()
                dislay.setTitle("Remove")
                dislay.show()
            }
            itemView.item_contact.setOnClickListener {

                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    val permissions = arrayOf(Manifest.permission.CALL_PHONE)
                    ActivityCompat.requestPermissions((context as MainActivity), permissions, 0)
                    return@setOnClickListener
                }
                var dialog = AlertDialog.Builder(context)
                dialog.setMessage("Do you want to call to ${p0.phone}")
                    .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

                    }
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                        val intent = Intent(Intent.ACTION_CALL);
                        intent.data = Uri.parse("tel:${p0.phone}")
                        context.startActivity(intent)
                        return@setPositiveButton
                    }
                var dislay = dialog.create()
                dislay.setTitle("Call phone")
                dislay.show()
            }
        }
    }

}