# HeroFinder 📋

HeroFinder - Su Portal para descubrir a los verdaderos Héroes.

## Descripción 📄

HeroFinder es una aplicación Android que te permite buscar y explorar información sobre héroes de cómics y películas. Los usuarios pueden buscar héroes por nombre y ver detalles sobre sus estadísticas, biografía, y más. También pueden descubrir nuevos héroes que no conocían con la función "Héroe Aleatorio". 

## Características 🚀

* Búsqueda de héroes de todas las categorías por nombre.
* Visualización de detalles del héroe, incluyendo sus estadísticas, biografía y más.
* Descubre nuevos héroes con la función "Héroe Aleatorio".

## Instalación 🔧

1. Clona este repositorio en tu máquina local.
```
git clone https://github.com/InfiniteNel/hero-finder-app.git
```
2. Abre el proyecto en Android Studio.

3. Construye y ejecuta la aplicación en tu dispositivo o emulador.

## Pantallas 📲

#### SearchHero
![SearchHero](https://i.imgur.com/axZsfgq.png)
![SearchHero](https://i.imgur.com/d1wiEcQ.png)
![SearchHero](https://i.imgur.com/topNwfy.png)

Pantalla de búsqueda de héroe. Al usar la barra de búsqueda envía una petición a la API que devolverá una lista de héroes que coinciden con su búsqueda y los mostrará en su pantalla. Si no hay coincidencias, se muestra una lista vacía. Si la petición a la API falla, mostrara un mensaje de error.

### DetailHero
![DetailHero](https://i.imgur.com/qCppDW5.png)
![DetailHero](https://i.imgur.com/5X6qzgX.png)

Pantalla que muestra la información del Héroe seleccionado en la pantalla SearchHero o RandomHero. La información que muestra es una imagen del Héroe, su nombre, su Biografía, sus estadística, su apariencia y su trabajo.

### RandomHero
![RandomHero](https://i.imgur.com/ZkK1mDg.png)
![RandomHero](https://i.imgur.com/ZGot8pM.png)

Pantalla que muestra una tarjeta que al hacer clic sobre la misma muestra un Héroe aleatorio de la API. Al hacer scroll hacia abajo hace una petición de un nuevo Héroe aleatorio a la API y muestra nuevamente la tarjeta vacía. Al hacer clic sobre la tarjeta con el Héroe revelado muestra la pantalla DetailHero con la información del Héroe.

### Login
![Login](https://i.imgur.com/THQ9KOv.png)

Pantalla para acceder a la aplicación.

### Signup
![Signup](https://i.imgur.com/ys3HS1q.png)

Pantalla para registrarse en la aplicación.

### ForgotPassword
![ForgotPassword](https://i.imgur.com/fpd3L4d.png)

Pantalla para reestablecer la contraseña mediante el correo electrónico.

## Construido con 🛠️

* [Kotlin](https://developer.android.com/kotlin/learn?hl=es-419) - Lenguaje de programación principal.

### Tecnologías Utilizadas

* [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=es-419) - Mostrar la lista de resultados.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=es-419) y [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=es-419) - Arquitectura de la aplicación.

### Dependencias

* [NavComponent](https://developer.android.com/guide/navigation/get-started?hl=es-419) y [SafeArgs](https://developer.android.com/guide/navigation/use-graph/safe-args?hl=es-419) - Navegación entre pantallas.
* [Glide](https://github.com/bumptech/glide) - Carga y almacenamiento en caché de imágenes.
* [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419) - Inyección de dependencias.
* [Retrofit](https://square.github.io/retrofit/) - Comunicación con la API.
* [SwipeRefreshLayout](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout?hl=es-419) - Mecanismo estándar para actualizar el contenido de una vista.
* [Firebase](https://firebase.google.com/?hl=es) - Gestión de usuarios

## Agradecimientos ✍️

<a href="https://www.flaticon.es/iconos-gratis/buscar" title="buscar iconos">Buscar iconos creados por Freepik - Flaticon</a>

<a href="https://www.flaticon.es/iconos-gratis/aleatorio" title="aleatorio iconos">Aleatorio iconos creados por noomtah - Flaticon</a>

<a href="https://www.flaticon.es/iconos-gratis/heroe" title="heroe iconos">Heroe iconos creados por Freepik - Flaticon</a>

<a href="https://www.flaticon.es/iconos-gratis/preguntas-mas-frecuentes" title="preguntas más frecuentes iconos">Preguntas más frecuentes iconos creados por Freepik - Flaticon</a>

<a href="https://www.flaticon.es/iconos-gratis/no-hay-resultados" title="no hay resultados iconos">No hay resultados iconos creados por kmg design - Flaticon</a> 

Agradecemos a la siguiente fuente de datos y API por proporcionar información sobre héroes utilizada en esta aplicación:

* [API de Superhero](https://superheroapi.com/)
