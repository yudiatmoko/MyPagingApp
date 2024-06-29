package com.iyam.mypagingapp.data.network.api.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.iyam.mypagingapp.model.User

@Keep
data class UserItemResponse(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?
)

fun UserItemResponse.toUser() = User(
    avatar = this.avatar.orEmpty(),
    email = this.email.orEmpty(),
    firstName = this.firstName.orEmpty(),
    lastName = this.lastName.orEmpty(),
    id = this.id ?: 0
)

fun Collection<UserItemResponse>.toUserList() = this.map {
    it.toUser()
}
