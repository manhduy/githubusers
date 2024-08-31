package com.duyha.data.local.converter

import androidx.room.TypeConverter
import com.duyha.data.local.model.UserDbModel
import com.duyha.data.local.model.toEntity
import com.duyha.domain.entity.User

/**
 * @author: DuyHa
 * @date: 31/08/2024
 */
class UserTypeConverter {
    @TypeConverter
    fun toUserEntity(model: UserDbModel): User {
        return model.toEntity()
    }
}