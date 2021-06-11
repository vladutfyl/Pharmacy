package utils.validators;

import view.IAdminView;

public final class DrugValidatorUtils {
    private DrugValidatorUtils(){}

    public static boolean validateMeds(IAdminView iAdminView, String username, float price, int cantitate) {
        if (username == null || username.equals("")){
            iAdminView.setInfo("Invalid name");
            return false;
        }
        if (price < 0 ) {
            iAdminView.setInfo("Invalid price");
            return false;
        }
        if (cantitate < 1 ){
            iAdminView.setInfo("Invalid quantity");
            return false;
        }
        return true;
    }

}
