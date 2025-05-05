import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {

    //LEIA ANTES DE USAR!!!!
    //PARA QUE O ARQUIVO SEJA LIDO DO DIRETORIO CERTO DO SEU COMPUTADOR 
    //USE O EXPLORADOR DE ARQUIVOS PARA LOCALIZAR O ARQUIVO "arq.txt"
    //DEPOIS CLIQUE COM O BOTÃO DIREITO, VÁ EM PROPRIEDADES E COPIE O >LOCAL<
    //ENTÃO O ADICIONE NO CAMINHO DO CCÓDIGO EM: File arquivoFile = new File(AQUI O LOCAL DO ARQUIVO)
    public static void main(String[] args) throws Exception {
        System.out.println("Executando...");
        LinkedList linkedList = new LinkedList();
        File arquivoFile = new File("C:\\Users\\isaac\\Downloads\\Projetos em Java\\ListaLigada\\ListaLigada\\src\\arq-novo.txt");
        Scanner scanner = null;
        long inicio = System.nanoTime();
        //ler o arquivo com scanner apenas no try para fechar na função finally

        try {
            scanner = new Scanner(arquivoFile);
            List<String> linhas = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (!linha.isEmpty()) {
                    linhas.add(linha);
                }
            }

            int linhaComandosIndex = -1;
            int numeroAcoes = 0;

            for (int i = 0; i < linhas.size(); i++) {
                if (linhas.get(i).matches("\\d+")) {
                    numeroAcoes = Integer.parseInt(linhas.get(i));
                    linhaComandosIndex = i;
                    break;
                }
            }

            if (linhaComandosIndex == -1) {
                return;
            }

            for (int i = 0; i < linhaComandosIndex; i++) {
                String[] valores = linhas.get(i).split("\\s+");
                for (String val : valores) {
                    if (!val.isEmpty()) {
                        linkedList.Adicionar(Integer.parseInt(val));
                    }
                }
            }


            int contador = 0;
            for (int i = linhaComandosIndex + 1; i < linhas.size() && contador < numeroAcoes; i++, contador++) {
                String linha = linhas.get(i).trim();
                if (linha.isEmpty()) {
                    continue;
                }

                String[] partes = linha.split("\\s+");
                if (partes.length == 0) {
                    continue;
                }
                switch (partes[0]) {
                    case "A":
                        int valor = Integer.parseInt(partes[1]);
                        int posicao = Integer.parseInt(partes[2]);

                        if (posicao < 0 || posicao > linkedList.getTamanho()) {
                            continue;
                        }

                        ValorArmazenado novo = new ValorArmazenado(valor);

                        if(posicao == 0) {
                            novo.setProximo(linkedList.getPrimeiro());
                            linkedList.setPrimeiro(novo);
                            if(linkedList.getTamanho() == 0){
                                linkedList.setUltimo(novo);
                            }
                            linkedList.setTamanho(linkedList.getTamanho() + 1);//é bom criar metodo pra incrementar tamanho incrementarTamanho();
                        }

                        else if(posicao >= linkedList.getTamanho()){
                                linkedList.Adicionar(valor);
                        }

                        else {
                                ValorArmazenado anterior = linkedList.getPosicao(posicao - 1);
                                novo.setProximo(anterior.getProximo());
                                anterior.setProximo(novo);
                                linkedList.setTamanho(linkedList.getTamanho() + 1);
                        }

                        break;
                    case "R":
                        int valorRemover = Integer.parseInt(partes[1]);
                        boolean removido = linkedList.Remover(valorRemover);

                        if (!removido) {
                            linkedList.Remover(Integer.parseInt(partes[1]));
                        }
                    case "P":
                        ValorArmazenado atual = linkedList.getPrimeiro();
                        while (atual != null) {
                            System.out.print(atual.getValor() + " ");
                            atual = atual.getProximo();
                        }
                        System.out.println();
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());

        }
        finally {
            if(scanner != null){
                scanner.close();
            }
            long fim = System.nanoTime();
            long duracao = fim - inicio; // em nanossegundos

            System.out.println("Tempo de execução: " + duracao / 1_000_000.0 + " ms");
        }
    }
}
