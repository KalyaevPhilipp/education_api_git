package ru.philipp_kalyaev.android.education_api_git.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
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

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchData()
        subscribeToUsers()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    sealed interface State {
        object Loading : State
        data class Error(val errorMessage: String) : State
        data class Success(val users: List<User>) : State
    }

    private fun fetchData() {
        repository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},
                { e ->
                    userState.postValue(State.Error(e.localizedMessage.orEmpty()))
                })
    }

    private fun subscribeToUsers() {
        repository.getUserList()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = State.Success(it)
                },
                {
                    Log.e("ERROR", it.localizedMessage.orEmpty())
                }
            )
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

