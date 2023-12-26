package ma.ofppt.retrofitcrud.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ma.ofppt.retrofitcrud.RetrofitClient
import ma.ofppt.retrofitcrud.model.User

class UserViewModel : AndroidViewModel {

    val userList = MutableLiveData<List<User>>()
    val currentUser = MutableLiveData<User>(User())


    constructor(application: Application) : super(application) {

    }

    @SuppressLint("SuspiciousIndentation")
    fun getUsers() {
        GlobalScope.launch(Dispatchers.IO) {
            val list = RetrofitClient.instance.getAllUsers()
            userList.postValue(list)
            Log.i("MyTAGRetro", list.joinToString("\n"))
        }
    }

    fun deleteUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val message = RetrofitClient.instance.deleteUser(user.id)
            if (message.success) getUsers()
        }
    }

    fun addUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val user = RetrofitClient.instance.createUser(user)
            Log.i("MyTAGRetro", user.toString())
            getUsers()
        }

    }

    fun updateUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val user = RetrofitClient.instance.updateUser(user)
            Log.i("MyTAGRetro", user.toString())
        }
        getUsers()
    }

}