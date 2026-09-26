package pe.upeu.biblioandes.data.repository

import kotlinx.coroutines.delay
import pe.upeu.biblioandes.data.local.DatosSimulados
import pe.upeu.biblioandes.domain.model.Estudiante
import pe.upeu.biblioandes.domain.model.Libro
import pe.upeu.biblioandes.domain.model.Prestamo
import pe.upeu.biblioandes.domain.repository.BibliotecaRepository

class BibliotecaRepositoryFake : BibliotecaRepository {
    private val libros = DatosSimulados.libros.toMutableList()
    private val prestamos = DatosSimulados.prestamos.toMutableList()

    override suspend fun obtenerCatalogo(): List<Libro> {
        delay(800)
        return libros.toList()
    }

    override suspend fun obtenerPrestamos(): List<Prestamo> {
        delay(800)
        return prestamos.toList()
    }

    override suspend fun obtenerEstudiante(): Estudiante {
        delay(800)
        return DatosSimulados.estudiante
    }

    override suspend fun guardarPrestamo(prestamo: Prestamo) {
        delay(800)
        prestamos.add(prestamo)
    }

    override suspend fun actualizarLibro(libro: Libro) {
        delay(800)
        val index = libros.indexOfFirst { it.id == libro.id }
        if (index != -1) {
            libros[index] = libro
        }
    }
}
