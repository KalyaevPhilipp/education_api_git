package ru.philipp_kalyaev.android.education_api_git.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.philipp_kalyaev.android.education_api_git.domain.GithubRepository
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

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
        repository.getDetailsByUser(username = user.userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userState.postValue(State.Success(it))
            }, {
                userState.postValue(State.Error(it.localizedMessage.orEmpty()))
            })

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
