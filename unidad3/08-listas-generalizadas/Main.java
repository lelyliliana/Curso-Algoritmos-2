public class Main {
    sealed interface Elemento permits Atomo, Grupo {}
    record Atomo(String texto) implements Elemento {}
    record Grupo(java.util.List<Elemento> elementos) implements Elemento {
        Grupo { elementos = java.util.List.copyOf(elementos); }
    }
    static class Lector {
        final String texto; int pos;
        Lector(String texto) { this.texto = texto; }
        void blancos() { while (pos < texto.length() && Character.isWhitespace(texto.charAt(pos))) pos++; }
        Elemento elemento() {
            blancos();
            if (pos >= texto.length()) throw new IllegalArgumentException("Elemento esperado");
            if (texto.charAt(pos) == '(') {
                pos++; blancos(); java.util.List<Elemento> r = new java.util.ArrayList<>();
                if (pos < texto.length() && texto.charAt(pos) == ')') { pos++; return new Grupo(r); }
                while (true) {
                    r.add(elemento()); blancos();
                    if (pos >= texto.length()) throw new IllegalArgumentException("Falta cierre");
                    char c = texto.charAt(pos++);
                    if (c == ')') return new Grupo(r);
                    if (c != ',') throw new IllegalArgumentException("Coma esperada");
                }
            }
            int inicio = pos;
            while (pos < texto.length() && Character.isLetterOrDigit(texto.charAt(pos))) pos++;
            if (inicio == pos) throw new IllegalArgumentException("Átomo inválido");
            return new Atomo(texto.substring(inicio,pos));
        }
    }
    static Grupo leer(String texto) {
        Lector lector = new Lector(texto); Elemento e = lector.elemento(); lector.blancos();
        if (!(e instanceof Grupo g) || lector.pos != texto.length()) throw new IllegalArgumentException("Lista completa requerida");
        return g;
    }
    static int atomos(Elemento e) {
        if (e instanceof Atomo) return 1;
        int total = 0; for (Elemento hijo : ((Grupo)e).elementos()) total += atomos(hijo); return total;
    }
    static String representar(Elemento e) {
        if (e instanceof Atomo a) return a.texto();
        java.util.StringJoiner j = new java.util.StringJoiner(",","(",")");
        for (Elemento hijo : ((Grupo)e).elementos()) j.add(representar(hijo));
        return j.toString();
    }
    public static void main(String[] args) {
        Grupo g = leer("(a, (b,c), ())");
        System.out.println(representar(g)); System.out.println("Átomos: " + atomos(g));
    }
}
