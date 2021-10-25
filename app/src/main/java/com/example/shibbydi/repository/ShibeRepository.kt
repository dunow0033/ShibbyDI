package com.example.shibbydi.repository

import com.example.shibbydi.network.ShibeService
import com.example.shibbydi.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShibeRepository @Inject constructor(
    private val shibeService: ShibeService
) {

    fun getShibes() : Flow<Resource<List<String>>> = flow {
        val response = shibeService.getShibes(10, true)

        val resource = try {
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to load shibes.")
            }
        } catch (ex: Exception) {
            Resource.Error(ex.toString())
        }
        emit(resource)
    }
}