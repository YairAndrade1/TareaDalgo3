package uniandes.algorithms.coinchange;

public class GreedyCoinChangeAlgorithm implements CoinChangeAlgorithm {

    @Override
    public int[] calculateOptimalChange(int totalValue, int[] denominations) {
        // Arreglo que almacenará la cantidad de monedas utilizadas para cada denominación.
        int[] coinCount = new int[denominations.length];
        int remaining = totalValue;

        // Se asume que las denominaciones vienen ordenadas de menor a mayor.
        // Mientras queden unidades por cubrir:
        while (remaining > 0) {
            // Se busca la moneda con mayor denominación que sea menor o igual al valor restante.
            int selectedIndex = -1;
            for (int i = denominations.length - 1; i >= 0; i--) {
                if (denominations[i] <= remaining) {
                    selectedIndex = i;
                    break;
                }
            }
            // Si no se encontró ninguna moneda (lo cual no debería suceder si se incluye la moneda 1),
            // se sale del ciclo.
            if (selectedIndex == -1) {
                break;
            }
            // Se utiliza la moneda seleccionada, se actualiza el conteo y se reduce el valor restante.
            coinCount[selectedIndex]++;
            remaining -= denominations[selectedIndex];
        }
        return coinCount;
    }
}
