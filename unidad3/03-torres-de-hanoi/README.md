# Torres de Hanói y crecimiento exponencial

[Recurso anterior](../../unidad3/02-fibonacci-y-memoizacion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad3/04-arboles-generales/README.md)

## Objetivo

Descomponer un traslado respetando reglas de discos.

## Conceptos y razonamiento

Para mover n discos de A a C, se mueven n-1 de A a B, se mueve el mayor a C y se mueven n-1 de B a C. Solo puede moverse el disco superior y nunca colocarse uno mayor sobre uno menor. El auxiliar cambia de papel en las llamadas.

El caso base n=0 no agrega movimientos. La recurrencia de cantidad es T(n)=2 T(n-1)+1 y produce 2ⁿ−1. Aumentar n en 1 casi duplica trabajo. Una solución corta en código puede describir una ejecución enorme.

El método público limita 0..10 para permitir práctica pequeña. El helper requiere n válido y etiquetas distintas, garantizado por resolver; no es una API que acepte cualquier parámetro externo. Se guarda la lista de movimientos para poder simularlos y verificar su legalidad, no solo su cantidad.

## Caso resuelto y prueba de escritorio

Para 2: A→B, A→C, B→C. El disco pequeño pasa primero al auxiliar, el grande al destino y el pequeño encima. Para 3 hay 7 movimientos. Contar 7 no demuestra legalidad por sí solo.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    static void hanoi(int n, String origen, String auxiliar, String destino, java.util.List<String> pasos) {
        if (n == 0) return;
        hanoi(n-1, origen, destino, auxiliar, pasos);
        pasos.add(origen + " -> " + destino);
        hanoi(n-1, auxiliar, origen, destino, pasos);
    }
    static java.util.List<String> resolver(int n) {
        if (n < 0 || n > 10) throw new IllegalArgumentException("Discos de 0 a 10");
        java.util.List<String> pasos = new java.util.ArrayList<>();
        hanoi(n,"A","B","C",pasos); return pasos;
    }
    public static void main(String[] args) {
        java.util.List<String> pasos = resolver(2);
        for (String p : pasos) System.out.println(p);
        System.out.println("Movimientos: " + pasos.size());
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
A -> B
A -> C
B -> C
Movimientos: 3
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Θ(2ⁿ) movimientos, Θ(n) pila y Θ(2ⁿ) salida guardada. Si se imprime en lugar de guardar, se conserva costo de generar la salida.

## Errores frecuentes

- Intercambiar mal los roles de auxiliar/destino.
- Confundir pocas líneas con costo pequeño.

## Ejercicio

Simula resolver 3 con pilas de discos y verifica cada movimiento y estado final.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Inicializa A con 3 abajo, 2, 1 arriba. Para cada paso extrae cima de origen y comprueba destino vacío o cima mayor. Al final A/B vacíos, C contiene 3, 2, 1. Verifica cantidad 7 además de reglas.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad3/02-fibonacci-y-memoizacion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad3/04-arboles-generales/README.md)
