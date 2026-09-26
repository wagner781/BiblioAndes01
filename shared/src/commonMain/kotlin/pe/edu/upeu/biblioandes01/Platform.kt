package pe.edu.upeu.biblioandes01

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform