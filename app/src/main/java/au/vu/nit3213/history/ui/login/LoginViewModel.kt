package au.vu.nit3213.history.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import au.vu.nit3213.history.data.Repository
import au.vu.nit3213.history.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    // Emits Loading / Success(keypass) / Error
    private val _state = MutableStateFlow<Result<String>>(Result.Success(""))
    val state: StateFlow<Result<String>> = _state

    suspend fun doLogin(campus: String, username: String, password: String) {
        _state.value = Result.Loading
        _state.value = repo.login(campus, username, password)
    }
}
