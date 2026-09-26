package pe.upeu.biblioandes.data.local

import pe.upeu.biblioandes.domain.model.EstadoPrestamo
import pe.upeu.biblioandes.domain.model.Estudiante
import pe.upeu.biblioandes.domain.model.Libro
import pe.upeu.biblioandes.domain.model.Prestamo

object DatosSimulados {
    val estudiante = Estudiante(
        "E-2291", 
        "Diego Huamán Ccama",
        "Ingeniería de Sistemas", 
        "diego.huaman@correo.pe"
    )

    val categorias = listOf("Programación", "Matemática", "Redes", "Gestión", "Literatura")

    val libros = listOf(
        Libro(1, "Kotlin en profundidad", "M. Salazar", 2023, "Programación", "Central", 3),
        Libro(2, "Estructuras de datos", "R. Peña", 2021, "Programación", "Central", 0),
        Libro(3, "Cálculo aplicado", "L. Ortega", 2019, "Matemática", "Sede Norte", 2),
        Libro(4, "Redes de computadoras", "A. Medina", 2022, "Redes", "Sede Sur", 4),
        Libro(5, "Seguridad en redes", "P. Ríos", 2024, "Redes", "Central", 0),
        Libro(6, "Gestión de proyectos", "S. Delgado", 2021, "Gestión", "Sede Norte", 2),
        Libro(7, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Literatura", "Central", 5),
        Libro(8, "Cien años de soledad", "Gabriel García Márquez", 1967, "Literatura", "Sede Sur", 1),
        Libro(9, "Desarrollo Ágil", "C. Martinez", 2020, "Gestión", "Central", 3),
        Libro(10, "Álgebra Lineal", "J. Silva", 2018, "Matemática", "Sede Sur", 2),
        Libro(11, "Arquitectura Clean", "R. Martin", 2017, "Programación", "Central", 4),
        Libro(12, "Introducción a IoT", "A. Torres", 2023, "Redes", "Sede Norte", 2)
    )

    val prestamos = listOf(
        Prestamo(1, libros[0], "2026-09-20", "2026-09-27", EstadoPrestamo.Activo(1)),
        Prestamo(2, libros[3], "2026-09-24", "2026-10-01", EstadoPrestamo.Activo(5)),
        Prestamo(3, libros[2], "2026-08-20", "2026-08-27", EstadoPrestamo.Devuelto("2026-08-26")),
        Prestamo(4, libros[1], "2026-08-05", "2026-08-12", EstadoPrestamo.Devuelto("2026-08-11")),
        Prestamo(5, libros[5], "2026-08-28", "2026-09-04", EstadoPrestamo.Vencido(22))
    )
}
