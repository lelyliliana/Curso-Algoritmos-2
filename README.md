# Algoritmos 2

Curso de estructuras de datos y algoritmos con Java. El recorrido desarrolla búsquedas y ordenamientos, listas enlazadas, pilas, colas, recursividad, árboles y grafos. Incluye 42 lecciones con casos resueltos, código completo, salidas esperadas, ejercicios, soluciones y análisis de eficiencia.

## Unidades

1. [Algoritmos de ordenamiento y búsqueda](unidad1/README.md): búsqueda secuencial, binaria y transformación de claves; burbuja, inserción, selección, merge sort, quick sort, cocktail sort y análisis de costos.
2. [Manejo de estructuras dinámicas en memoria (listas ligadas)](unidad2/README.md): referencias, nodos, listas simples, ordenadas, circulares y dobles; pilas y colas con arreglos y nodos; dos pilas compartidas y Deque.
3. [Conceptualización de recursividad, árboles y grafos](unidad3/README.md): factorial, Fibonacci, Hanói, árboles generales/binarios, de búsqueda, listas generalizadas, representaciones de grafos, DFS, BFS y componentes.

Se conserva la secuencia de unidades y temas, con explicaciones originales, correcciones conceptuales y prácticas actuales. Se supone conocimiento básico de variables, condicionales, ciclos, métodos y arreglos. Para repasar, consulta [Java esencial](docs/repaso-java.md) o [Algoritmos 1](https://github.com/lelyliliana/Curso-Algoritmos-1).

## Comenzar

Lee [cómo estudiar](docs/como-estudiar.md), prepara el [ambiente en Ubuntu, Windows o macOS](docs/ambiente-y-herramientas.md) y abre el [primer recurso](unidad1/01-busqueda-secuencial/README.md). Cada lección enlaza el recurso anterior, el índice y el siguiente.

Puedes descargar ZIP desde Code → Download ZIP y descomprimirlo, o clonar:

```bash
git clone https://github.com/lelyliliana/Curso-Algoritmos-2.git
cd Curso-Algoritmos-2
```

Cada lección tiene un Main.java independiente. Abre su carpeta y ejecuta:

```bash
java Main.java
```

No compiles todas las clases Main juntas, pues se repite el nombre para facilitar el estudio independiente. Usa JDK 21; no requiere Maven ni dependencias externas.

## Recursos

- [Mapa de temas](docs/mapa-de-contenidos.md) y [convenciones](docs/convenciones.md).
- [Tabla de complejidades](docs/complejidades.md).
- [Glosario](docs/glosario.md) y [fuentes](docs/fuentes.md).
- Talleres:[unidad 1](talleres/unidad1.md), [unidad 2](talleres/unidad2.md), [unidad 3](talleres/unidad3.md).
- [Proyecto integrador:red de lugares y rutas](unidad3/16-proyecto-integrador/README.md).
- [Verificación de ejemplos y pruebas](docs/verificacion.md).
- [Cierre](docs/cierre.md).

## Verificar

Desde la raíz, en Windows:

```powershell
python scripts/verificar.py
```

En Ubuntu/macOS:

```bash
python3 scripts/verificar.py
```

La verificación usa Python 3.10 o posterior para coordinar las pruebas de Java. La automatización comprueba JDK 21 en Ubuntu, Windows y macOS. Compara resultados esperados, ensaya estructuras y revisa enlaces internos.

## Autoría

Material educativo de Lely Liliana Díaz Izquierdo. Los ejemplos utilizan datos ficticios. Las referencias se identifican en las fuentes; las explicaciones y prácticas se desarrollan como contenido propio del curso.
