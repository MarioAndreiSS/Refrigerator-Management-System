package app;

import model.Refrigerator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Using try-with-resources to automatically close the scanner
        try (Scanner scanner = new Scanner(System.in)) {
            
            // 1. Read an array of Refrigerators from the console
            System.out.print("Enter the number of refrigerators: ");
            int n = scanner.nextInt();
            scanner.nextLine(); // Consume remaining newline

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
                scanner.nextLine(); // Consume newline
                System.out.print("Net volume (liters): ");
                int netVolume = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Price (USD): ");
                double price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline after price

                refrigerators[i] = new Refrigerator(brand, coolingSystem, energyClass, doorsCount, netVolume, price);
            }

            // 2. Display all elements in the array
            System.out.println("\n--- 2. All Refrigerators in the Warehouse ---");
            for (Refrigerator r : refrigerators) {
                System.out.println(r);
            }

            // 3. Search by Brand and Maximum Price
            System.out.println("\n--- 3. Search Refrigerator ---");
            System.out.print("Enter the brand to search for: ");
            String brandSearch = scanner.nextLine();
            System.out.print("Enter the maximum price threshold: ");
            double priceSearch = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.println("Search results:");
            boolean found = false;
            for (Refrigerator r : refrigerators) {
                if (r.getBrand().equalsIgnoreCase(brandSearch) && r.getPrice() <= priceSearch) {
                    System.out.println(r);
                    found = true;
                }
            }
            if (!found) System.out.println("No products found matching the specified criteria.");

            // 4. Display by Energy Class
            System.out.println("\n--- 4. Display by Energy Class ---");
            System.out.print("Enter desired energy class (e.g., E): ");
            String energyClassSearch = scanner.nextLine();
            for (Refrigerator r : refrigerators) {
                if (r.getEnergyClass().equalsIgnoreCase(energyClassSearch)) {
                    System.out.println(r);
                }
            }

            // 5. Delete by Brand and Energy Class
            System.out.println("\n--- 5. Delete Products ---");
            System.out.print("Enter the brand of items to delete: ");
            String brandToDelete = scanner.nextLine();
            System.out.print("Enter the energy class of items to delete: ");
            String energyClassToDelete = scanner.nextLine();

            int remainingElementsCount = 0;
            for (Refrigerator r : refrigerators) {
                if (!(r.getBrand().equalsIgnoreCase(brandToDelete) && r.getEnergyClass().equalsIgnoreCase(energyClassToDelete))) {
                    remainingElementsCount++;
                }
            }

            Refrigerator[] remainingRefrigerators = new Refrigerator[remainingElementsCount];
            int idx = 0;
            for (Refrigerator r : refrigerators) {
                if (!(r.getBrand().equalsIgnoreCase(brandToDelete) && r.getEnergyClass().equalsIgnoreCase(energyClassToDelete))) {
                    remainingRefrigerators[idx++] = r;
                }
            }
            refrigerators = remainingRefrigerators;
            System.out.println("Deletion complete! Updated stock inventory:");
            for (Refrigerator r : refrigerators) System.out.println(r);

            // 6. Sort the array by Net Volume (Selection Sort)
            System.out.println("\n--- 6. Sorted by Net Volume ---");
            for (int i = 0; i < refrigerators.length - 1; i++) {
                for (int j = i + 1; j < refrigerators.length; j++) {
                    if (refrigerators[i].getNetVolume() > refrigerators[j].getNetVolume()) {
                        Refrigerator temp = refrigerators[i];
                        refrigerators[i] = refrigerators[j];
                        refrigerators[j] = temp;
                    }
                }
            }
            for (Refrigerator r : refrigerators) System.out.println(r);

            // 7. Generate Quadratic Matrix
            System.out.println("\n--- 7. Generated Matrix ---");
            int mDim = refrigerators.length;
            int[][] matrix = new int[mDim][mDim];
            int defaultStudentValue = 5;

            for (int i = 0; i < mDim; i++) {
                for (int j = 0; j < mDim; j++) {
                    if (i == j) {
                        matrix[i][j] = refrigerators[i].getNetVolume(); // Main diagonal
                    } else {
                        matrix[i][j] = defaultStudentValue; // Non-diagonal elements
                    }
                }
            }

            // Display Matrix
            for (int i = 0; i < mDim; i++) {
                for (int j = 0; j < mDim; j++) {
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }
            
        } // Scanner automatically closes here at the end of the try block
    }
}
