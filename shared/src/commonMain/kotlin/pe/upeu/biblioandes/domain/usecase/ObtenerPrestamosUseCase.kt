package pe.upeu.biblioandes.domain.usecase

import pe.upeu.biblioandes.domain.model.EstadoPrestamo
import pe.upeu.biblioandes.domain.model.Prestamo
import pe.upeu.biblioandes.domain.repository.BibliotecaRepository

class ObtenerPrestamosUseCase(private val repository: BibliotecaRepository) {
    suspend operator fun invoke(): List<Prestamo> {
        val prestamos = repository.obtenerPrestamos()
        val hoy = "2026-09-26" // Simulación del día de la evaluación
        
        // RN-03: Todo préstamo dura siete días; si la fecha de devolución ya pasó, el préstamo se muestra como Vencido.
        return prestamos.map { prestamo ->
            if (prestamo.estado is EstadoPrestamo.Activo && prestamo.fechaLimite < hoy) {
                prestamo.copy(estado = EstadoPrestamo.Vencido(1))
            } else {
                prestamo
            }
        }.sortedBy { it.fechaLimite } // RF-04: ordenado por fecha de devolución más próxima
    }
}
