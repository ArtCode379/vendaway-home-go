package vendaway.housewares.vendawayhomego.data.repository

import vendaway.housewares.vendawayhomego.data.datastore.OJFXTOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OJFXTOnboardingRepo(
    private val ojfxtOnboardingStoreManager: OJFXTOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return ojfxtOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            ojfxtOnboardingStoreManager.setOnboardedState(state)
        }
    }
}