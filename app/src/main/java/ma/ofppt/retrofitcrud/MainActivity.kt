package ma.ofppt.retrofitcrud

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ma.ofppt.retrofitcrud.fragments.UserListFragment
import ma.ofppt.retrofitcrud.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var userListFragment: UserListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userListFragment = UserListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,userListFragment)
            .addToBackStack(null)
            .commit()
        val sharedPreferences : SharedPreferences = application.getSharedPreferences("app1", Context. MODE_PRIVATE)
        sharedPreferences.edit().putString("nom","nom1")
    }
}