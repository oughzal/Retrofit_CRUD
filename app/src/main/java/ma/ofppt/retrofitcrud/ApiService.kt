package ma.ofppt.retrofitcrud

import ma.ofppt.retrofitcrud.model.Message
import ma.ofppt.retrofitcrud.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    @GET("userApi.php")
    suspend fun getAllUsers(): List<User>

    @GET("userApi.php")
    suspend fun getUserById(@Query("id") id: Int): User

    @POST("userApi.php")
    suspend fun createUser(@Body user: User): User

    @PUT("userApi.php")
    suspend fun updateUser( @Body user: User): User

    @DELETE("userApi.php")
    suspend fun deleteUser(@Query("id") id: Int) : Message
}