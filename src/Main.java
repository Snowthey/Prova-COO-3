import org.jfree.data.xy.XYSeries;

public class Main {
    public static void main(String[] args) {
        XYSeries series = new XYSeries("Complexidade");

        for (int n = 1; n <= 5000; n++) { // Reduzido para 500 para evitar longos tempos de execução
            int numOperacoes = Operacoes.numOperacoes(n);
            series.add(n, numOperacoes);
        }

        new Grafico(series);
    }
}