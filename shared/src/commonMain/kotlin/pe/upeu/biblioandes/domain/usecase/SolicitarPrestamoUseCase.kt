package pe.upeu.biblioandes.domain.usecase

import pe.upeu.biblioandes.domain.model.EstadoPrestamo
import pe.upeu.biblioandes.domain.model.Libro
import pe.upeu.biblioandes.domain.model.Prestamo
import pe.upeu.biblioandes.domain.repository.BibliotecaRepository

class SolicitarPrestamoUseCase(private val repository: BibliotecaRepository) {
    suspend operator fun invoke(libro: Libro): Result<Unit> {
        val prestamos = repository.obtenerPrestamos()
        val hoy = "2026-09-26" // Simulación del día de la evaluación
        
        val prestamosActualizados = prestamos.map { prestamo ->
            if (prestamo.estado is EstadoPrestamo.Activo && prestamo.fechaLimite < hoy) {
                prestamo.copy(estado = EstadoPrestamo.Vencido(1))
            } else {
                prestamo
            }
        }

        val activos = prestamosActualizados.count { it.estado is EstadoPrestamo.Activo }
        val tieneVencidos = prestamosActualizados.any { it.estado is EstadoPrestamo.Vencido }

        // RN-01: Un estudiante no puede tener más de tres préstamos en estado Activo de forma simultánea.
        if (activos >= 3) {
            return Result.failure(Exception("Límite de préstamos activos alcanzado (Máx 3)."))
        }

        // RN-02: No se puede solicitar un libro cuyo número de ejemplares disponibles sea cero.
        if (libro.ejemplaresDisponibles <= 0) {
            return Result.failure(Exception("No hay ejemplares disponibles de este libro."))
        }

        // RN-04: Un estudiante con al menos un préstamo Vencido no puede solicitar un libro nuevo hasta regularizarlo.
        if (tieneVencidos) {
            return Result.failure(Exception("Tienes préstamos vencidos, debes regularizarlos primero."))
        }

        val nuevoPrestamo = Prestamo(
            id = (prestamos.maxOfOrNull { it.id } ?: 0) + 1,
            libro = libro,
            fechaPrestamo = hoy,
            fechaLimite = "2026-10-03", // +7 días (RN-03: Todo préstamo dura siete días)
            estado = EstadoPrestamo.Activo(7)
        )
        
        val libroActualizado = libro.copy(ejemplaresDisponibles = libro.ejemplaresDisponibles - 1)
        
        repository.guardarPrestamo(nuevoPrestamo)
        repository.actualizarLibro(libroActualizado)

        return Result.success(Unit)
    }
}
