package io.github.vacxe.domain.interactors

interface Interactor<INPUT, OUTPUT> {
    suspend fun execute(input: INPUT): OUTPUT
}
