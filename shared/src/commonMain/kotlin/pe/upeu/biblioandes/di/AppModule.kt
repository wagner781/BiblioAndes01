package pe.upeu.biblioandes.di

import org.koin.core.context.startKoin
import org.koin.dsl.module
import pe.upeu.biblioandes.data.repository.BibliotecaRepositoryFake
import pe.upeu.biblioandes.domain.repository.BibliotecaRepository
import pe.upeu.biblioandes.domain.usecase.ObtenerCatalogoUseCase
import pe.upeu.biblioandes.domain.usecase.ObtenerPrestamosUseCase
import pe.upeu.biblioandes.domain.usecase.SolicitarPrestamoUseCase

val appModule = module {
    single<BibliotecaRepository> { BibliotecaRepositoryFake() }
    
    factory { ObtenerCatalogoUseCase(get()) }
    factory { ObtenerPrestamosUseCase(get()) }
    factory { SolicitarPrestamoUseCase(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
