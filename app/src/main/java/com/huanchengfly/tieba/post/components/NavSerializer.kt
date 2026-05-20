package com.huanchengfly.tieba.post.components

import android.util.LruCache
import com.huanchengfly.tieba.post.api.models.protos.ThreadInfo
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicInteger


object ThreadNavBridge {
    private val counter = AtomicInteger(0)
    private const val MAX_CACHE_SIZE = 4

    private val cache = LruCache<Int, WeakReference<ThreadInfo>>(MAX_CACHE_SIZE)

    fun put(data: ThreadInfo): String {
        val id = counter.getAndIncrement()
        cache.put(id, WeakReference(data))
        return id.toString()
    }

    fun getAndRemove(key: String): ThreadInfo? {
        val id = key.toIntOrNull() ?: return null
        return cache.remove(id)?.get()
    }
}

@NavTypeSerializer
class ThreadInfoSerializer : DestinationsNavTypeSerializer<ThreadInfo> {
    override fun toRouteString(value: ThreadInfo): String {
        return ThreadNavBridge.put(value)
    }

    override fun fromRouteString(routeStr: String): ThreadInfo {
        return ThreadNavBridge.getAndRemove(routeStr) ?: ThreadInfo()
    }
}