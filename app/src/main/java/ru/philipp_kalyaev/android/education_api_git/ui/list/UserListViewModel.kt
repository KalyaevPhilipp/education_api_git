package ru.philipp_kalyaev.android.education_api_git.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.education_api_git.navigation.Screens
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.Callbacks
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val router: Router,
    private val repository: GithubRepository
) : ViewModel(), Callbacks {

    var userState = MutableLiveData<State>(State.Loading)

    init {
        fetchData()
        subscribeToUsers()
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorMessage: String) : State
        data class Success(val users: List<User>) : State
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                repository.getUsers()
            } catch (e: Exception) {
                userState.postValue(State.Error(e.localizedMessage.orEmpty()))
            }
        }
    }

    private fun subscribeToUsers() {
        viewModelScope.launch {
            repository.getUserList().collect { users ->
                userState.value = State.Success(users)
            }
        }
    }

    override fun onUserSelected(user: User) {
        router.navigateTo(Screens.Details(user))
    }
}

class UserListViewModelFactory(
    private val viewModel: UserListViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}

