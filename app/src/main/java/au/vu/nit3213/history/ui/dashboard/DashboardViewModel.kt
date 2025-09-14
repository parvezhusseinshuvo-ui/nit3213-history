package au.vu.nit3213.history.ui.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import au.vu.nit3213.history.data.Repository
import au.vu.nit3213.history.data.Result
import au.vu.nit3213.history.data.remote.DashboardResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repo: Repository
) : ViewModel() {

    // Emits Loading / Success(DashboardResponse) / Error
    private val _state = MutableStateFlow<Result<DashboardResponse>>(Result.Loading)
    val state: StateFlow<Result<DashboardResponse>> = _state

    suspend fun load(keypass: String) {
        _state.value = Result.Loading
        _state.value = repo.loadHistory(keypass)
    }
}
