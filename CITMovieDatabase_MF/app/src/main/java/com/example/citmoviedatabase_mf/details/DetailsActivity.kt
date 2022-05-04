package com.example.citmoviedatabase_mf.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.activities.MainActivity
import com.example.citmoviedatabase_mf.databinding.ActivityDetailsBinding
import com.example.citmoviedatabase_mf.models.CastAndCrewModel
import com.example.citmoviedatabase_mf.models.PhotoModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        supportActionBar!!.hide()

        binding.ivBackNavigationDetailsIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val castAndCrewList = listOf<CastAndCrewModel>(
            CastAndCrewModel(0, R.drawable.cast_and_crew_keanu_reeves, "Keanu Reeves", "JOHN WICK"),
            CastAndCrewModel(1, R.drawable.cast_and_crew_halle_barry, "Halle Barry", "SOFIA"),
            CastAndCrewModel(2, R.drawable.cast_and_crew_laurence_fishburn, "Laurence Fishburn", "BOWERY KING"),
            CastAndCrewModel(3, R.drawable.cast_and_crew_mark_dacascos, "Mark Dacascos", "ZERO"),
            CastAndCrewModel(4, R.drawable.cast_and_crew_asia_kate_dillon, "Asia Kate Dillon", "ADJUDICATOR"),
            CastAndCrewModel(5, R.drawable.cast_and_crew_lance_reddick, "Lance Reddick", "CHARON"),
            CastAndCrewModel(6, R.drawable.cast_and_crew_angelica_huston, "Angelica Huston", "DIRECTOR"),
            CastAndCrewModel(7, R.drawable.cast_and_crew_margaret_daly, "Margaret Daly", "OPERATOR"),
            CastAndCrewModel(7, R.drawable.cast_and_crew_jerome_flynn, "Jerome Flynn", "BERRADA")
        )

        val castAndCrewAdapter by lazy { CastAndCrewAdapter(castAndCrewList) }

        binding.rvCastAndCrew.adapter = castAndCrewAdapter

        val photoList = listOf<PhotoModel>(
            PhotoModel(0, R.drawable.scene1_john_wick),
            PhotoModel(1, R.drawable.scene2_john_wick),
            PhotoModel(2, R.drawable.scene3_john_wick),
        )

        val photoAdapter by lazy { PhotoAdapter(photoList) }

        binding.rvPhotos.adapter = photoAdapter

    }
}