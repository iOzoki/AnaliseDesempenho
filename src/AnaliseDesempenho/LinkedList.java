public class LinkedList {
    private ValorArmazenado primeiro;
    private ValorArmazenado ultimo;
    private int tamanho;

    public LinkedList(){
        this.tamanho = 0;
    }

    public int getTamanho(){
        return tamanho;
    }

    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }

    public ValorArmazenado getPrimeiro() {
        return primeiro;
    }

//    public ValorArmazenado getUltimo() {
//        return ultimo;
//    }

    public void setPrimeiro(ValorArmazenado primeiro) {
        this.primeiro = primeiro;
    }

    public void setUltimo(ValorArmazenado ultimo) {
        this.ultimo = ultimo;
    }

    public void Adicionar(int novoValor) {
        ValorArmazenado novoValorArmazenado = new ValorArmazenado(novoValor);
        if (primeiro == null && ultimo == null){
            primeiro = novoValorArmazenado;
            ultimo = novoValorArmazenado;
            this.tamanho++;
        }
        else{
            this.ultimo.setProximo(novoValorArmazenado);
            this.ultimo = novoValorArmazenado;
            this.tamanho++;
        }
    }

    public boolean Remover(int valorParaRemover) {
        ValorArmazenado atual = this.primeiro;
        ValorArmazenado anterior = null;

        for (int i = 0; i < this.getTamanho(); i++) {
            if (atual.getValor() == valorParaRemover) {
                if (atual == primeiro && atual == ultimo) {
                    this.primeiro = null;
                    this.ultimo = null;
                } else if (atual == primeiro) {
                    this.primeiro = atual.getProximo();
                    atual.setProximo(null);
                } else if (atual == ultimo) {
                    this.ultimo = anterior;
                    anterior.setProximo(null);
                } else {
                    anterior.setProximo(atual.getProximo());
                    atual = null;
                }

                this.tamanho--;
                return true;
            }

            anterior = atual;
            atual = atual.getProximo();
        }

        return false;
    }


    public ValorArmazenado getPosicao(int posicao){
        ValorArmazenado atual = this.primeiro;
        for(int i = 0; i < posicao; i++){
            if(atual.getProximo() != null){
                atual = atual.getProximo();
            }
        }
        return atual;
    }
}
