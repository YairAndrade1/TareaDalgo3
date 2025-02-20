package uniandes.algorithms.coinchange;

import java.util.Arrays;

public class RecursiveCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        // Array para registrar, para cada monto, el índice de la moneda elegida en la solución óptima.
        int[] coinUsed = new int[totalValue + 1];
        Arrays.fill(coinUsed, -1);

        // Se calcula el número mínimo de monedas para totalValue mediante la función recursiva.
        // Se asume que siempre existe una solución.
        int min = minCoins(totalValue, denominations, coinUsed);

        // Reconstrucción de la solución:
        // Se arma un arreglo (coinCount) en el que cada posición corresponde a la cantidad de monedas de esa denominación.
        int[] coinCount = new int[denominations.length];
        int amount = totalValue;
        while (amount > 0) {
            int coinIndex = coinUsed[amount];
            // Si por alguna razón coinIndex es -1, significa que no se encontró solución, pero aquí asumimos que siempre hay.
            if (coinIndex == -1) {
                break;
            }
            coinCount[coinIndex]++;
            amount -= denominations[coinIndex];
        }
        return coinCount;
    }

    // Función recursiva que implementa la ecuación de recurrencia:
// f(P) = min { 1 + f(P - d) } para cada denominación d que cumple d ≤ P, con f(0) = 0.
// Retorna Integer.MAX_VALUE si no es posible formar el monto (no aplicable si se asume que siempre hay solución).
    private int minCoins(int amount, int[] denominations, int[] coinUsed) {
        if (amount == 0) {
            return 0;  // Caso base: para 0 se necesitan 0 monedas.
        }
        int min = Integer.MAX_VALUE;
        int bestCoin = -1;

        // Se recorre cada denominación posible
        for (int i = 0; i < denominations.length; i++) {
            if (denominations[i] <= amount) {
                int subResult = minCoins(amount - denominations[i], denominations, coinUsed);
                // Se verifica que se encontró solución para el subproblema y se evalúa si es la mejor opción.
                if (subResult != Integer.MAX_VALUE && subResult + 1 < min) {
                    min = subResult + 1;
                    bestCoin = i;  // Guarda el índice de la moneda que llevó a la solución óptima.
                }
            }
        }
        // Se registra la moneda elegida para el monto "amount"
        coinUsed[amount] = bestCoin;
        return min;
    }
}