# SmartStock

SmartStock es una aplicación desarrollada en **Kotlin** para la gestión eficiente de productos en supermercados y tiendas. Permite controlar fechas de vencimiento, gestionar stock y recibir notificaciones sobre productos cercanos a su expiración.

## Tecnologías y Herramientas

- **Kotlin**: Lenguaje de programación principal.
- **MVVM**: Patrón de arquitectura utilizado para la separación de responsabilidades.
- **Room**: Base de datos local para el almacenamiento de productos.
- **Dagger Hilt**: Inyección de dependencias.
- **Retrofit**: Consumo de APIs externas para obtener información de productos.
- **Picasso**: Carga y procesamiento de imágenes.
- **LiveData**: Manejo de datos en tiempo real.

## Características Principales

- Búsqueda de productos por código de barras.
- Asignación de productos a distintas sucursales.
- Control y gestión de stock y vencimientos.
- Notificaciones automáticas cuando faltan 30 y 15 días para la expiración de un producto.
- Uso de API para obtener imagen y nombre del producto.

## Instalación y Configuración

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tuusuario/SmartStock.git
   cd SmartStock
   ```

2. Abrir el proyecto en Android Studio.
3. Asegurarse de tener configurado el entorno de desarrollo de Kotlin.
4. Instalar las dependencias necesarias mediante Gradle.
5. Ejecutar la aplicación en un dispositivo o emulador.

## Dependencias Principales

Agrega estas dependencias en tu archivo `build.gradle`:

```gradle
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
implementation "androidx.room:room-runtime:2.4.2"
kapt "androidx.room:room-compiler:2.4.2"
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.picasso:picasso:2.8"
implementation "com.google.dagger:hilt-android:2.40.5"
kapt "com.google.dagger:hilt-compiler:2.40.5"
```

## Contribución

Si deseas contribuir a SmartStock:
1. Haz un fork del repositorio.
2. Crea una rama con tus cambios: `git checkout -b mi-nueva-funcionalidad`
3. Realiza un commit de tus cambios: `git commit -m 'Agregada nueva funcionalidad'`
4. Sube los cambios a tu repositorio: `git push origin mi-nueva-funcionalidad`
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

¡Espero que SmartStock te ayude a optimizar la gestión de inventarios de tu negocio! 🚀

