# Comparación experimental y estabilidad

[Recurso anterior](../../unidad1/11-analisis-de-eficiencia/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad2/01-referencias-y-memoria/README.md)

## Objetivo

Diseñar una comparación repetible sin convertirla en un benchmark engañoso.

## Conceptos y razonamiento

Una comparación justa utiliza las mismas entradas, copias separadas, el mismo criterio y verifica resultados antes de valorar velocidad. Si el segundo algoritmo recibe el arreglo ya ordenado por el primero, se cambió la condición experimental.

La estabilidad conserva el orden relativo de registros con claves iguales. Ordenar por código y luego buscar por nombre no cumple la precondición de una búsqueda binaria por nombre. Elegir una estructura depende de consultas y actualizaciones, además del costo de ordenar.

El ejemplo compara Arrays.sort como referencia de resultado con una implementación propia de inserción, usando copias. Arrays.sort aquí no se usa como prueba del procedimiento interno, sino como oráculo del orden correcto. Medir nanoTime una sola vez no elimina calentamiento de JVM, ruido ni optimizaciones. En este curso se priorizan conteos deterministas y casos variados.

## Caso resuelto y prueba de escritorio

Original [4, 1, 4, -2]. Cada método recibe una copia. Ambas salidas deben ser [-2, 1, 4, 4] y el original debe seguir [4, 1, 4, -2]. La prueba protege orden y conservación de multiplicidades.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void insercion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int clave = a[i], j = i - 1;
            while (j >= 0 && a[j] > clave) { a[j+1] = a[j]; j--; }
            a[j+1] = clave;
        }
    }
    public static void main(String[] args) {
        int[] original = {4, 1, 4, -2};
        int[] referencia = original.clone(), propio = original.clone();
        Arrays.sort(referencia); insercion(propio);
        System.out.println(Arrays.equals(referencia, propio));
        System.out.println(Arrays.toString(original));
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
true
[4, 1, 4, -2]
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Preparar cada copia cuesta Θ(n). Ese costo debe separarse de ordenar si se reportan tiempos de la operación.

## Errores frecuentes

- Reutilizar el arreglo modificado entre algoritmos.
- Comprobar solo que crece sin comprobar pérdida o duplicación de valores.

## Ejercicio

Construye seis entradas:vacía, un elemento, ordenada, inversa, duplicados y negativos. Compara cada algoritmo con referencia.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Clona para cada algoritmo y usa Arrays.equals contra Arrays.sort de otra copia. Añade casos aleatorios reproducibles. Para estabilidad se necesitan identidades en registros, no solo int iguales.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/11-analisis-de-eficiencia/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad2/01-referencias-y-memoria/README.md)
