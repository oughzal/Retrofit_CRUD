package ma.ofppt.retrofitcrud.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ma.ofppt.retrofitcrud.R
import ma.ofppt.retrofitcrud.RetrofitClient
import ma.ofppt.retrofitcrud.adapter.UserAdapter
import ma.ofppt.retrofitcrud.databinding.UserListBinding
import ma.ofppt.retrofitcrud.model.User
import ma.ofppt.retrofitcrud.viewmodel.UserViewModel

class UserListFragment : Fragment(),UserAdapter.Listener,UserEditeCallback {
    lateinit var binding: UserListBinding
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter
    lateinit var userEdite: UserEditeFragment
    var userList : List<User> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding = UserListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(userList,this)
        binding.recyclerView.adapter = userAdapter
        userViewModel.userList.observe(viewLifecycleOwner) {
            userList = it
            userAdapter.notifyDataSetChanged()

            binding.btnRefresh.setOnClickListener {
            userViewModel.getUsers()
        }
        }
        userViewModel.getUsers()
        userEdite = UserEditeFragment()
        userEdite.isCancelable = false
        userEdite.userEditeCallback = this
        binding.btnAdd.setOnClickListener {
            userEdite.user = User()
           userEdite.show(requireActivity().supportFragmentManager,userEdite.tag)
        }

    }

    override fun onEdite(position: Int) {
        val user = userViewModel.userList.value?.get(position)?:User()
        userViewModel.currentUser.value = user
        Log.i("MyTAGRetro",user.toString())
       userEdite.user = user

        userEdite.show(requireActivity().supportFragmentManager,userEdite.tag)
    }

    override fun onDelete(position: Int) {
        val user = userViewModel.userList.value?.get(position)?:User()
        userViewModel.deleteUser(user)
    }

    override fun onUserEdite(user: User) {
        userViewModel.addUser(user)
    }

    override fun onUserAdd(user: User) {

    }


}