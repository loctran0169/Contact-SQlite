package huuloc.uit.edu.viewmobeltest.Fragment

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import huuloc.uit.edu.viewmobeltest.Contact.ContactRepository

class MyFragmentAdapter(val application: Application, fragment: FragmentManager) : FragmentStatePagerAdapter(fragment) {
    private var items = listOf("All", "Favorite")

    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return AllContact(application)
        return FavoriteContact(application)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items[position]
    }
}