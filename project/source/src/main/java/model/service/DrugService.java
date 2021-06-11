package model.service;

import model.entity.Drug;
import model.repository.DrugsRepo;
import utils.UuidGeneratorUtils;


import java.util.List;

public class DrugService {
    DrugsRepo drugRepo;

    public DrugService() {
        this.drugRepo = new DrugsRepo();
    }

    public void addDrug(String drugName, String type, float price, int quantity) {
        Drug d=new Drug(UuidGeneratorUtils.generateUUID(),drugName,type,price,quantity);
        drugRepo.insertMed(d);
    }


    public List<Drug> getMeds(){

        return drugRepo.getMeds();
    }

    public void deleteDrug(String name) {
        drugRepo.deleteMedByName(name);
    }

    public void modifyDrug(String name, float price, int quantity, float rating, int discount, int qunatityDiscounted) {
        Drug d=drugRepo.checkNameMeds(name);
        if(d!=null){
            drugRepo.updatePrice(name,price);
            drugRepo.updateCant(d,quantity);
            drugRepo.updateRatingAndDiscount(d,rating,discount,qunatityDiscounted);
        }
    }

    public Drug getMed(String name) {
        return drugRepo.checkNameMeds(name);
    }

    public void updateQuantity(Drug d, int i) {
        drugRepo.updateCant(d,i);
    }

    public void updateRating(String drugName, float currentRating) {
        Drug d=drugRepo.checkNameMeds(drugName);
        float rating=d.getRating();
        int nrReviews=d.getNrReviews();
        float newRating=(rating*nrReviews + currentRating) / (nrReviews+1);
        drugRepo.updateRating(d,newRating,nrReviews+1);
    }
}
