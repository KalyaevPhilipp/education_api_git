package ru.philipp_kalyaev.android.education_api_git.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

class DetailsViewModel : ViewModel() {

    val userState = MutableLiveData<State>(State.Loading)

    private val retrofitService = Common.retrofitService

    init {
        /* viewModelScope.launch {
             delay(3000L)
             repoToUser()
         }*/
        getSubscribers("./users/tekkub/followers")
    }

    sealed interface State {
        object Loading : State
        data class Success(val users: List<User>) : State
    }

    private fun repoToUser() {
        retrofitService.getUserList().enqueue(object : Callback<List<ResponseListUsers>> {
            override fun onFailure(call: Call<List<ResponseListUsers>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<ResponseListUsers>>,
                response: Response<List<ResponseListUsers>>
            ) {
                userState.value = State.Success((response.body() as List<ResponseListUsers>).map {
                    User(
                        it.id.toString(),
                        it.login,
                        it.avatar_url
                    )
                })
            }
        })

    }

    private fun getSubscribers(userSubUrl: String) {
        retrofitService.getSubscribersByUser(userSubUrl)
            .enqueue(object : Callback<List<ResponseListUsers>> {
                override fun onFailure(call: Call<List<ResponseListUsers>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<List<ResponseListUsers>>,
                    response: Response<List<ResponseListUsers>>
                ) {
                    userState.value =
                        DetailsViewModel.State.Success((response.body() as List<ResponseListUsers>).map {
                            User(
                                it.id.toString(),
                                it.login,
                                it.avatar_url
                            )
                        })
                }
            })
    }
}
