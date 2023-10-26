package com.jroslar.heroapp.ui.searchhero

import androidx.lifecycle.ViewModel
import com.jroslar.heroapp.domain.model.AppearanceModel
import com.jroslar.heroapp.domain.model.BiographyModel
import com.jroslar.heroapp.domain.model.HeroModel
import com.jroslar.heroapp.domain.model.ImageModel
import com.jroslar.heroapp.domain.model.PowerstatsModel
import com.jroslar.heroapp.domain.model.WorkModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchHeroViewModel: ViewModel() {

    private var _heroList = MutableStateFlow<List<HeroModel>>(emptyList())
    val heroList: StateFlow<List<HeroModel>> = _heroList

    init {
        _heroList.value = listOf(
            HeroModel("195", "Cyborg Superman",
                PowerstatsModel("75", "93", "92", "100", "100", "80"),
                BiographyModel("Henry Henshaw","No alter egos found.", listOf("Grandmaster of the Manhunters", "Herald of the Anti-Monitor", "Alpha-Prime of the Alpha Lanterns"),"-","Adventures of Superman #466 (May, 1990)","DC Comics","bad"),
                AppearanceModel("Male","Cyborg", listOf("-", "0 cm"), listOf("- lb", "0 kg"),"Blue","Black"),
                WorkModel("-", "Warworld, Qward, Antimatter Universe, formerly Biot, Sector 3601"),
                ImageModel("https://www.superherodb.com/pictures2/portraits/10/100/667.jpg")
            )
        )
    }
}