package au.vu.nit3213.history.ui.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import au.vu.nit3213.history.data.remote.HistoryEntity
import au.vu.nit3213.history.databinding.ActivityDetailsBinding
import com.google.gson.Gson

class DetailsActivity : AppCompatActivity() {
    private lateinit var b: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)

        val e = Gson().fromJson(intent.getStringExtra("entity_json"), HistoryEntity::class.java)
        b.tvTitle.text = e.event
        b.tvMeta.text  = "${e.startYear}–${e.endYear} • ${e.location} • Key figure: ${e.keyFigure}"
        b.tvDesc.text  = e.description
    }
}
