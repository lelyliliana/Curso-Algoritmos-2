# Inserción en un prefijo ordenado

[Recurso anterior](../../unidad1/05-burbuja/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/07-seleccion/README.md)

## Objetivo

Mantener orden al insertar cada elemento en su lugar.

## Conceptos y razonamiento

Inserción mantiene el prefijo [0, i) ordenado. Guarda a [i] como clave, desplaza a la derecha valores mayores y coloca la clave en el hueco. Guardar antes de desplazar evita perder el dato que se está insertando.

La condición j>=0 se evalúa antes de a [j]>clave gracias al cortocircuito. Si se invierte el orden puede intentarse acceder a [-1]. No se necesita buscar el lugar con un ciclo y después intercambiar repetidamente: el desplazamiento expresa directamente la inserción.

Con >, los iguales no cruzan su orden relativo. En datos casi ordenados puede realizar pocos desplazamientos. Insertar un dato en un vector de capacidad limitada también exige separar longitud física y cantidad lógica; aquí todos los elementos existentes se ordenan en el mismo arreglo.

## Caso resuelto y prueba de escritorio

Con [5, 2, 4, 2, -1], i=1 guarda 2, desplaza 5 y produce [2, 5, 4, 2, -1]. En i=2 guarda 4 y desplaza 5: [2, 4, 5, 2, -1]. El prefijo crece sin afirmar que el resto ya esté ordenado.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int clave = a[i], j = i - 1;
            while (j >= 0 && a[j] > clave) { a[j+1] = a[j]; j--; }
            a[j+1] = clave;
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

Mejor Θ(n), peor Θ(n²), espacio Θ(1), estable con >. Insertar en medio de un vector puede mover Θ(n) elementos.

## Errores frecuentes

- Sobrescribir la clave al desplazar.
- Leer a [j] antes de comprobar j>=0.

## Ejercicio

Inserta 7 en [2, 4, 8] dentro de un arreglo con capacidad 4 y cantidad lógica 3.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Valida capacidad y busca desde cantidad-1; mueve 8 al índice 3 y coloca 7 en 2. La cantidad pasa a 4. No leas el espacio libre como si fuera dato. Si está lleno, rechaza antes de desplazar.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/05-burbuja/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/07-seleccion/README.md)
