import java.util.HashMap;
import java.util.Map;

public class MaquinaDeTuring {
    private Map<String, Estado> tabelaDeTransicao;

    public MaquinaDeTuring() {
        tabelaDeTransicao = new HashMap<>();

        Estado q0 = new Estado(false);
        q0.adicionarTransicao('#', "q0", '#', 'D');
        q0.adicionarTransicao('a', "q1", 'A', 'D');
        q0.adicionarTransicao('B', "q7", 'B', 'D');
        tabelaDeTransicao.put("q0", q0);

        Estado q1 = new Estado(false);
        q1.adicionarTransicao('a', "q1", 'a', 'D');
        q1.adicionarTransicao('B', "q1", 'B', 'D');
        q1.adicionarTransicao('b', "q2", 'B', 'D');
        tabelaDeTransicao.put("q1", q1);

        Estado q2 = new Estado(false);
        q2.adicionarTransicao('b', "q3", 'B', 'D');
        tabelaDeTransicao.put("q2", q2);

        Estado q3 = new Estado(false);
        q3.adicionarTransicao('C', "q3", 'C', 'D');
        q3.adicionarTransicao('c', "q4", 'C', 'D');
        q3.adicionarTransicao('b', "q3", 'b', 'D');
        tabelaDeTransicao.put("q3", q3);

        Estado q4 = new Estado(false);
        q4.adicionarTransicao('c', "q5", 'C', 'D');
        tabelaDeTransicao.put("q4", q4);

        Estado q5 = new Estado(false);
        q5.adicionarTransicao('c', "q5", 'C', 'E');
        tabelaDeTransicao.put("q5", q5);

        Estado q6 = new Estado(false);
        q6.adicionarTransicao('C', "q6", 'C', 'E');
        q6.adicionarTransicao('B', "q6", 'B', 'E');
        q6.adicionarTransicao('b', "q6", 'b', 'E');
        q6.adicionarTransicao('a', "q6", 'a', 'E');
        q6.adicionarTransicao('A', "q0", 'A', 'D');
        tabelaDeTransicao.put("q6", q6);

        Estado q7 = new Estado(false);
        q7.adicionarTransicao('C', "q7", 'C', 'D');
        q7.adicionarTransicao('B', "q7", 'B', 'D');
        q7.adicionarTransicao(' ', "q8", ' ', 'E');
        tabelaDeTransicao.put("q7", q7);

        Estado q8 = new Estado(true);
        tabelaDeTransicao.put("q8", q8);
    }

    public int simular(String fita) {
        char[] fitaArray = fita.toCharArray();
        int posicao = 0;
        String estadoAtual = "q0";
        int numOperacoes = 0;

        while (!tabelaDeTransicao.get(estadoAtual).ehEstadoFinal()) {
            char simboloAtual = fitaArray[posicao];
            Transicao transicao = tabelaDeTransicao.get(estadoAtual).transicoes.get(simboloAtual);

            if (transicao == null) {
                break;
            }

            fitaArray[posicao] = transicao.getCaractereEscrito();
            estadoAtual = transicao.getNovoEstado();
            posicao += transicao.getDirecao() == 'D' ? 1 : -1;
            numOperacoes++;

            if (posicao < 0 || posicao >= fitaArray.length) {
                break;
            }
        }

        return numOperacoes;
    }
}

class Estado {
    private boolean ehEstadoFinal;
    Map<Character, Transicao> transicoes;

    public Estado(boolean ehEstadoFinal) {
        this.ehEstadoFinal = ehEstadoFinal;
        transicoes = new HashMap<>();
    }

    public void adicionarTransicao(char caractere, String novoEstado, char caractereEscrito, char direcao) {
        Transicao transicao = new Transicao(novoEstado, caractereEscrito, direcao);
        transicoes.put(caractere, transicao);
    }

    public boolean ehEstadoFinal() {
        return ehEstadoFinal;
    }
}

class Transicao {
    private String novoEstado;
    private char caractereEscrito;
    private char direcao;

    public Transicao(String novoEstado, char caractereEscrito, char direcao) {
        this.novoEstado = novoEstado;
        this.caractereEscrito = caractereEscrito;
        this.direcao = direcao;
    }

    public String getNovoEstado() {
        return novoEstado;
    }

    public char getCaractereEscrito() {
        return caractereEscrito;
    }

    public char getDirecao() {
        return direcao;
    }
}

class Operacoes {
    public static int numOperacoes(int n) {
        MaquinaDeTuring maquina = new MaquinaDeTuring();
        StringBuilder fita = new StringBuilder();
        for (int i = 0; i < n; i++) {
            fita.append('a');
        }
        for (int i = 0; i < n; i++) {
            fita.append('b');
        }
        for (int i = 0; i < n; i++) {
            fita.append('c');
        }
        fita.append(' ');
        return maquina.simular(fita.toString());
    }
}