package au.vu.nit3213.history.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import au.vu.nit3213.history.data.Result
import au.vu.nit3213.history.databinding.ActivityDashboardBinding
import au.vu.nit3213.history.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.google.gson.Gson

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var b: ActivityDashboardBinding
    private val vm: DashboardViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(b.root)

        adapter = HistoryAdapter(mutableListOf()) { entity ->
            startActivity(Intent(this, DetailsActivity::class.java)
                .putExtra("entity_json", Gson().toJson(entity)))
        }
        b.rv.layoutManager = LinearLayoutManager(this)
        b.rv.adapter = adapter

        val keypass = intent.getStringExtra("keypass") ?: "history"
        lifecycleScope.launch { vm.load(keypass) }

        lifecycleScope.launch {
            vm.state.collect { r ->
                when (r) {
                    is Result.Loading -> {}
                    is Result.Error -> Toast.makeText(this@DashboardActivity, r.message, Toast.LENGTH_SHORT).show()
                    is Result.Success -> adapter.submit(r.data.entities)
                }
            }
        }
    }
}
