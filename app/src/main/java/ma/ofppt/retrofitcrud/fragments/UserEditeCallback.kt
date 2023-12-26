package ma.ofppt.retrofitcrud.fragments

import ma.ofppt.retrofitcrud.model.User

interface UserEditeCallback {
    fun onUserEdite(user: User)
    fun onUserAdd(user: User)
}