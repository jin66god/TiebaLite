package com.huanchengfly.tieba.post.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft")
data class Draft(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hash: String,
    val content: String,
)
