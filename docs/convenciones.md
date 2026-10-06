# Convenciones y contratos

[Inicio](../README.md) · [Siguiente recurso](complejidades.md)

Los índices comienzan en 0. Los algoritmos que usan intervalo cerrado [izq, der] incluyen ambos extremos; merge usa [inicio, fin), con fin excluido. No se mezclan las convenciones.

La flecha←en pseudocódigo expresa asignación. Java usa=para asignar y==para comparar primitivos o identidad. OR lógico es inclusivo; los operadores Java&&y||usan cortocircuito. null representa ausencia de referencia, no un número ni un nodo con dato 0.

Los árboles usan altura medida en aristas:hoja 0, árbol vacío-1 cuando se define una función de altura. Un BST no balanceado puede tener altura n-1. Los grafos son simples, no dirigidos y sin pesos salvo indicación explícita. Una ruta con k vértices tiene k-1 aristas si no está vacía.

## Plantilla de contrato

- Entradas, tipo y dominio.
- Resultado y significado de ausencia.
- Regla de duplicados.
- Si modifica o conserva la entrada.
- Precondiciones e invariantes.
- Casos normal, vacío, límite e inválido.
- Costo de localizar, modificar y devolver resultado.

## Pseudocódigo de búsqueda lineal

```text
PARA i desde0hasta longitud-1:
    SI datos[i] = clave:
        RETORNAR i
RETORNAR -1
```

El significado de PARA es visitar cada índice válido en orden. Vacío no genera ninguna vuelta. Retornar -1 se interpreta como ausencia y no debe utilizarse directamente como índice.
