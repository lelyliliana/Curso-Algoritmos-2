# Ambiente y herramientas

[Inicio](../README.md) · [Lección de configuración](../unidad1/01-busqueda-secuencial/README.md)

## Elegir tu sistema

Los ejemplos usan Java y rutas relativas construidas con `Path`, por lo que no requieren un sistema operativo específico. Instala un **JDK 21** adecuado para tu equipo. Puedes usar [Eclipse Temurin](https://adoptium.net/temurin/releases/?version=21) y consultar sus [instrucciones oficiales de instalación](https://adoptium.net/installation/).

| Sistema | Terminal del curso | Instalación del JDK |
|---|---|---|
| Windows | PowerShell | Instalador `.msi` de Temurin 21 para tu arquitectura |
| Ubuntu | Terminal | Paquete `openjdk-21-jdk` o distribución Temurin 21 |
| macOS | Terminal | Instalador `.pkg` de Temurin 21 para Apple Silicon (aarch64) o Intel (x64) |

Elige una sola ruta de instalación. No necesitas instalar varios JDK ni utilizar WSL, Docker o una máquina virtual.

## Windows: preparar el JDK

1. Descarga el instalador de **JDK 21** para Windows desde Temurin. Comprueba la arquitectura de tu equipo (habitualmente x64; ARM64 requiere su distribución correspondiente).
2. Ejecuta el instalador. En sus opciones, habilita agregar Java a `PATH` y establecer `JAVA_HOME`, si aparecen.
3. Cierra PowerShell y abre una ventana nueva.
4. Ejecuta `java -version` y después `javac -version`. Ambos deben indicar 21.
5. Si no se reconocen, revisa las variables de entorno de Windows: `JAVA_HOME` debe apuntar a la carpeta instalada del JDK y `PATH` debe incluir su subcarpeta `bin`. No copies una ruta de otro computador.

Para abrir una terminal en la carpeta del repositorio, ábrela en el Explorador de archivos y utiliza **Abrir en Terminal**, o entra desde PowerShell con `cd "ruta de tu carpeta"`.

Si las tildes se ven alteradas al ejecutar, establece UTF-8 para esa sesión antes de volver a iniciar Java:

```powershell
chcp 65001
```

Guarda también los fuentes como UTF-8. Este ajuste se aplica a la consola; los archivos del curso indican su codificación explícitamente.

## Ubuntu: preparar el JDK

1. Abre Terminal y actualiza el índice de paquetes:

```bash
sudo apt update
```

2. Instala el JDK 21 si el paquete está disponible en tus repositorios:

```bash
sudo apt install openjdk-21-jdk
```

3. Ejecuta `java -version` y después `javac -version`. Ambos deben indicar 21.
4. Si tu versión de Ubuntu no ofrece ese paquete, utiliza una distribución JDK 21 desde Temurin siguiendo sus instrucciones para Linux. No agregues repositorios ajenos sin revisar su procedencia.

Si ya tienes varios JDK, comprueba las selecciones con `sudo update-alternatives --config java` y `sudo update-alternatives --config javac`. Elige la misma versión principal para ambos.

## macOS: preparar el JDK

1. Consulta **Acerca de este Mac** para identificar Apple Silicon o Intel.
2. Descarga Temurin **JDK 21** para macOS: aarch64 para Apple Silicon o x64 para Intel.
3. Abre el instalador `.pkg` y completa la instalación.
4. Abre una ventana nueva de Terminal. Ejecuta `java -version` y después `javac -version`.
5. Si tienes varios JDK, consulta los instalados con `/usr/libexec/java_home -V`. Para seleccionar Java 21 en la sesión actual, usa:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH="$JAVA_HOME/bin:$PATH"
```

Vuelve a comprobar las versiones. No necesitas Homebrew para seguir esta ruta.

## Comprobación común

`JAVA_HOME` apunta a la carpeta del JDK cuando una herramienta lo requiere; `PATH` permite localizar su carpeta `bin`. No establezcas un `CLASSPATH` global para este curso. Si usas un IDE, selecciona también JDK 21 en su configuración.

Los comandos `cd`, `java Main.java`, `javac -encoding UTF-8 -d out Main.java` y `java -cp out Main` usados en las lecciones funcionan en PowerShell y en las terminales de Ubuntu/macOS. Las rutas de los ejemplos utilizan `/`, aceptado por PowerShell. En rutas propias con espacios, usa comillas.

## Dos maneras de ejecutar

Cada ejemplo es independiente y tiene Main.java. Desde su propia carpeta:

```bash
java Main.java
```

El lanzamiento del fuente compila en memoria y ejecuta. Para observar las etapas separadas, crea primero una carpeta out:

```bash
mkdir out
```

Compila el archivo (incluidas sus clases de apoyo):

```bash
javac -encoding UTF-8 -d out Main.java
```

Ejecuta el bytecode:

```bash
java -cp out Main
```

Si out ya existe, no hace falta volver a crearla. Después de editar el fuente debes recompilar para que la ejecución del bytecode refleje el cambio. `java Main.java` realiza esa preparación cada vez.

## Editor e IDE

Puedes usar [VS Code con soporte de Java](https://code.visualstudio.com/docs/languages/java), [IntelliJ IDEA](https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html), [Eclipse](https://www.eclipse.org/downloads/) u otro editor. Abre una carpeta de ejemplo por vez: varios Main en el mismo paquete producirían clases duplicadas. El editor debe guardar los archivos en UTF-8.

## Diagnóstico por síntoma

| Síntoma | Qué revisar primero |
|---|---|
| `java` no se reconoce | JDK instalado, terminal nueva y PATH |
| `javac` no existe | Que instalaste JDK completo, no solo un runtime |
| Archivo no encontrado | Carpeta actual y nombre Main.java |
| Clase duplicada Main | Ejemplos distintos importados como un único módulo |
| Resultado antiguo | Fuente guardado y recompilación si ejecutas `.class` |
| Tildes alteradas | UTF-8 del fuente, archivo y consola |
| El programa espera | Está pidiendo datos: revisa la lección de entrada |
| Error después de una edición | Mensaje completo, línea indicada y línea anterior |

Empieza por reproducir en terminal. Si funciona allí y falla en el IDE, revisa JDK seleccionado, carpeta de trabajo y configuración de ejecución del IDE. Cambiar el algoritmo no corrige una herramienta mal configurada.
