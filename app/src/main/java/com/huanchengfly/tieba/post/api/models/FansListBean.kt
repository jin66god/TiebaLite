package com.huanchengfly.tieba.post.api.models

import com.google.gson.annotations.SerializedName
import com.huanchengfly.tieba.post.models.BaseBean

/**
 * 粉丝列表（客户端接口 /c/u/fans/page）
 */
data class FansListBean(
    @SerializedName("error_code")
    val errorCode: Int = 0,
    @SerializedName("error_msg")
    val errorMsg: String? = null,
    val time: Long = 0,
    val logid: String? = null,
    @SerializedName("tips_text")
    val tipsText: String? = null,
    @SerializedName("user_list")
    var userList: List<FollowListBean.FollowUserBean> = emptyList(),
    @SerializedName("page")
    val page: FansPageBean? = null,
) : BaseBean() {

    val hasMore: Boolean
        get() = page?.hasMore == 1

    data class FansPageBean(
        @SerializedName("page_size")
        val pageSize: Int = 0,
        @SerializedName("current_page")
        val currentPage: Int = 1,
        @SerializedName("total_page")
        val totalPage: Int = 0,
        @SerializedName("total_count")
        val totalCount: Int = 0,
        @SerializedName("has_more")
        val hasMore: Int = 0,
        @SerializedName("has_prev")
        val hasPrev: Int = 0,
    )
}
