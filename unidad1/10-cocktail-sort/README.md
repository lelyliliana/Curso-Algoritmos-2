# Cocktail sort: burbuja bidireccional

[Recurso anterior](../../unidad1/09-quick-sort/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/11-analisis-de-eficiencia/README.md)

## Objetivo

Alternar pasadas y mantener límites coherentes.

## Conceptos y razonamiento

Cocktail sort realiza una pasada hacia la derecha y otra hacia la izquierda. La primera fija un máximo al final; la segunda fija un mínimo al inicio. Se reduce el intervalo pendiente después de cada dirección.

Las comparaciones siguen siendo entre vecinos. Las banderas permiten detener si una pasada no produce cambios. Antes de recorrer hacia atrás se reduce fin porque el máximo ya quedó ubicado. El índice inverso compara a [i-1] con a [i] y debe permanecer por encima de inicio.

La versión puede corregir antes ciertos elementos pequeños situados al final, pero no cambia el peor orden cuadrático. Al usar > conserva la estabilidad por intercambios adyacentes de claves estrictamente distintas. Es una variante útil para analizar límites, no una recomendación general para grandes conjuntos.

## Caso resuelto y prueba de escritorio

[5, 2, 4, 2, -1] hace una pasada derecha:[2, 4, 2, -1, 5]. La vuelta izquierda lleva-1 al inicio y deja [−1, 2, 4, 2, 5]. Inicio y fin delimitan únicamente las posiciones pendientes.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        int inicio = 0, fin = a.length - 1;
        boolean cambio = true;
        while (cambio && inicio < fin) {
            cambio = false;
            for (int i = inicio; i < fin; i++) if (a[i] > a[i+1]) {
                int t = a[i]; a[i] = a[i+1]; a[i+1] = t; cambio = true;
            }
            if (!cambio) break;
            fin--; cambio = false;
            for (int i = fin; i > inicio; i--) if (a[i-1] > a[i]) {
                int t = a[i]; a[i] = a[i-1]; a[i-1] = t; cambio = true;
            }
            inicio++;
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

Mejor Θ(n), peor Θ(n²), auxiliar Θ(1), estable con >.

## Errores frecuentes

- Usar i>=inicio y acceder a i-1 fuera del intervalo.
- Olvidar reducir fin antes de la vuelta inversa.

## Ejercicio

Instrumenta pasadas y compara [2, 3, 4, 5, 1] con burbuja. Mantén la misma definición de comparación.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

El mínimo 1 se mueve hacia el inicio en la pasada inversa. Cuenta comparaciones cada vez que se evalúa un par y no solo intercambios. No generalices ventaja a todos los arreglos.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/09-quick-sort/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/11-analisis-de-eficiencia/README.md)
