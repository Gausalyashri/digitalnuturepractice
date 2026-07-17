import java.util.HashMap;
import java.util.Map;

/**
 * Financial forecasting tool that predicts a future value based on a
 * present value and an annual growth rate, using recursion.
 *
 * Future Value formula (compound growth):
 *   FV(n) = PV * (1 + r)^n
 *
 * Recursive relation used here:
 *   FV(0) = PV
 *   FV(n) = FV(n - 1) * (1 + r)      for n > 0
 */
public class FinancialForecast {

    /**
     * Naive recursive future value calculation.
     *
     * @param presentValue the starting amount (e.g., current revenue or investment)
     * @param growthRate   the growth rate per period, expressed as a decimal (e.g., 0.05 for 5%)
     * @param years        number of periods (years) to project forward
     * @return the projected future value
     *
     * Time Complexity: O(n) — one recursive call per year, each doing O(1) work.
     * Space Complexity: O(n) — due to the recursive call stack (n stack frames deep).
     */
    public static double calculateFutureValueRecursive(double presentValue, double growthRate, int years) {
        // Base case: no more years to project, so the value stays as-is.
        if (years == 0) {
            return presentValue;
        }
        // Recursive case: grow last year's value by the growth rate, one year at a time.
        return calculateFutureValueRecursive(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    /**
     * Optimized version using memoization to avoid recomputation when the
     * same (years) forecast is requested multiple times for the same inputs.
     * Useful if the tool is asked to forecast many overlapping scenarios
     * (e.g., re-running forecasts for years 1..20 repeatedly).
     *
     * Time Complexity: O(n) per unique call, O(1) for any repeated call
     *                   with values already cached.
     * Space Complexity: O(n) for the recursion stack + O(n) for the cache.
     */
    public static double calculateFutureValueMemoized(double presentValue, double growthRate,
                                                        int years, Map<Integer, Double> cache) {
        if (years == 0) {
            return presentValue;
        }
        if (cache.containsKey(years)) {
            return cache.get(years);
        }
        double result = calculateFutureValueMemoized(presentValue, growthRate, years - 1, cache) * (1 + growthRate);
        cache.put(years, result);
        return result;
    }

    /**
     * Iterative version, included for comparison. Avoids recursion overhead
     * and stack depth limits entirely — the best choice for very large
     * numbers of years or performance-critical code.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int years) {
        double futureValue = presentValue;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double presentValue = 10000;   // e.g., $10,000 starting investment/revenue
        double growthRate = 0.07;      // 7% annual growth
        int years = 10;

        double recursiveResult = calculateFutureValueRecursive(presentValue, growthRate, years);
        System.out.printf("Recursive forecast (%d years): %.2f%n", years, recursiveResult);

        Map<Integer, Double> cache = new HashMap<>();
        double memoizedResult = calculateFutureValueMemoized(presentValue, growthRate, years, cache);
        System.out.printf("Memoized forecast (%d years): %.2f%n", years, memoizedResult);

        double iterativeResult = calculateFutureValueIterative(presentValue, growthRate, years);
        System.out.printf("Iterative forecast (%d years): %.2f%n", years, iterativeResult);
    }
}
