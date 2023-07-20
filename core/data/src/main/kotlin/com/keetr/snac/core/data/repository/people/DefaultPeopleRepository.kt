package com.keetr.snac.core.data.repository.people

import android.util.Log
import com.keetr.snac.core.data.mapppers.toPersonDetails
import com.keetr.snac.core.model.PersonDetails
import com.keetr.snac.core.network.people.PeopleNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "DefaultPeopleRepository"

class DefaultPeopleRepository @Inject constructor(
    private val networkDataSource: PeopleNetworkDataSource,
    @Named("IO") private val dispatcher: CoroutineDispatcher,
) : PeopleRepository {
    override suspend fun getDetails(id: Int, language: String): Result<PersonDetails> =
        withContext(dispatcher) {
            try {
                val result = networkDataSource.getDetails(id, language).toPersonDetails()
                Result.success(result)
            } catch (exception: Exception) {
                Log.e(TAG, "getDetails: $exception")
                Result.failure(exception)
            }
        }

}