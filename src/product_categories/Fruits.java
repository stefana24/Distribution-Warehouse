package product_categories;

import product_categories.Categories;

import java.time.LocalDate;

public class Fruits extends Categories {
    private double nutritionalQuality;

    public double getNutritionalQuality() {
        return nutritionalQuality;
    }

    public void setNutritionalQuality(double nutritionalQuality) {
        this.nutritionalQuality = nutritionalQuality;
    }


    public void applyDiscount(LocalDate ld){
        LocalDate fiveWeeksBefore = ld.minusDays(35);
        LocalDate dateNow = LocalDate.now();
        if(dateNow.isAfter(fiveWeeksBefore)){
            double price = getPricePerUnit() - 0.1*getPricePerUnit();
            setPricePerUnit(price);
        }
        //System.out.println(getPricePerUnit());

    }

}
