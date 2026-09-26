package pe.upeu.biblioandes.domain.repository

import pe.upeu.biblioandes.domain.model.Estudiante
import pe.upeu.biblioandes.domain.model.Libro
import pe.upeu.biblioandes.domain.model.Prestamo

interface BibliotecaRepository {
    suspend fun obtenerCatalogo(): List<Libro>
    suspend fun obtenerPrestamos(): List<Prestamo>
    suspend fun obtenerEstudiante(): Estudiante
    suspend fun guardarPrestamo(prestamo: Prestamo)
    suspend fun actualizarLibro(libro: Libro)
}
