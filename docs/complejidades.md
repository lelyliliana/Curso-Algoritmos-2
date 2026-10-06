# Tabla de complejidades y condiciones

[Inicio](../README.md) · [Siguiente recurso](glosario.md)

Las cotas describen estas implementaciones bajo comparación y acceso elemental de tamaño acotado. n indica elementos, V vértices, E aristas, h altura, α factor de carga. Las cotas de entrada vacía se tratan como constantes. Espacio se refiere al auxiliar salvo aclaración.

| Algoritmo | Mejor | Peor | Auxiliar | Condición |
|---|---|---|---|---|
| Búsqueda lineal primera coincidencia | Θ(1) | Θ(n) | Θ(1) | Sin orden requerido |
| Binaria iterativa | Θ(1) | Θ(log n) | Θ(1) | Arreglo ordenado |
| Encadenamiento hash | Según cubeta | O(n) | Estructura O(n+m) | Esperado O(1+α) con distribución adecuada |
| Burbuja con bandera | Θ(n) | Θ(n²) | Θ(1) | Estable con> |
| Inserción | Θ(n) | Θ(n²) | Θ(1) | Estable con> |
| Selección | Θ(n²) | Θ(n²) | Θ(1) | No estable |
| Merge de las lecciones | Θ(n log n) | Θ(n log n) | Θ(n) | Estable con<= |
| Quick último pivote | Θ(n log n) para particiones equilibradas | Θ(n²) | O(n) pila en peor | No estable |
| Cocktail con bandera | Θ(n) | Θ(n²) | Θ(1) | Estable con> |

| Estructura/operación | Costo | Condición |
|---|---|---|
| Lista simple insertar extremos | Θ(1) | Conserva cola para final |
| Lista simple localizar por clave | O(n) | Recorrido secuencial |
| Lista doble borrar localizado | Θ(1) | La localización se cuenta aparte |
| Pila fija/apilar/desapilar | Θ(1) | Validación de lleno/vacío |
| Cola circular/enqueue/dequeue | Θ(1) | Frente y cantidad consistentes |
| ArrayDeque/extremos | O(1) amortizado | Una ampliación puede copiar |
| BST buscar/insertar/borrar | O(h+1) | Peor O(n), sin balance automático |
| Recorrer árbol | Θ(n) | Árbol acíclico, pila O(h) |
| DFS/BFS con listas por índice | O(V+E) | Inicialización de marcas incluida |
| DFS/BFS con matriz | O(V²) completo | Cada fila inspecciona Vposiciones |
| BFS del proyecto con TreeMap | O(Vlog V+E) | Códigos acotados y accesos hash esperados |

## Interpretación

O no significa peor caso por definición:es una cota superior de la función que analizas. Θ es una cota ajustada. Amortizado distribuye costo de una secuencia de operaciones; esperado usa supuestos probabilísticos. No son lo mismo.

Ordenar para una sola búsqueda puede costar más que buscar directamente. Ordenar una vez para muchas consultas puede compensar, pero las actualizaciones cambian el análisis. El costo de crear copias, validar precondiciones y producir la salida también existe aunque se reporte por separado.

Contar operaciones no garantiza tiempo de reloj. JVM, memoria, datos y entorno afectan mediciones. Declara qué se cuenta y no conviertas una ejecución breve en un ranking universal.
