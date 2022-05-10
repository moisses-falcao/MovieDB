package com.example.citmoviedatabase_mf.details.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.citmoviedatabase_mf.R
import com.example.citmoviedatabase_mf.databinding.ActivityCastAndCrewDialogBinding
import com.example.citmoviedatabase_mf.databinding.CastAndCrewModelBinding
import com.example.citmoviedatabase_mf.models.CastAndCrewModel

class CastAndCrewDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCastAndCrewDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCastAndCrewDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


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


        val adapter by lazy { CastAndCrewDialogAdapter(castAndCrewList) }

        binding.rvCastAndCrewDialog.adapter = adapter

    }
}