package kr.hs.dgsw.avocatalk.data.mapper

import kr.hs.dgsw.avocatalk.data.base.BaseEntityMapper
import kr.hs.dgsw.avocatalk.data.database.entity.UserEntity
import kr.hs.dgsw.avocatalk.domain.model.User

object UserMapper : BaseEntityMapper<User, UserEntity> {
    override fun mapToModel(entity: UserEntity): User {
        return User(
            entity.idx,
            entity.email,
            entity.name,
            entity.nickname,
            entity.intro,
            entity.profileImage,
            entity.backgroundImage,
            entity.isAuth,
            entity.joinDate
        )
    }

    override fun mapToEntity(model: User): UserEntity {
        return UserEntity(
            model.idx,
            model.email,
            model.name,
            model.nickname,
            model.intro,
            model.profileImage,
            model.backgroundImage,
            model.isAuth,
            model.joinDate
        )
    }
}