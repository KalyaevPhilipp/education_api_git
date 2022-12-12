package ru.philipp_kalyaev.android.education_api_git.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.philipp_kalyaev.android.education_api_git.App
import ru.philipp_kalyaev.android.education_api_git.domain.DbRepository
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject

class UserListViewModel(
    application: App,
) : ViewModel() {

    var userState = MutableLiveData<State>(State.Loading)
    var userDbState = MutableLiveData<State>(State.Loading)

    @Inject
    lateinit var repository: GithubRepository

    @Inject
    lateinit var repositoryDb: DbRepository

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
                if (users.isNotEmpty())
                    updateDbUsers(users)

                //userState.postValue(State.Success(users))
            } catch (e: Exception) {
                userState.postValue(State.Error(e.localizedMessage.orEmpty()))
            }
        }
    }

    fun updateDbUsers(users: List<User>) {
        viewModelScope.launch {
            try {
                repositoryDb.setUserList(users)
                val users = repositoryDb.getUserList()
                userState.postValue(State.Success(users))
            } catch (e: Exception) {
                userDbState.postValue(State.Error(e.localizedMessage.orEmpty()))
            }
        }
    }
}

class UserListViewModelFactory(
    private val application: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(App::class.java)
            .newInstance(application)
    }
}

