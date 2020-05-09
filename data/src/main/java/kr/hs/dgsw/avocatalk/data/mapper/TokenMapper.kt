package kr.hs.dgsw.avocatalk.data.mapper

import kr.hs.dgsw.avocatalk.data.base.BaseEntityMapper
import kr.hs.dgsw.avocatalk.domain.model.Token
import kr.hs.dgsw.avocatalk.data.database.entity.TokenEntity

object TokenMapper: BaseEntityMapper<Token, TokenEntity> {
    override fun mapToModel(entity: TokenEntity): Token {
        return Token(
            entity.token
        )
    }

    override fun mapToEntity(model: Token): TokenEntity {
        return TokenEntity(
            model.token
        )
    }
}