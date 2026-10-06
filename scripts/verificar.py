from pathlib import Path
import json
import os
import re
import shutil
import subprocess
import sys
import tempfile

ROOT=Path(__file__).resolve().parents[1]
JAVA=["java","-Dfile.encoding=UTF-8","-Dstdout.encoding=UTF-8","-Dstderr.encoding=UTF-8"]

def run(comando, carpeta, entrada=""):
    p=subprocess.run(comando,cwd=carpeta,input=entrada,capture_output=True,text=True,encoding="utf-8",timeout=60)
    if p.returncode:
        raise RuntimeError(f"Falló {comando}\n{p.stdout}\n{p.stderr}")
    return p.stdout.replace("\r\n","\n")

def main():
    ejemplos=json.loads((ROOT/"scripts/manifest.json").read_text(encoding="utf-8"))
    for ruta in ejemplos:
        carpeta=ROOT/ruta
        with tempfile.TemporaryDirectory() as temporal:
            salida=run(JAVA+[str(carpeta/"Main.java")],temporal)
        esperado=(carpeta/"esperado.txt").read_text(encoding="utf-8")
        if salida.rstrip("\n")!=esperado.rstrip("\n"):
            raise RuntimeError(f"Salida distinta en {ruta}\nEsperado:\n{esperado}\nObtenido:\n{salida}")
    print(f"Ejemplos comprobados: {len(ejemplos)}",flush=True)
    pruebas=json.loads((ROOT/"tests/manifest.json").read_text(encoding="utf-8"))
    for ruta in pruebas:
        with tempfile.TemporaryDirectory() as temporal:
            tmp=Path(temporal)
            shutil.copyfile(ROOT/ruta/"Main.java",tmp/"Main.java")
            shutil.copyfile(ROOT/"tests"/ruta/"Prueba.java",tmp/"Prueba.java")
            run(JAVA+["com.sun.tools.javac.Main","-encoding","UTF-8","-d",".","Main.java","Prueba.java"],tmp)
            salida=run(JAVA+["Prueba"],tmp)
            if salida.strip()!="Pruebas correctas":raise RuntimeError(f"Prueba sin confirmación: {ruta}")
    print(f"Grupos de pruebas Java comprobados: {len(pruebas)}",flush=True)
    with tempfile.TemporaryDirectory() as temporal:
        guion="6\n1\nA\n1\nB\n2\nA\nB\n2\nA\nA\n3\nA\nB\n5\n1\nC\n6\n4\n9\n0\n"
        salida=run(JAVA+[str(ROOT/"unidad3/16-proyecto-integrador/Main.java"),"--menu"],temporal,guion)
        for texto in ("Error:","Lugar agregado","Conexión agregada","No se admiten lazos","[A, B] (1 conexiones)","Red guardada","Red cargada","{A=[B], B=[A]}","Opción inválida","Fin"):
            if texto not in salida:raise RuntimeError(f"Falta resultado del menú: {texto}\n{salida}")
    enlaces=0
    for archivo in ROOT.rglob("*.md"):
        texto=re.sub(r"```.*?```","",archivo.read_text(encoding="utf-8"),flags=re.S)
        for destino in re.findall(r"\[[^\]]*\]\(([^)]+)\)",texto):
            if re.match(r"[A-Za-z][A-Za-z0-9+.-]*:",destino) or destino.startswith("#"):continue
            ruta=destino.split("#",1)[0]
            if ruta and not (archivo.parent/ruta).exists():raise RuntimeError(f"Enlace inválido: {archivo.relative_to(ROOT)} -> {destino}")
            enlaces+=1
    print(f"Enlaces locales comprobados: {enlaces}")
    print("Verificación completa")

if __name__=="__main__":main()
