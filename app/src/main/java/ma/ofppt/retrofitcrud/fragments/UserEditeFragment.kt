package ma.ofppt.retrofitcrud.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ma.ofppt.retrofitcrud.RetrofitClient
import ma.ofppt.retrofitcrud.databinding.UserEditBinding
import ma.ofppt.retrofitcrud.model.User
import ma.ofppt.retrofitcrud.viewmodel.UserViewModel

class UserEditeFragment : BottomSheetDialogFragment() {
    lateinit var binding: UserEditBinding
    lateinit var userViewModel: UserViewModel
    var userEditeCallback: UserEditeCallback? = null
    var user = User()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserEditBinding.inflate(layoutInflater, container, false)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val user = userViewModel.currentUser.value
        binding.txtFirstName.setText(user.firstName)
        binding.txtLastName.setText(user.lastName)
        binding.btncancel.setOnClickListener {
            dismiss()
        }

        binding.btnSave.setOnClickListener {
            //TODO : save User
            user.firstName = binding.txtFirstName.text.toString()
            user.lastName = binding.txtLastName.text.toString()
            userEditeCallback?.onUserEdite(user)
            dismiss()
        }


    }
}