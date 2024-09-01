package com.duyha.data.local.converter

import androidx.room.TypeConverter
import com.duyha.data.local.model.UserDbModel
import com.duyha.data.local.model.toEntity
import com.duyha.domain.entity.User

/**
 * Converter for converting UserDbModel to User.
 */
class UserTypeConverter {
    @TypeConverter
    fun toUserEntity(model: UserDbModel): User {
        return model.toEntity()
    }
}