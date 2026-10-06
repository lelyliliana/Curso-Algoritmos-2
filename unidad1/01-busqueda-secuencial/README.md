# Búsqueda secuencial y contrato

[Recurso anterior](../../docs/como-estudiar.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/02-busqueda-binaria/README.md)

## Objetivo

Encontrar la primera coincidencia sin exigir orden previo.

## Conceptos y razonamiento

Buscar consiste en localizar una clave bajo una regla de igualdad. Antes de elegir algoritmo se aclara qué hacer con duplicados y ausencia. Este contrato recibe un arreglo de enteros y retorna el índice de la primera coincidencia o -1. Un índice es posición, no el valor buscado; el cero puede ser un índice válido.

La búsqueda secuencial inspecciona desde el inicio hasta encontrar la clave o agotar el arreglo. No exige datos ordenados. Antes de evaluar la posición i, ninguna posición anterior coincide: esta propiedad justifica que la primera coincidencia sea la que retorna. Un arreglo vacío no ejecuta el ciclo y produce -1.

El código usa igualdad numérica de int. Para objetos deben definirse equals o un comparador; comparar referencias no sustituye comparar atributos. Los ejemplos del curso usan índices desde cero y evitan centinelas dentro del arreglo que obliguen a modificar datos ajenos.

## Caso resuelto y prueba de escritorio

Datos [8, 3, 8, 1]. Para 8 se compara la posición 0 y retorna 0. Para 1 se revisan cuatro posiciones y retorna 3. Para 7 se revisan cuatro y retorna-1. La búsqueda no modifica el arreglo.

| Clave | Índice | Motivo |
|---|---|---|
| 8 | 0 | Primera coincidencia |
| 1 | 3 | Última posición |
| 7 | -1 | Ausencia |

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static int buscar(int[] datos, int clave) {
        for (int i = 0; i < datos.length; i++) if (datos[i] == clave) return i;
        return -1;
    }
    public static void main(String[] args) {
        int[] datos = {8, 3, 8, 1};
        for (int clave : new int[]{8, 1, 7}) System.out.println(clave + " -> " + buscar(datos, clave));
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
8 -> 0
1 -> 3
7 -> -1
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Mejor caso Θ(1), peor Θ(n), memoria auxiliar Θ(1). El arreglo vacío requiere trabajo constante. No se atribuye un promedio sin declarar cómo se distribuyen las consultas.

## Errores frecuentes

- Confundir -1 con una posición válida.
- Devolver el valor en vez del índice.

## Ejercicio

Devuelve todas las posiciones de una clave. Prueba vacío, ausente y tres duplicados.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Recorre todo el arreglo y agrega cada índice coincidente a una lista nueva. Con [8, 3, 8, 8] y 8 retorna [0, 2, 3]. La ausencia produce lista vacía. Cambiar el contrato elimina la salida temprana de la primera coincidencia.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../docs/como-estudiar.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/02-busqueda-binaria/README.md)
