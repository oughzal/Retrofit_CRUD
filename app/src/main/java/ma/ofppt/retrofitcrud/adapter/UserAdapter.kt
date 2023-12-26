package ma.ofppt.retrofitcrud.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ma.ofppt.retrofitcrud.R
import ma.ofppt.retrofitcrud.fragments.UserEditeFragment
import ma.ofppt.retrofitcrud.model.User

class UserAdapter(list: List<User>,listener : Listener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    val listener = listener
    val userList = list
    lateinit var userEdite: UserEditeFragment

    interface Listener{
        fun onEdite(position: Int)
        fun onDelete(position: Int)

    }

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.id.text = user.id.toString()
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName

        holder.btnEdite.setOnClickListener{
        listener.onEdite(position)
        }
          holder.btnDelete.setOnClickListener{
        listener.onDelete(position)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val id :TextView = itemView.findViewById(R.id.txtId)
    val firstName :TextView = itemView.findViewById(R.id.txtFirstName)
    val lastName :TextView = itemView.findViewById(R.id.txtLastName)
    val btnEdite :ImageView = itemView.findViewById(R.id.btnEdite)
    val btnDelete :ImageView = itemView.findViewById(R.id.btnDelete)
    }
}