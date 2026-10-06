# Ordenamiento por selección

[Recurso anterior](../../unidad1/06-insercion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/08-merge-sort/README.md)

## Objetivo

Seleccionar mínimos y analizar comparaciones frente a intercambios.

## Conceptos y razonamiento

Selección busca un mínimo en el sufijo pendiente y lo intercambia con su primera posición. Al comenzar la vuelta i, el prefijo anterior contiene sus valores definitivos. El índice menor se inicializa en i, no en cero: el prefijo terminado ya no debe participar.

El algoritmo realiza casi las mismas comparaciones aunque el arreglo esté ordenado. La bandera de burbuja no se traslada automáticamente a selección. Hay como máximo un intercambio por pasada; ahorrar intercambios no vuelve lineal el trabajo de comparar.

Esta versión no es estable. Un intercambio distante puede mover un registro por encima de otro con la misma clave. Ejemplo de identidades:[2 A, 2 B, 1] cambia a [1, 2 B, 2 A] después del primer intercambio. Las dos claves 2 quedaron invertidas.

## Caso resuelto y prueba de escritorio

En [5, 2, 4, 2, -1], la primera búsqueda encuentra índice 4 y coloca-1 al inicio. La siguiente busca mínimo solo desde 1. Un mínimo repetido puede ocupar una posición válida sin eliminar los demás.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < a.length; j++) if (a[j] < a[menor]) menor = j;
            int t = a[i]; a[i] = a[menor]; a[menor] = t;
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

Θ(n²) comparaciones en mejor y peor caso, Θ(1) espacio, versión no estable.

## Errores frecuentes

- Inicializar menor en 0 en cada pasada.
- Confundir pocos intercambios con pocas comparaciones.

## Ejercicio

Cuenta comparaciones para n=5 y demuestra inestabilidad con registros identificados.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Se comparan 4+3+2+1=10 independientemente del orden inicial. Usa registros(clave, id) y [2 A, 2 B, 1 C]; el intercambio de 1 C con 2 A deja 2 B antes de 2 A.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/06-insercion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/08-merge-sort/README.md)
