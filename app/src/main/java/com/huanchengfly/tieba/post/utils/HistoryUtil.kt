package com.huanchengfly.tieba.post.utils

import com.huanchengfly.tieba.post.models.database.History
import kotlinx.coroutines.flow.Flow

object HistoryUtil {
    const val PAGE_SIZE = 100
    const val TYPE_FORUM = 1
    const val TYPE_THREAD = 2

    suspend fun deleteAll() {
        DatabaseUtil.deleteAllHistory()
    }

    suspend fun saveHistory(history: History) {
        DatabaseUtil.upsertHistory(history)
    }

    suspend fun getAll(): List<History> = DatabaseUtil.getAllHistory()

    suspend fun getAll(type: Int): List<History> = DatabaseUtil.getHistoryByType(type)

    fun getFlow(type: Int, page: Int): Flow<List<History>> =
        DatabaseUtil.getHistoryFlowByType(type)
}
