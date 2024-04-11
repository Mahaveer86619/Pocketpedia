package com.seven.pocketpedia.data.repository

import android.app.Application
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.seven.pocketpedia.R
import com.seven.pocketpedia.data.mapper.toWordItem
import com.seven.pocketpedia.data.remote.api_service.DictionaryApi
import com.seven.pocketpedia.domain.model.WordItem
import com.seven.pocketpedia.domain.repository.DictionaryRepository
import com.seven.pocketpedia.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi,
    private val application: Application,
): DictionaryRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getWordResult(word: String): Flow<Result<WordItem>> {
        return flow {
            emit(Result.Loading(true))

            val remoteWordResultDto = try {
                dictionaryApi.getWordResult(word)
            } catch (e: HttpException) {

                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.cant_get_result)))
                emit(Result.Loading(false))

                return@flow
            }  catch (e: IOException) {

                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result_i_o_exception)))
                emit(Result.Loading(false))

                return@flow
            }  catch (e: Exception) {

                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result_exception)))
                emit(Result.Loading(false))

                return@flow
            }

            remoteWordResultDto?.let { wordResultDto ->
                emit(Result.Success(wordResultDto[0].toWordItem()))
                emit(Result.Loading(false))

                return@flow
            }

            emit(Result.Loading(false))
        }
    }
}