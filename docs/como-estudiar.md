# Cómo estudiar Algoritmos 2

[Inicio](../README.md) · [Siguiente recurso](ambiente-y-herramientas.md)

## Antes de ejecutar

Lee contrato, precondiciones e invariantes. Dibuja un arreglo con sus índices o nodos con sus enlaces. Predice qué resultado tendrá la búsqueda o modificación. Conserva el estado anterior para saber si un error dejó cambios parciales.

## Recorrido de una lección

1. Estudia conceptos y caso resuelto.
2. Sigue el código con una prueba de escritorio. Registra índices, referencias o marcas visitadas según el tema.
3. Ejecuta el ejemplo en su carpeta y compara con esperado.txt.
4. Prueba vacío, único, duplicados, ausente y extremos cuando sean pertinentes.
5. Resuelve el ejercicio antes de abrir la solución.
6. Comprueba resultado y conservación de invariantes.
7. Explica costo de localizar, modificar y producir salida por separado.
8. Usa Siguiente recurso para avanzar.

## Organización

Trabaja en una copia o en tu propia carpeta. Cada ejemplo tiene Main.java y es independiente. No mezcles archivos de dos lecciones en la misma compilación. Guarda tu implementación, casos y razonamiento juntos; usa datos ficticios.

## Cómo revisar un algoritmo

Una salida ordenada podría haber perdido duplicados. Una lista recorrida hacia adelante podría tener enlaces anteriores rotos. Un BFS podría encontrar una ruta válida que no sea mínima si se cambió la cola por una pila. Comprueba tanto resultado como propiedades de la representación.

Para fallos, reduce la entrada, localiza el primer estado distinto y cambia una cosa a la vez. Al corregir, conserva un caso de regresión. No cambies el resultado esperado solo para que coincida con una implementación defectuosa.
