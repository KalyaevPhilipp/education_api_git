package ru.philipp_kalyaev.android.education_api_git.ui.details

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

class DetailsViewModel(
    private val user: User,
    application: App,
) : ViewModel() {

    val userState = MutableLiveData<State>(State.Loading)

    @Inject
    lateinit var repository: GithubRepository

    @Inject
    lateinit var repositoryDb: DbRepository

    init {
        application.appComponent.inject(this)
        getSubscribers()
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorMessage: String) : State
        data class Success(val users: List<User>) : State
    }

    fun getSubscribers() {
        viewModelScope.launch {
            try {
                val users = repository.getDetails(username = user.userName)
                userState.postValue(State.Success(users))
            } catch (e: Exception) {
                userState.postValue(State.Error(e.localizedMessage.orEmpty()))
            }
        }
    }
}

class DetailsViewModelFactory(
    private val user: User,
    private val application: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(User::class.java, App::class.java)
            .newInstance(user, application)
    }
}
