import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dev.essentials.tastybot.repository.StrategyRepository
import dev.essentials.tastybot.viewmodels.StrategyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val context: Context = get()
        context.dataStore
    }

    single { StrategyRepository(get()) }

    viewModel { StrategyViewModel(repository = get()) }
}

val Context.dataStore by preferencesDataStore("user_preferences")
