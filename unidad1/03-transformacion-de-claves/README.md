# Transformación de claves y función hash

[Recurso anterior](../../unidad1/02-busqueda-binaria/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/04-colisiones-y-encadenamiento/README.md)

## Objetivo

Distinguir clave, índice de cubeta y coincidencia real.

## Conceptos y razonamiento

Una tabla hash aplica una función a una clave para seleccionar una posición o cubeta. La función debe ser consistente: claves iguales deben seleccionar la misma cubeta. El índice no identifica necesariamente una única clave: el conjunto de claves suele ser mucho mayor que la cantidad de cubetas.

La función modular del ejemplo usa Math.floorMod para aceptar enteros negativos y obtener índices 0..m-1 con m positivo. El operador % en Java puede producir un residuo negativo y no debe usarse directamente como índice para cualquier clave. Un número primo puede ser útil con ciertas distribuciones y funciones, pero no elimina colisiones.

Elegir una función depende de datos y modelo. Si todas las claves comparten un patrón, una transformación pobre concentra entradas. HashMap proporciona una estructura general, pero el ejemplo expone primero la transformación sin simular que cada índice es una búsqueda completa. Después de llegar a una cubeta todavía se compara la clave real.

## Caso resuelto y prueba de escritorio

Con m=5, claves 7 y 12 producen 2. Clave-3 también produce 2 con floorMod. Son claves diferentes y chocan. Esa observación no es un defecto por sí sola: la tabla necesita una política explícita para resolverlo.

| Clave | floorMod(clave, 5) |
|---|---|
| 7 | 2 |
| 12 | 2 |
| -3 | 2 |

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static int indice(int clave, int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva requerida");
        return Math.floorMod(clave, capacidad);
    }
    public static void main(String[] args) {
        for (int clave : new int[]{7, 12, -3}) System.out.println(clave + " -> " + indice(clave, 5));
    }
}
```

## Ejecución paso a paso

1. Prepara el JDK según la [guía de ambiente](../../docs/ambiente-y-herramientas.md).
2. Abre una terminal en la carpeta de esta lección. Cada Main.java es independiente: no compiles todos juntos.
3. Ejecuta este comando en PowerShell (Windows), Terminal (Ubuntu) o Terminal (macOS):

```bash
java Main.java
```

4. Compara con [esperado.txt](esperado.txt). Antes de modificar, explica qué instrucción produce cada resultado.

### Salida esperada

```text
7 -> 2
12 -> 2
-3 -> 2
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Transformar un int con capacidad positiva usa trabajo constante en este modelo. El costo de la tabla completa depende de colisiones, carga y operaciones.

## Errores frecuentes

- Confundir índice hash con identidad de clave.
- Usar % negativo como índice de arreglo.

## Ejercicio

Encuentra dos claves distintas que colisionen con capacidad 7. Explica si elegir 7 por ser primo evita el problema.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

3 y 10 producen 3. Una capacidad prima no hace inyectiva una transformación de infinitas claves a siete posiciones. Se necesitan comparación de claves y resolución de colisiones.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/02-busqueda-binaria/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/04-colisiones-y-encadenamiento/README.md)
