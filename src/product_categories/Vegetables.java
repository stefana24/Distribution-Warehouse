package product_categories;

import product_categories.Categories;

import java.time.LocalDate;

public class Vegetables extends Categories {
    private double nutritionalQuality;
    private String producer;

    public double getNutritionalQuality() {
        return nutritionalQuality;
    }

    public void setNutritionalQuality(double nutritionalQuality) {
        this.nutritionalQuality = nutritionalQuality;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void applyDiscount(LocalDate ld){
        LocalDate fiveWeeksBefore = ld.minusDays(35);
        LocalDate dateNow = LocalDate.now();
        if(dateNow.isAfter(fiveWeeksBefore)){
            double price = getPricePerUnit() - 0.05*getPricePerUnit();
            setPricePerUnit(price);
        }
        //System.out.println(getPricePerUnit());

    }
}
