package au.vu.nit3213.history.ui.login

import au.vu.nit3213.history.data.Repository
import au.vu.nit3213.history.data.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

// Fake repo that returns a successful keypass for login
private class FakeRepoSuccess : Repository {
    override suspend fun login(campus: String, username: String, password: String)
            = Result.Success("history")

    // Not used in this test
    override suspend fun loadHistory(keypass: String)
            = Result.Error("Not used")
}

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @Test
    fun `login emits Success with keypass history`() = runTest {
        val vm = LoginViewModel(repo = FakeRepoSuccess())

        // act
        vm.doLogin("br", "MD Parvez Hussain", "8131988")

        // assert
        val state = vm.state.value
        assertTrue(state is Result.Success)
        assertEquals("history", (state as Result.Success<String>).data)
    }
}
