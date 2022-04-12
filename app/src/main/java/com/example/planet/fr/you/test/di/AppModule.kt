package calm.sleep.relaxing.persons.noise.di

import com.example.planet.fr.you.test.ui.person_screen.PersonViewModel
import com.example.planet.fr.you.test.ui.person_list_screen.PersonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PersonListViewModel(repository = get()) }
    viewModel { PersonViewModel(repository = get()) }
}