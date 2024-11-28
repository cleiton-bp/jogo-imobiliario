class Tabuleiro {
    Casa inicio;

    public void adicionarCasa(Casa novaCasa) {
        if (inicio == null) {
            inicio = novaCasa;
            inicio.proxima = inicio;
        } else {
            Casa atual = inicio;
            while (atual.proxima != inicio) {
                atual = atual.proxima;
            }
            atual.proxima = novaCasa;
            novaCasa.proxima = inicio;
        }
    }

    public void imprimirTabuleiro() {
        if (inicio == null) return;
        Casa atual = inicio;
        do {
            System.out.println(atual.nome + " (" + atual.tipo + ")");
            atual = atual.proxima;
        } while (atual != inicio);
    }
}