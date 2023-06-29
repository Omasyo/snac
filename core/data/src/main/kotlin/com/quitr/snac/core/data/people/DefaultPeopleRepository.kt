package com.quitr.snac.core.data.people

import android.util.Log
import com.quitr.snac.core.data.Error
import com.quitr.snac.core.data.Response
import com.quitr.snac.core.data.Success
import com.quitr.snac.core.data.mapppers.toPersonDetails
import com.quitr.snac.core.model.PersonDetails
import com.quitr.snac.core.network.people.PeopleNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "DefaultPeopleRepository"

class DefaultPeopleRepository @Inject constructor(
    private val networkDataSource: PeopleNetworkDataSource,
    @Named("IO") private val dispatcher: CoroutineDispatcher,
) :
    PeopleRepository {
    override suspend fun getDetails(id: Int, language: String): Response<PersonDetails> =
        withContext(dispatcher) {
            try {
                val result = networkDataSource.getDetails(id, language).toPersonDetails()
                Success(result)
            } catch (exception: Exception) {
                Log.d(TAG, "getDetails: $exception")
                Error
            }
        }

}