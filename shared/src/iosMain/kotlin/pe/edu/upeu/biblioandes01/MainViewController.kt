package pe.edu.upeu.biblioandes01

import androidx.compose.ui.window.ComposeUIViewController
import pe.upeu.biblioandes.di.initKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoinIOS() {
    initKoin()
}