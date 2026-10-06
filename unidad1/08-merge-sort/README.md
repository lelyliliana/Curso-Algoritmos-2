# Merge sort: dividir y mezclar

[Recurso anterior](../../unidad1/07-seleccion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/09-quick-sort/README.md)

## Objetivo

Combinar dos intervalos ordenados preservando todos sus elementos.

## Conceptos y razonamiento

Merge sort divide un intervalo, ordena ambas mitades y las mezcla. El nombre correcto es merge sort. El código usa intervalos semiabiertos [inicio, fin), con fin excluido; tamaño 0 o 1 constituye el caso base.

Durante mezcla, i y j apuntan a próximos elementos de cada mitad, k a la salida auxiliar. Se toma el menor y se avanza solo en su mitad. Cuando una mitad termina, se copian los restantes. Después se traslada el intervalo auxiliar al arreglo original.

El arreglo auxiliar se reserva una vez y se comparte entre llamadas que operan intervalos distintos. La comparación <= elige primero la mitad izquierda cuando las claves son iguales, conservando estabilidad. La estructura recursiva aparece aquí como aplicación de dividir el problema; la unidad 3 estudia llamadas y casos base en detalle.

## Caso resuelto y prueba de escritorio

[5, 2, 4, 2, -1] se divide en [5, 2] y [4, 2, -1]. Ordenadas:[2, 5] y [-1, 2, 4]. Mezcla selecciona-1, 2, 2, 4, 5. La igualdad entre los dos 2 se resuelve tomando primero el izquierdo.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) { dividir(a, new int[a.length], 0, a.length); }
    static void dividir(int[] a, int[] aux, int inicio, int fin) {
        if (fin - inicio <= 1) return;
        int medio = inicio + (fin - inicio) / 2;
        dividir(a, aux, inicio, medio); dividir(a, aux, medio, fin);
        int i = inicio, j = medio, k = inicio;
        while (i < medio && j < fin) aux[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i < medio) aux[k++] = a[i++];
        while (j < fin) aux[k++] = a[j++];
        System.arraycopy(aux, inicio, a, inicio, fin - inicio);
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

Θ(n log n) para n≥2 en esta versión, Θ(n) auxiliar más Θ(log n) pila; estable con <=.

## Errores frecuentes

- Omitir restantes de una mitad.
- Mezclar límites cerrados y semiabiertos.

## Ejercicio

Ordena registros por clave y conserva ids. Comprueba estabilidad con dos claves iguales en mitades diferentes.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Adapta aux al tipo de registro y compara clave. Usa <= para elegir la mitad izquierda. Con [2 A, 1 B, 2 C], la salida es [1 B, 2 A, 2 C]. Cambiar a< puede alterar el orden relativo.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/07-seleccion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/09-quick-sort/README.md)
