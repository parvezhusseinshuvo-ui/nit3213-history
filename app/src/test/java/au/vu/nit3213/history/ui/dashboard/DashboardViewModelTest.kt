package au.vu.nit3213.history.ui.dashboard

import au.vu.nit3213.history.data.Repository
import au.vu.nit3213.history.data.Result
import au.vu.nit3213.history.data.remote.DashboardResponse
import au.vu.nit3213.history.data.remote.HistoryEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

// Fake repo that returns a small, deterministic dashboard payload
private class FakeRepoDashboard : Repository {
    override suspend fun login(campus: String, username: String, password: String)
            = Result.Success("history") // not used in this test

    override suspend fun loadHistory(keypass: String): Result<DashboardResponse> {
        val items = listOf(
            HistoryEntity(
                event = "World War II",
                startYear = 1939,
                endYear = 1945,
                location = "Global",
                keyFigure = "Winston Churchill",
                description = "A global war..."
            ),
            HistoryEntity(
                event = "Renaissance",
                startYear = 1300,
                endYear = 1600,
                location = "Europe",
                keyFigure = "Leonardo da Vinci",
                description = "A cultural revival..."
            )
        )
        return Result.Success(DashboardResponse(entities = items, entityTotal = items.size))
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @Test
    fun `load emits Success with correct data`() = runTest {
        val vm = DashboardViewModel(repo = FakeRepoDashboard())

        // act
        vm.load("history")

        // assert
        val state = vm.state.value
        assertTrue(state is Result.Success)
        val data = (state as Result.Success<DashboardResponse>).data
        assertEquals(2, data.entityTotal)
        assertEquals("World War II", data.entities.first().event)
        assertEquals("Renaissance", data.entities.last().event)
    }
}
