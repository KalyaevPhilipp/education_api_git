package ru.philipp_kalyaev.android.education_api_git.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.data.api.model.ResponseListUsers
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

class UserListViewModel : ViewModel() {

    var job: Job? = null
    var userState = MutableLiveData<State>(State.Loading)
    private val repository = GithubRepository()
    private val retrofitService = Common.retrofitService

    init {
        repoToUser()
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorMessage: String) : State
        data class Success(val users: List<User>) : State
    }

    fun repoToUser() {
        job = CoroutineScope(Dispatchers.Main).launch {
            repository.getUsers(
                onError = {
                    userState.postValue(State.Error(it.localizedMessage.orEmpty()))
                },
                onSuccess = {
                    userState.postValue(State.Success(it))
                })
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}



