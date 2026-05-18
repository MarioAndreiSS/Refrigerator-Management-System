package app;

import model.Refrigerator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            
            // 1. Read input
            Refrigerator[] refrigerators = inputRefrigerators(scanner);
            if (refrigerators.length == 0) {
                System.out.println("No refrigerators in inventory. Exiting.");
                return;
            }

            // 2. Display all elements
            displayWarehouse(refrigerators);

            // 3. Search by Brand and Maximum Price
            searchByBrandAndPrice(scanner, refrigerators);

            // 4. Display by Energy Class
            displayByEnergyClass(scanner, refrigerators);

            // 5. Delete by Brand and Energy Class
            refrigerators = deleteRefrigerators(scanner, refrigerators);
            
            // 6. Sort the array by Net Volume
            sortRefrigeratorsByVolume(refrigerators);

            // 7. Generate and Display Quadratic Matrix
            generateAndDisplayMatrix(refrigerators);
            
        } 
    }

    // --- HELPER METHODS FOR CLEANER ARCHITECTURE ---

    private static Refrigerator[] inputRefrigerators(Scanner scanner) {
        System.out.print("Enter the number of refrigerators: ");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        if (n <= 0) return new Refrigerator[0];

        Refrigerator[] refrigerators = new Refrigerator[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter data for refrigerator " + (i + 1) + ":");
            System.out.print("Brand: ");
            String brand = scanner.nextLine();
            System.out.print("Cooling system (e.g., No Frost/Static): ");
            String coolingSystem = scanner.nextLine();
            System.out.print("Energy class (e.g., A/E/F): ");
            String energyClass = scanner.nextLine();
            System.out.print("Number of doors: ");
            int doorsCount = scanner.nextInt();
            System.out.print("Net volume (liters): ");
            int netVolume = scanner.nextInt();
            System.out.print("Price (USD): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); 

            refrigerators[i] = new Refrigerator(brand, coolingSystem, energyClass, doorsCount, netVolume, price);
        }
        return refrigerators;
    }

    private static void displayWarehouse(Refrigerator[] refrigerators) {
        System.out.println("\n--- 2. All Refrigerators in the Warehouse ---");
        for (Refrigerator r : refrigerators) {
            System.out.println(r);
        }
    }

    private static void searchByBrandAndPrice(Scanner scanner, Refrigerator[] refrigerators) {
        System.out.println("\n--- 3. Search Refrigerator ---");
        System.out.print("Enter the brand to search for: ");
        String brandSearch = scanner.nextLine();
        System.out.print("Enter the maximum price threshold: ");
        double priceSearch = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Search results:");
        boolean found = false;
        for (Refrigerator r : refrigerators) {
            if (r.getBrand().equalsIgnoreCase(brandSearch) && r.getPrice() <= priceSearch) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) System.out.println("No products found matching the specified criteria.");
    }

    private static void displayByEnergyClass(Scanner scanner, Refrigerator[] refrigerators) {
        System.out.println("\n--- 4. Display by Energy Class ---");
        System.out.print("Enter desired energy class (e.g., E): ");
        String energyClassSearch = scanner.nextLine();
        for (Refrigerator r : refrigerators) {
            if (r.getEnergyClass().equalsIgnoreCase(energyClassSearch)) {
                System.out.println(r);
            }
        }
    }

    private static Refrigerator[] deleteRefrigerators(Scanner scanner, Refrigerator[] refrigerators) {
        System.out.println("\n--- 5. Delete Products ---");
        System.out.print("Enter the brand of items to delete: ");
        String brandToDelete = scanner.nextLine();
        System.out.print("Enter the energy class of items to delete: ");
        String energyClassToDelete = scanner.nextLine();
        
        int remainingCount = 0;
        for (Refrigerator r : refrigerators) {
            if (!(r.getBrand().equalsIgnoreCase(brandToDelete) && r.getEnergyClass().equalsIgnoreCase(energyClassToDelete))) {
                remainingCount++;
            }
        }

        Refrigerator[] remainingRefrigerators = new Refrigerator[remainingCount];
        int idx = 0;
        for (Refrigerator r : refrigerators) {
            if (!(r.getBrand().equalsIgnoreCase(brandToDelete) && r.getEnergyClass().equalsIgnoreCase(energyClassToDelete))) {
                remainingRefrigerators[idx++] = r;
            }
        }
        
        System.out.println("Deletion complete! Updated stock inventory:");
        for (Refrigerator r : remainingRefrigerators) {
            System.out.println(r);
        }
        return remainingRefrigerators;
    }

    private static void sortRefrigeratorsByVolume(Refrigerator[] refrigerators) {
        System.out.println("\n--- 6. Sorted by Net Volume ---");
        if (refrigerators.length == 0) {
            System.out.println("No data to sort.");
            return;
        }
        
        // Bubble / Selection sort logic retained
        for (int i = 0; i < refrigerators.length - 1; i++) {
            for (int j = i + 1; j < refrigerators.length; j++) {
                if (refrigerators[i].getNetVolume() > refrigerators[j].getNetVolume()) {
                    Refrigerator temp = refrigerators[i];
                    refrigerators[i] = refrigerators[j];
                    refrigerators[j] = temp;
                }
            }
        }
        for (Refrigerator r : refrigerators) {
            System.out.println(r);
        }
    }

    private static void generateAndDisplayMatrix(Refrigerator[] refrigerators) {
        System.out.println("\n--- 7. Generated Matrix ---");
        int mDim = refrigerators.length;
        if (mDim == 0) {
            System.out.println("Matrix cannot be generated because inventory is empty.");
            return;
        }

        int[][] matrix = new int[mDim][mDim];
        int defaultValue = 5;

        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                matrix[i][j] = (i == j) ? refrigerators[i].getNetVolume() : defaultValue;
            }
        }

        // Display Matrix
        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
