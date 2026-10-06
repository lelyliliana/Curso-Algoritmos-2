# Verificación de ejemplos y pruebas

[Inicio](../README.md) · [Siguiente recurso](cierre.md)

Desde la raíz ejecuta python scripts/verificar.py en Windows o python3 scripts/verificar.py en Ubuntu/macOS. Necesitas un JDK 21 y Python 3.10 o posterior. Python coordina ejecución y enlaces; los algoritmos están en Java.

## Qué comprueba

1. Ejecuta los 42 ejemplos en carpetas temporales y compara con esperado.txt.
2. Compila las prácticas seleccionadas con sus pruebas Java, sin mezclar clases Main.
3. Compara ordenamientos contra Arrays.sort con entradas vacías, extremos, duplicados y datos reproducibles.
4. Revisa búsquedas, hash, listas, pilas, colas, árboles, listas generalizadas y grafos.
5. Ensaya persistencia, carga inválida y menú del proyecto.
6. Comprueba enlaces Markdown locales.

Los saltos CRLF/LFse normalizan y se fija UTF-8 para fuentes, captura y archivos. Un fallo explica el ejemplo o la prueba y devuelve error. Las carpetas temporales aíslan archivos de salida.

GitHub Actions ejecuta las mismas comprobaciones en Ubuntu, Windows y macOS con JDK 21. Revisa la pestaña Actionspara consultar cada resultado.

## Límites de la comprobación

Las pruebas dan evidencia de los casos y contratos definidos, no ausencia absoluta de defectos. No miden circuitos ni certifican rendimiento real de una máquina. Los modelos, explicaciones y análisis se revisan además de ejecutar. Añade pruebas cuando cambies una regla.
