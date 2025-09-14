package au.vu.nit3213.history.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import au.vu.nit3213.history.data.Result
import au.vu.nit3213.history.databinding.ActivityLoginBinding
import au.vu.nit3213.history.ui.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var b: ActivityLoginBinding
    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        // prefill for demo
        b.etFirstName.setText("MD Parvez Hussain")
        b.etStudentId.setText("8131988")

        b.btnLogin.setOnClickListener {
            val user = b.etFirstName.text.toString().trim()
            val pass = b.etStudentId.text.toString().trim()
            lifecycleScope.launch { vm.doLogin("br", user, pass) }
        }

        lifecycleScope.launch {
            vm.state.collect { r ->
                when (r) {
                    is Result.Loading -> {}
                    is Result.Error ->
                        Toast.makeText(this@LoginActivity, r.message, Toast.LENGTH_SHORT).show()
                    is Result.Success -> {
                        if (r.data.isBlank()) return@collect
                        startActivity(
                            Intent(this@LoginActivity, DashboardActivity::class.java)
                                .putExtra("keypass", r.data)
                        )
                    }
                }
            }
        }
    }
}
