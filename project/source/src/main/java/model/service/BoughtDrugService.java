package model.service;

import model.entity.BoughtDrug;
import model.entity.User;
import model.repository.BoughtDrugRepo;
import utils.UuidGeneratorUtils;


public class BoughtDrugService {
    BoughtDrugRepo boughtDrugRepo=new BoughtDrugRepo();

    public void add(String nume, String type, float price, int quantity, User u) {
            BoughtDrug boughtDrug = new BoughtDrug(UuidGeneratorUtils.generateUUID(), nume, type, price, quantity, u);
            boughtDrug.setUser(u);
            boughtDrugRepo.add(boughtDrug);
    }

}
