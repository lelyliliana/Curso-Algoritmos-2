# Fibonacci y memoización

[Recurso anterior](../../unidad3/01-recursividad-y-caso-base/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad3/03-torres-de-hanoi/README.md)

## Objetivo

Evitar recalcular subproblemas y comprobar límites numéricos.

## Conceptos y razonamiento

Con F0=0 y F1=1, F(n)=F(n-1)+F(n-2). La recursión ingenua vuelve a calcular muchos valores. La memoización conserva resultados por índice y los reutiliza; no cambia la definición matemática.

El arreglo de memoria se llena con-1 como marca no calculada, segura porque los valores válidos son no negativos. Se establecen 0 y 1 antes de recursión. Una nueva llamada pública crea su propio arreglo, por lo que no comparte estado global entre consultas.

El rango 0..92 cabe en long; F93 lo supera. El contrato valida antes de reservar o acceder. La memoización usa espacio para resultados y para llamadas; una versión iterativa con dos anteriores evita ambos crecimientos proporcionales. No se describe la recursión ingenua como lineal por ver dos líneas de código.

## Caso resuelto y prueba de escritorio

Para F6, se calculan índices 2..6 una vez cada uno. Cuando se pide un valor conocido, se retorna del arreglo. F6=8, F10=55. Las bases 0 y 1 evitan llamadas con índices negativos.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.Arrays;
public class Main {
    static long fibonacci(int n) {
        if (n < 0 || n > 92) throw new IllegalArgumentException("Entero de 0 a 92");
        long[] memo = new long[Math.max(2, n+1)]; Arrays.fill(memo, -1);
        memo[0] = 0; memo[1] = 1;
        return calcular(n, memo);
    }
    static long calcular(int n, long[] memo) {
        if (memo[n] < 0) memo[n] = Math.addExact(calcular(n-1,memo), calcular(n-2,memo));
        return memo[n];
    }
    public static void main(String[] args) {
        for (int n : new int[]{0, 1, 6, 10}) System.out.println(n + " -> " + fibonacci(n));
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
1 -> 1
6 -> 8
10 -> 55
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Memoización Θ(n) tiempo y Θ(n) memoria bajo aritmética de long. Recursión ingenua crece exponencialmente; iteración puede usar Θ(1) adicional.

## Errores frecuentes

- Usar 0 como marca sin distinguir F0.
- Ignorar el límite de long.

## Ejercicio

Cuenta cuántas veces se calcula una posición no base y compara con versión ingenua para n=10.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Incrementa cuando memo [n]<0 antes de calcular. Para n 10 se calculan 9 posiciones nuevas(2..10). En la ingenua hay subllamadas repetidas. Si cuentas llamadas totales en lugar de cálculos nuevos, los números cambian; declara la métrica.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad3/01-recursividad-y-caso-base/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad3/03-torres-de-hanoi/README.md)
