package ru.philipp_kalyaev.android.education_api_git.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

class DetailsViewModel(
    private val user: User,
) : ViewModel() {

    val userState = MutableLiveData<State>(State.Loading)

    private val retrofitService = Common.retrofitService

    init {
        getSubscribers()
    }

    sealed interface State {
        object Loading : State
        data class Success(val users: List<User>) : State
    }

    fun getSubscribers() {
        retrofitService.getSubscribersByUser(user.userName)
            .enqueue(object : Callback<List<ResponseListUsers>> {
                override fun onFailure(call: Call<List<ResponseListUsers>>, t: Throwable) {
                    Log.e("DetailsViewModel", "Subscribers error", t)
                }

                override fun onResponse(
                    call: Call<List<ResponseListUsers>>,
                    response: Response<List<ResponseListUsers>>
                ) {
                    userState.value =
                        State.Success((response.body() as List<ResponseListUsers>).map {
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

class DetailsViewModelFactory(private val user: User): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(User::class.java)
            .newInstance(user)
    }
}
