import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.util.Base64

class PreferencesManager(private val context: Context) {

    private val API_KEY = stringPreferencesKey("api_key")
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = API_KEY.toString())

    @RequiresApi(Build.VERSION_CODES.O)
    fun storeApiKey(apiKey: String) {
        val encodedApiKey = Base64.getEncoder().encodeToString(apiKey.toByteArray())
        runBlocking {
            context.dataStore.edit { settings ->
                settings[API_KEY] = encodedApiKey
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getApiKey(): String {
        return runBlocking {
            val apiKeyFlow = context.dataStore.data.map { preferences ->
                preferences[API_KEY] ?: ""
            }
            val encodedApiKey = apiKeyFlow.first()
            val decodedBytes = Base64.getDecoder().decode(encodedApiKey)
            String(decodedBytes)
        }
    }
}
