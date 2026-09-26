package pe.edu.upeu.biblioandes01

import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import pe.upeu.biblioandes.presentation.theme.BiblioAndesTheme
import pe.upeu.biblioandes.presentation.navigation.AppNavHost

@Composable
fun App() {
    BiblioAndesTheme {
        KoinContext {
            AppNavHost()
        }
    }
}