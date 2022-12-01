package ru.philipp_kalyaev.android.education_api_git.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.philipp_kalyaev.android.education_api_git.App
import ru.philipp_kalyaev.android.education_api_git.data.Common
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject

class UserListViewModel(
    application: App,
) : ViewModel() {

    var userState = MutableLiveData<State>(State.Loading)

    @Inject
    lateinit var repository: GithubRepository

    init {
        application.appComponent.inject(this)
        repoToUser()
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorMessage: String) : State
        data class Success(val users: List<User>) : State
    }

    fun repoToUser() {
        viewModelScope.launch {
            try {
                val users = repository.getUsers()
                userState.postValue(State.Success(users))
            } catch (e: Exception) {
                userState.postValue(State.Error(e.localizedMessage.orEmpty()))
            }
        }
    }
}

class UserListViewModelFactory(
    private val application: App
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(App::class.java)
            .newInstance(application)
    }
}

