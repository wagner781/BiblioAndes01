package pe.upeu.biblioandes.presentation.navigation

object Destinos {
    const val Inicio = "inicio"
    const val Catalogo = "catalogo"
    const val Prestamos = "prestamos"
    const val Perfil = "perfil"
    const val Detalle = "detalle/{libroId}"
    
    fun crearRutaDetalle(libroId: Int): String {
        return "detalle/$libroId"
    }
}
