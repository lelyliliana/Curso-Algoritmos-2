# Taller 1. Búsqueda, ordenamiento y evidencia de eficiencia

[Inicio](../README.md) · [Unidad 1](../unidad1/README.md) · [Siguiente recurso](../unidad2/01-referencias-y-memoria/README.md)

## Situación

Un catálogo ficticio almacena códigos numéricos [12, 7, 12, -3, 20, 0]. Se desea consultar la primera posición original, consultar presencia muchas veces y preparar un reporte ascendente conservando todos los registros.

## Actividades

1. Define tres contratos distintos:no confundir posición original, presencia y posición después de ordenar.
2. Implementa búsqueda lineal y binaria. Para binaria usa una copia ordenada y declara su precondición.
3. Traza las consultas 12, 0 y 99. Incluye vacío y duplicados.
4. Construye una tabla encadenada con 5 cubetas. Registra colisiones y explica por qué una capacidad prima no las elimina.
5. Implementa burbuja, inserción y selección. Explica un invariante de cada algoritmo.
6. Ejecuta merge, quick y cocktail sobre copias de los mismos datos. Compara con Arrays.sort y comprueba que original no cambia.
7. Usa registros(clave, id) para demostrar estabilidad de inserción y un caso de inestabilidad de selección.
8. Cuenta comparaciones en ordenado, inverso y duplicados para un tamaño pequeño. Declara exactamente qué cuenta.
9. Compara consulta única y muchas consultas sobre datos que no cambian. Incluye costo previo de ordenar o construir tabla.
10. Explica peor caso de quick con último pivote y espacio de su pila.

## Caso resuelto

| Consulta | Primera posición original | En arreglo ordenado |
|---|---|---|
| 12 | 0 | Cualquier índice 3 o 4 bajo contrato binario |
| 0 | 5 | Índice 1 |
| 99 | -1 | -1 |

Ordenado [-3, 0, 7, 12, 12, 20]. La posición después de ordenar no responde dónde estaba originalmente.

<details>
<summary>Solución orientativa</summary>

Para conservar posición original, se usa lineal sobre original o se almacenan pares(clave, posicion). Para presencia reiterada, una tabla puede ser útil bajo una distribución adecuada; una copia ordenada permite binaria tras pagar el ordenamiento. Si hay cambios, se deben actualizar estructuras y repetir el análisis.

Con módulo 5, 12 y 7 colisionan en 2; −3 también selecciona 2 con floorMod; 20 y 0 colisionan en 0. La cubeta guarda claves distintas y búsqueda compara clave real. Repetir 12 como clave no crea entrada nueva en el conjunto del ejemplo, pero en un catálogo de registros duplicados se requiere otra política.

Selección compara n(n−1)/2 y no mejora a lineal con datos ordenados. Burbuja con bandera e inserción pueden terminar con trabajo lineal en ese caso. Con último pivote, quick ordenado puede generar tamaños n−1, n−2, ...y costo cuadrático. Su pila también puede ser lineal. Para estabilidad, usa [2 A, 2 B, 1 C]:selección deja [1 C, 2 B, 2 A].

Las pruebas requieren entrada equivalente y referencia que conserve multiplicidades. Mostrar una secuencia creciente no basta si faltan valores. La comparación de tiempos necesita más diseño que una ejecución con nanoTime.

</details>

## Entrega y criterios

Entrega contratos, código, trazas, casos y análisis. Revisa:contratos 20%; implementación 25%; pruebas 25%; costos y supuestos 20%; claridad 10%. Conserva los intentos fallidos y la explicación de la corrección.
