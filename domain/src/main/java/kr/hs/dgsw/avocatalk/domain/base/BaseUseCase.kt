package kr.hs.dgsw.avocatalk.domain.base

abstract class BaseUseCase<out T> {

    abstract fun buildUseCaseObservable(): T
}