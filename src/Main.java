import product_categories.Categories;
import product_categories.Fruits;
import product_categories.Other;
import product_categories.Vegetables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int NO_GENERATED_STORAGE_PACKAGES = 200;
    private static final String location = "D://Desktop//Warehouse.csv";
    private static String[]arrayOfProducts ={"Apples","Potatoes","Onions","Peaches","Oranges","Crackers"};

    private static String[] arrayOfMeasurableUnits = {"kg","bag","bag","box","bag","pack"};
    private static double[] arrayOfMeasurableUnitsKg = {1,10,4,6,2.5,1};

    private static String[] fruits  = {"Apples","Peaches","Oranges"};
    private static String[] vegetables  = {"Potatoes","Onions"};

    private static double[] pricePerUnitArray = {6,15,2.5,30,13,2};

    public static void printSummary(List<Categories> products){
//        List<String> fruitsList = Arrays.asList(fruits);
//        List<String> vegetablesList = Arrays.asList(vegetables);

//        int total = 0;
//        for(Categories categories: products){
//            if(fruitsList.contains(categories.getProductName())){
//                if(categories.getMeasurableUnit())
//                total+= categories.getQuantity();
//            }
//        }
    }


    public static List<Categories> buildWarehouse() {
        List<Categories> products = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();
        long start = dateNow.toEpochDay();
        LocalDate endDate = dateNow.plusMonths(6);
        long end = endDate.toEpochDay();

        List<String> fruitsList = Arrays.asList(fruits);
        List<String> vegetablesList = Arrays.asList(vegetables);


        for (int i = 0; i < NO_GENERATED_STORAGE_PACKAGES; i++) {

            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();

            Random generator = new Random();
            int randomIndex = generator.nextInt(arrayOfProducts.length);

            Categories categoryProduct = null;

            if (fruitsList.contains(arrayOfProducts[randomIndex])) {
                categoryProduct = new Fruits();


            } else if (vegetablesList.contains(arrayOfProducts[randomIndex])) {
                categoryProduct = new Vegetables();

            } else {
                categoryProduct = new Other();
            }

            categoryProduct.setProductName(arrayOfProducts[randomIndex]);
            categoryProduct.setMeasurableUnit(arrayOfMeasurableUnits[randomIndex]);
            categoryProduct.setWeight(arrayOfMeasurableUnitsKg[randomIndex]);

            int high = 0;
            int low = 0;

            if(arrayOfMeasurableUnits[randomIndex].equals("kg")){
                low = 50;
                high = 250;
            }else if(arrayOfMeasurableUnits[randomIndex].equals("bag")){
                low = 15;
                high = 25;
            } else if(arrayOfMeasurableUnits[randomIndex].equals("box")){
                low = 30;
                high = 60;
            }else{
                low = 100;
                high = 500;
            }
            //System.out.println(arrayOfMeasurableUnits[randomIndex]);
            categoryProduct.setPricePerUnit(pricePerUnitArray[randomIndex]);

            if(fruitsList.contains(arrayOfProducts[randomIndex]) || vegetablesList.contains(arrayOfProducts[randomIndex])){
                categoryProduct.setExpirationDate(LocalDate.ofEpochDay(randomEpochDay));
                //categoryProduct.applyDiscount(categoryProduct.getExpirationDate());
            }

            int randQuantity = generator.nextInt(high-low) + low;
            categoryProduct.setQuantity(randQuantity);
            categoryProduct.setOriginalPrice(randQuantity*pricePerUnitArray[randomIndex]);
            categoryProduct.setEntryDate(dateNow);

            products.add(categoryProduct);

        }
        return products;
    }
    public static void writeInCsv(List<Categories> products){
        String[] header = {"Products","Measurable unit","Equivalence in kg","Quantity","Price Per Unit","Expiration date","Original Price"};

        try (PrintWriter writer = new PrintWriter(new File(location))) {

            StringBuilder sb = new StringBuilder();
            for(int i=0;i< header.length;i++){
                sb.append(header[i]);
                sb.append(',');
            }
            sb.append('\n');

            for(int i=0;i<NO_GENERATED_STORAGE_PACKAGES;i++){
                int index = (int)(Math.random() * products.size());
                sb.append(products.get(index).getProductName());
                sb.append(',');
                sb.append(products.get(index).getMeasurableUnit());
                sb.append(',');
                sb.append(products.get(index).getWeight());
                sb.append(',');
                sb.append(products.get(index).getQuantity());
                sb.append(',');
                sb.append(products.get(index).getPricePerUnit());
                sb.append(',');
                sb.append(products.get(index).getExpirationDate());
                sb.append(',');
                sb.append(products.get(index).getOriginalPrice());
                sb.append('\n');
            }

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[]args){
        List<Categories> products = buildWarehouse();
        writeInCsv(products);
        printSummary(products);

    }
}
