# Análisis de eficiencia: modelo y casos

[Recurso anterior](../../unidad1/10-cocktail-sort/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/12-comparacion-y-estabilidad/README.md)

## Objetivo

Relacionar operaciones contadas con crecimiento y espacio.

## Conceptos y razonamiento

El costo depende del modelo: comparaciones, movimientos, memoria o tiempo observado responden preguntas distintas. n es tamaño de entrada. O es una cota superior asintótica, Ω una inferior y Θ una cota ajustada; no significan respectivamente peor, mejor y promedio. Cada caso puede tener su propia cota.

Para selección, el número de comparaciones es n(n-1)/2. Para búsqueda lineal ausente es n. Para binaria se reduce aproximadamente a la mitad cada paso. Estos conteos explican crecimiento, pero no todos los costos de ejecutar objetos grandes o acceder a memoria.

También se analiza espacio auxiliar: el arreglo de entrada no se cuenta como memoria adicional del ordenamiento in situ. Merge usa un auxiliar y quick una pila de llamadas; omitir esa pila produce una descripción incompleta. Una operación de hash esperada constante requiere distribución y carga adecuadas.

## Caso resuelto y prueba de escritorio

Para n=4, selección compara 6 veces; para 8, 28. Duplicar n no duplica un término cuadrático. El programa usa long para el producto y evita el desbordamiento de int en el cálculo de la fórmula.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static long comparacionesSeleccion(int n) {
        if (n < 0) throw new IllegalArgumentException("Tamaño no negativo");
        return (long) n * (n - 1) / 2;
    }
    public static void main(String[] args) {
        for (int n : new int[]{0, 4, 8, 16}) System.out.println(n + " -> " + comparacionesSeleccion(n));
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
0 -> 0
4 -> 6
8 -> 28
16 -> 120
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

La fórmula usa Θ(1) operaciones aritméticas de ancho fijo y espacio Θ(1). No significa que ejecutar selección sea constante: la fórmula calcula su conteo.

## Errores frecuentes

- Usar O como sinónimo de tiempo medido.
- Ignorar pila de llamadas al contar espacio.

## Ejercicio

Compara n, n(n-1)/2 y el número de reducciones sucesivas a la mitad para n=16 y 32.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Lineal 16/32; selección 120/496. Para intervalos binarios, la cantidad exacta depende de consulta y redondeos, pero crece un paso aproximadamente al duplicar n. Define tu conteo antes de comparar.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/10-cocktail-sort/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/12-comparacion-y-estabilidad/README.md)
