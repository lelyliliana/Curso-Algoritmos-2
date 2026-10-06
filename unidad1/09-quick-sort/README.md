# Quick sort: partición y límites

[Recurso anterior](../../unidad1/08-merge-sort/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/10-cocktail-sort/README.md)

## Objetivo

Separar alrededor de un pivote y reconocer el peor caso.

## Conceptos y razonamiento

Quick sort coloca un pivote en una posición definitiva mediante partición y ordena los lados. Esta versión didáctica toma el último elemento como pivote. frontera delimita valores menores; los siguientes hasta el índice de inspección son mayores o iguales.

Al terminar, se intercambia el pivote con frontera. La recursión excluye esa posición usando frontera-1 y frontera+1. Excluirlo y tener caso base izq>=der garantiza reducción. Los duplicados quedan en el lado de mayores o iguales; muchos iguales pueden provocar particiones desequilibradas.

No se afirma que esta elección tenga promedio O(n log n) para cualquier entrada. Bajo una distribución apropiada de permutaciones con claves distintas el promedio es Θ(n log n), pero ordenado, inverso o todos iguales pueden producir Θ(n²) con este pivote. La pila puede crecer Θ(n) y fallar en entradas grandes. Una variante con pivote aleatorio y partición de tres vías atiende otros objetivos, pero necesita sus propias pruebas.

## Caso resuelto y prueba de escritorio

Primera partición pivote-1: ningún elemento es menor; se mueve al índice 0. Se ordena el resto. Para [1, 2, 3, 4], el último pivote queda siempre al final y apenas reduce el tamaño en 1.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) { dividir(a, 0, a.length - 1); }
    static void dividir(int[] a, int izq, int der) {
        if (izq >= der) return;
        int pivote = a[der], frontera = izq;
        for (int i = izq; i < der; i++) if (a[i] < pivote) {
            intercambiar(a, i, frontera++);
        }
        intercambiar(a, frontera, der);
        dividir(a, izq, frontera - 1); dividir(a, frontera + 1, der);
    }
    static void intercambiar(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
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

Promedio Θ(n log n) bajo supuestos de entrada; peor Θ(n²). Pila O(n) peor; partición local Θ(1). No estable.

## Errores frecuentes

- Reincluir el pivote en la recursión.
- Presentar el mejor comportamiento como garantía universal.

## Ejercicio

Traza un arreglo ordenado de 5 y uno de cinco iguales. Cuenta tamaños de los subproblemas.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Se generan tamaños 4, 3, 2, 1. La cantidad de comparaciones de partición es 4+3+2+1=10. Con iguales la prueba<también deja frontera al inicio. No se obtiene división equilibrada.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/08-merge-sort/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/10-cocktail-sort/README.md)
