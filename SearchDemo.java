import java.util.Arrays;
import java.util.Comparator;

/**
 * Demonstrates linear search (on an unsorted array) and binary search
 * (on a sorted array) for finding a Product by productName.
 */
public class SearchDemo {

    /**
     * Linear Search
     * Scans every element one by one until a match is found or the array ends.
     * Works on both sorted and unsorted data.
     *
     * Time Complexity:
     *   Best case:    O(1)      -> target is the first element
     *   Average case: O(n)      -> target is somewhere in the middle
     *   Worst case:   O(n)      -> target is the last element or not present
     */
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null; // not found
    }

    /**
     * Binary Search
     * Requires the array to be sorted (here, sorted by productName).
     * Repeatedly divides the search interval in half.
     *
     * Time Complexity:
     *   Best case:    O(1)      -> target is the middle element on first check
     *   Average case: O(log n)
     *   Worst case:   O(log n)  -> target is at an extreme end or not present
     */
    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                low = mid + 1;   // target is in the right half
            } else {
                high = mid - 1;  // target is in the left half
            }
        }
        return null; // not found
    }

    public static void main(String[] args) {
        Product[] products = {
                new Product(101, "Wireless Mouse", "Electronics"),
                new Product(102, "Bluetooth Speaker", "Electronics"),
                new Product(103, "Yoga Mat", "Fitness"),
                new Product(104, "Air Fryer", "Kitchen"),
                new Product(105, "Desk Lamp", "Home")
        };

        // --- Linear search on the unsorted array ---
        String searchTarget1 = "Air Fryer";
        Product resultLinear = linearSearch(products, searchTarget1);
        System.out.println("Linear search result: "
                + (resultLinear != null ? resultLinear : "Not found"));

        // --- Binary search requires a sorted array first ---
        Product[] sortedProducts = products.clone();
        Arrays.sort(sortedProducts, Comparator.comparing(Product::getProductName));

        String searchTarget2 = "Yoga Mat";
        Product resultBinary = binarySearch(sortedProducts, searchTarget2);
        System.out.println("Binary search result: "
                + (resultBinary != null ? resultBinary : "Not found"));
    }
}
