package huuloc.uit.edu.viewmobeltest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var query = MutableLiveData<String>().apply { value = "" }
}