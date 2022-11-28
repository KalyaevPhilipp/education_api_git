package ru.philipp_kalyaev.android.education_api_git.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

class DetailsViewModel(
    private val user: User,
) : ViewModel() {

    val userState = MutableLiveData<State>(State.Loading)
    var job: Job? = null
    private val repository = GithubRepository()

    init {
        getSubscribers()
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorMessage: String) : State
        data class Success(val users: List<User>) : State
    }

    fun getSubscribers() {
        job = CoroutineScope(Dispatchers.Main).launch {
            repository.getDetails(
                username = user.userName,
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

class DetailsViewModelFactory(private val user: User) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(User::class.java)
            .newInstance(user)
    }
}
