# Taller 2. Estructuras dinámicas y secuencias de operaciones

[Inicio](../README.md) · [Unidad 2](../unidad2/README.md) · [Siguiente recurso](../unidad3/01-recursividad-y-caso-base/README.md)

## Situación

Una mesa de trabajo registra solicitudes ficticias en orden de llegada. Los turnos se atienden FIFO. Las acciones recientes pueden deshacerse LIFO. Una lista doble mantiene la secuencia para revisar en ambos sentidos. Son estructuras distintas, aunque almacenen los mismos códigos.

## Actividades

1. Define TAD y representación para cola de turnos, pila de acciones y lista de revisión.
2. Implementa cola circular fija de capacidad 3 y cola enlazada. Ejecuta la misma secuencia:agregar 4, 8, 2; retirar 4; agregar 6; retirar hasta vacía; agregar 9 y retirar.
3. Comprueba rechazo al llenar cola fija y rechazo al retirar vacía, sin modificar estado.
4. Implementa pila fija y enlazada. Agrega 4, 8, 2 y retira:debe producir 2, 8, 4.
5. Construye lista simple, elimina primero, intermedio, último y único. Revisa cabeza, cola, cantidad y terminación.
6. En lista doble, comprueba anterior/siguiente y recorridos directo/inverso después de cada cambio.
7. Recorre lista circular una sola vuelta incluso con datos repetidos.
8. Comparte un arreglo entre dos pilas que crecen desde extremos opuestos. Prueba uso de toda capacidad por una sola.
9. Representa un polinomio por términos ordenados y combina exponentes iguales. Documenta tipo numérico.
10. Compara con ArrayDeque para turnos y acciones. Explica amortización y restricciones de null.

## Invariantes para comprobar

| Estructura | Propiedad |
|---|---|
| Lista simple vacía | Cabeza/cola null, cantidad 0 |
| Lista simple no vacía | Cola.siguiente null |
| Lista doble | a.siguiente=bimplica b.anterior=a |
| Circular | Último enlaza al primero |
| Cola circular | 0≤cantidad≤capacidad |
| Dos pilas | izquierda<derecha |

<details>
<summary>Solución orientativa</summary>

La cola produce 4, 8, 2, 6 y luego 9 tras reutilización. La pila produce 2, 8, 4. Si ambas producen la misma salida, se mezclaron sus reglas. La cola enlazada debe limpiar fin al retirar último; si no, la siguiente inserción puede partir de un nodo que ya no es frente activo.

En la circular fija, retirar libera capacidad y encolar 6 reutiliza índice 0. cantidad distingue lleno/vacío incluso en capacidad 1. En lista doble, revisa ambos recorridos:el inverso debe ser reverso del directo y cada vecino debe ser simétrico. En lista circular, termina por identidad de nodo inicial, no por igualdad de dato.

Polinomio 3 x²+2 x²−5 x²+x−xproduce colección vacía de términos no nulos. Combinar exige buscar exponente y sumar coeficientes. Con long, usa addExact si el contrato exige detectar desbordamiento. El orden de exponentes puede ser ascendente o descendente, pero debe acordarse.

Insertar/borrar un nodo conocido puede ser constante; localizarlo por clave sigue siendo lineal. ArrayDeque evita reimplementar para aplicaciones y opera amortizadamente en extremos; no acepta null. No es necesario elegir estructura enlazada solamente porque el curso la estudie.

</details>

## Entrega y criterios

Incluye código, diagramas antes/después, secuencias, pruebas y costos. Contratos 15%; invariantes 30%; pruebas 30%; aplicación 15%; claridad 10%. El recorrido normal no sustituye casos de vaciado y reutilización.
