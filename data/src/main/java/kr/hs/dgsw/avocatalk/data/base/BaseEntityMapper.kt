package kr.hs.dgsw.avocatalk.data.base

interface BaseEntityMapper<M, E> {

    fun mapToModel(entity: E): M

    fun mapToEntity(model: M): E
}