package uniandes.algorithms.coinchange;

import java.util.Arrays;

public class DynamicProgrammingCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        // dp[i] almacenará el número mínimo de monedas necesarias para formar el monto i.
        int[] dp = new int[totalValue + 1];
        // coinUsed[i] almacena el índice de la moneda que se usó para obtener la solución óptima para el monto i.
        int[] coinUsed = new int[totalValue + 1];

        // Caso base: para monto 0 se requieren 0 monedas.
        dp[0] = 0;
        Arrays.fill(coinUsed, -1);

        // Se itera para cada monto desde 1 hasta totalValue.
        for (int amount = 1; amount <= totalValue; amount++) {
            dp[amount] = Integer.MAX_VALUE; // Inicialmente, se asigna un valor infinito.
            // Se recorre cada denominación disponible.
            for (int i = 0; i < denominations.length; i++) {
                int coinValue = denominations[i];
                if (coinValue <= amount && dp[amount - coinValue] != Integer.MAX_VALUE) {
                    // Se evalúa si usando la moneda de valor coinValue se reduce la cantidad de monedas.
                    if (dp[amount - coinValue] + 1 < dp[amount]) {
                        dp[amount] = dp[amount - coinValue] + 1;
                        coinUsed[amount] = i;
                    }
                }
            }
        }

        // Reconstrucción de la solución:
        // coinCount[i] representa la cantidad de monedas de la denominación denominations[i] utilizadas.
        int[] coinCount = new int[denominations.length];
        int amount = totalValue;
        while (amount > 0) {
            int coinIndex = coinUsed[amount];
            if (coinIndex == -1) {
                // Si coinIndex es -1, significa que no es posible formar el monto.
                break;
            }
            coinCount[coinIndex]++;
            amount -= denominations[coinIndex];
        }

        return coinCount;
    }
}
