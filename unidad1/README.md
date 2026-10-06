# Unidad 1. Algoritmos de ordenamiento y búsqueda

[Inicio](../README.md) · [Mapa de temas](../docs/mapa-de-contenidos.md)

## Propósito

Seleccionar e implementar búsquedas y ordenamientos, justificando precondiciones, estabilidad y costos.

## Prueba inicial de comprensión

¿Qué significa encontrar un dato? ¿Qué cambia si los datos están ordenados? ¿Cómo comprobarías que un ordenamiento no perdió valores?

Conserva tus respuestas y revísalas al finalizar. Si necesitas repasar Java, usa la [guía de repaso](../docs/repaso-java.md).

## Recursos

- [Búsqueda secuencial y contrato](01-busqueda-secuencial/README.md): Encontrar la primera coincidencia sin exigir orden previo.
- [Búsqueda binaria y precondición de orden](02-busqueda-binaria/README.md): Reducir un intervalo conservando la posibilidad de encontrar la clave.
- [Transformación de claves y función hash](03-transformacion-de-claves/README.md): Distinguir clave, índice de cubeta y coincidencia real.
- [Colisiones, encadenamiento y factor de carga](04-colisiones-y-encadenamiento/README.md): Guardar claves que comparten cubeta sin perder información.
- [Ordenamiento por burbuja](05-burbuja/README.md): Ordenar mediante intercambios adyacentes y reconocer la parte terminada.
- [Inserción en un prefijo ordenado](06-insercion/README.md): Mantener orden al insertar cada elemento en su lugar.
- [Ordenamiento por selección](07-seleccion/README.md): Seleccionar mínimos y analizar comparaciones frente a intercambios.
- [Merge sort: dividir y mezclar](08-merge-sort/README.md): Combinar dos intervalos ordenados preservando todos sus elementos.
- [Quick sort: partición y límites](09-quick-sort/README.md): Separar alrededor de un pivote y reconocer el peor caso.
- [Cocktail sort: burbuja bidireccional](10-cocktail-sort/README.md): Alternar pasadas y mantener límites coherentes.
- [Análisis de eficiencia: modelo y casos](11-analisis-de-eficiencia/README.md): Relacionar operaciones contadas con crecimiento y espacio.
- [Comparación experimental y estabilidad](12-comparacion-y-estabilidad/README.md): Diseñar una comparación repetible sin convertirla en un benchmark engañoso.

## Taller

[Taller de unidad 1](../talleres/unidad1.md). Resuélvelo al completar el recorrido y conserva las pruebas de los casos límite.

[Siguiente recurso](01-busqueda-secuencial/README.md)
