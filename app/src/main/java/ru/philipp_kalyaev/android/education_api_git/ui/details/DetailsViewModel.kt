package ru.philipp_kalyaev.android.education_api_git.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.feature_repo.User

//class DetailsViewModel(
//    private val user: User,
//    application: App,
//) : ViewModel() {
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val user: User,
    private val repository: GithubRepository
) : ViewModel() {

    val userState = MutableLiveData<State>(State.Loading)


    init {
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
    @AssistedFactory
    interface Factory {
        fun create(user: User): DetailsViewModel
    }

}
class DetailsViewModelFactory(
    private val viewModel: DetailsViewModel,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}



/*
class DetailsViewModelFactory(
    private val user: User,
    private val application: App

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //return viewModel as T
        return modelClass.getConstructor(User::class.java, App::class.java)
            .newInstance(user , application)
    }
}
*/