# Taller 3. Recursión, árboles y red de rutas

[Inicio](../README.md) · [Unidad 3](../unidad3/README.md) · [Proyecto](../unidad3/16-proyecto-integrador/README.md) · [Siguiente recurso](../docs/cierre.md)

## Situación

Una red ficticia tiene lugares A, B, C, D, E. Conexiones A–B, A–C, B–D, C–D; E aislado. Un catálogo de claves 4, 2, 6, 1, 3, 5, 7 se representa como BST. Una jerarquía se expresa como lista generalizada(a, (b, c), ()).

## Actividades

1. Traza factorial 4 indicando llamadas y retornos. Compara memoria con versión iterativa.
2. Calcula F10 con memoización. Declara bases y rango de long. Explica subproblemas repetidos de la versión ingenua.
3. Simula Hanói 3 con pilas de discos. Comprueba movimientos legales y cantidad 7.
4. Construye BST, e identifica raíz, hojas, profundidad y altura. Agrega un duplicado sin alterar conjunto.
5. Escribe preorden, inorden, postorden. Elimina raíz 4 y luego hoja 1; comprueba propiedad global.
6. Lee la lista generalizada y cuenta átomos. Prueba(), (a, ), cierre faltante y texto sobrante.
7. Representa la red como matriz, listas de adyacencia e incidencia. Explica dimensiones y duplicación física de una arista no dirigida.
8. Traza DFS y BFS desde A con vecinos ordenados. Determina ruta mínima a D y respuesta para E.
9. Cuenta componentes, conecta D–E y comprueba el cambio.
10. Amplía proyecto con desconectar, documentando simetría, ausencia y efecto en rutas. Conserva guardado/carga y ensaya archivo inválido.

## Resultados de referencia

BST inorden [1, 2, 3, 4, 5, 6, 7]; preorden [4, 2, 1, 3, 6, 5, 7]; postorden [1, 3, 2, 5, 7, 6, 4]. Al eliminar 4 por sucesor, inorden [1, 2, 3, 5, 6, 7]. Lista generalizada contiene 3 átomos.

BFS desde A encuentra [A, B, D] con 2 conexiones. También existe [A, C, D], igual de corta. E aísla una segunda componente. Tras conectar D–E hay una componente y ruta A→E de 3 conexiones.

<details>
<summary>Solución orientativa</summary>

Factorial 4 crea cadena hasta 0 y retorna 1, 1, 2, 6, 24. La versión recursiva usa pila proporcional a n. Fibonacci memoizado calcula cada índice no base una vez; no evita el crecimiento numérico y requiere validación de rango. Hanói 3 genera 7 pasos; se deben simular discos para comprobar que no se coloca uno grande sobre pequeño.

Eliminar raíz BST con dos hijos copia sucesor 5 y lo retira de su posición anterior. El borrado posterior de 1 retira una hoja. Inorden queda [2, 3, 5, 6, 7]. La propiedad de búsqueda debe verificarse con límites de subárbol, no solo con hijos inmediatos.

La matriz de adyacencia es 5×5 y incidencia 5×4. Las listas contienen 8 pertenencias de arista y una entrada vacía para E. La incidencia tiene dos 1 por columna. Una matriz arista-arista representaría otra relación.

DFS desde A puede producir A, B, D, C y no E. BFS visita capas y reconstruye mínima cantidad de aristas. Para desconectar, retira ambas pertenencias solo después de validar extremos. Si no existía, devuelve false y conserva estado. Una carga inválida no debe reemplazar la red activa. Introducir pesos requiere replantear el problema de mínima ruta.

</details>

## Entrega y criterios

Recursión y límites 15%; árboles y listas generalizadas 25%; representaciones y recorridos 25%; proyecto y pruebas 25%; documentación 10%. Incluye análisis de memoria, pila y representación, además de resultados.
