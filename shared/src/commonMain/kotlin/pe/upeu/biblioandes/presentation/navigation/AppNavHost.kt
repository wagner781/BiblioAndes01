package pe.upeu.biblioandes.presentation.navigation

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pe.upeu.biblioandes.presentation.catalogo.CatalogoScreen
import pe.upeu.biblioandes.presentation.detalle.DetalleLibroScreen
import pe.upeu.biblioandes.presentation.inicio.InicioScreen
import pe.upeu.biblioandes.presentation.perfil.PerfilScreen
import pe.upeu.biblioandes.presentation.prestamos.PrestamosScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(Destinos.Inicio, Destinos.Catalogo, Destinos.Prestamos, Destinos.Perfil)) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Text("🏠") },
                        label = { Text("Inicio") },
                        selected = currentRoute == Destinos.Inicio,
                        onClick = {
                            navController.navigate(Destinos.Inicio) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Text("📖") },
                        label = { Text("Catálogo") },
                        selected = currentRoute == Destinos.Catalogo,
                        onClick = {
                            navController.navigate(Destinos.Catalogo) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Text("📚") },
                        label = { Text("Préstamos") },
                        selected = currentRoute == Destinos.Prestamos,
                        onClick = {
                            navController.navigate(Destinos.Prestamos) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Text("👤") },
                        label = { Text("Perfil") },
                        selected = currentRoute == Destinos.Perfil,
                        onClick = {
                            navController.navigate(Destinos.Perfil) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinos.Inicio,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destinos.Inicio) {
                InicioScreen(
                    onNavigateToCatalogo = { navController.navigate(Destinos.Catalogo) },
                    onNavigateToPrestamos = { navController.navigate(Destinos.Prestamos) }
                )
            }
            composable(Destinos.Catalogo) {
                CatalogoScreen(
                    onNavigateToDetalle = { id -> navController.navigate(Destinos.crearRutaDetalle(id)) }
                )
            }
            composable(Destinos.Prestamos) {
                PrestamosScreen()
            }
            composable(Destinos.Perfil) {
                PerfilScreen()
            }
            composable(Destinos.Detalle) { backStackEntry ->
                val idString = backStackEntry.arguments?.getString("libroId")
                val libroId = idString?.toIntOrNull() ?: 0
                DetalleLibroScreen(
                    libroId = libroId,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
