# Ordenamiento por burbuja

[Recurso anterior](../../unidad1/04-colisiones-y-encadenamiento/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/06-insercion/README.md)

## Objetivo

Ordenar mediante intercambios adyacentes y reconocer la parte terminada.

## Conceptos y razonamiento

Burbuja compara vecinos y los intercambia si están fuera de orden. En una pasada de izquierda a derecha, un máximo del intervalo pendiente llega al extremo derecho. Después se reduce fin porque esa posición ya tiene un valor definitivo.

Una bandera permite terminar si no hubo intercambio: todos los pares adyacentes revisados están en orden y el prefijo pendiente ya está ordenado. El algoritmo modifica el arreglo recibido y no retorna una copia. El contrato admite vacío, negativos y duplicados.

Con comparación estricta >, los elementos iguales no se intercambian y la versión es estable si se aplica a registros manteniendo su identidad. Con int no se observa esa identidad, pero la regla sigue importando cuando se ordenan objetos por un campo.

## Caso resuelto y prueba de escritorio

Primera pasada de [5, 2, 4, 2, -1] produce [2, 4, 2, -1, 5]. El 5 queda definitivo; las siguientes pasadas solo revisan el prefijo. La salida conserva dos apariciones de 2: ordenar no elimina duplicados.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        for (int fin = a.length - 1; fin > 0; fin--) {
            boolean cambio = false;
            for (int i = 0; i < fin; i++) if (a[i] > a[i+1]) {
                int t = a[i]; a[i] = a[i+1]; a[i+1] = t; cambio = true;
            }
            if (!cambio) break;
        }
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
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
[-1, 2, 2, 4, 5]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Mejor Θ(n) con bandera, peor Θ(n²), espacio Θ(1). Es estable bajo comparación estricta.

## Errores frecuentes

- Reducir fin antes de completar la pasada.
- Usar >= e intercambiar iguales innecesariamente.

## Ejercicio

Añade un contador de comparaciones. Compara un arreglo ordenado y uno inverso de tamaño 5.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Incrementa antes de comparar cada par. Ordenado realiza 4 comparaciones y termina. Inverso realiza 4+3+2+1=10. Distingue comparaciones de intercambios.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/04-colisiones-y-encadenamiento/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/06-insercion/README.md)
