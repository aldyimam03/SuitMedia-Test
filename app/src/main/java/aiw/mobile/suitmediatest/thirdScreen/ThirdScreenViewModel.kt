package aiw.mobile.suitmediatest.thirdScreen

import aiw.mobile.Api.ApiConfig
import aiw.mobile.Api.DataItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ThirdScreenViewModel : ViewModel() {

    private val _users = MutableLiveData<List<DataItem>>()
    val users: LiveData<List<DataItem>> get() = _users

    companion object {
        private const val TAG = "ThirdScreenViewModel"
    }

    fun getUsers(page: Int, perPage: Int) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getUsers(page, perPage)
                _users.value = response.data
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching users: ${e.message}")
            }
        }
    }
}
