package cargoCompany.securityDepartmentServices;

import cargoCompany.Company;

public class ConflictResolution {

    private static ConflictResolution conflictResolution;
    private ConflictResolution(){

    }

    public static ConflictResolution getConflictResolutionService(){
        if(conflictResolution==null){
            conflictResolution=new ConflictResolution();
        }
        return conflictResolution;
    }

    public  void resoluteDoubleTicketOnOnePlaceSituation(){
        Company.logger.info("Both passengers has same tickets. It is our mistake. We do something to repair it. " +
                "For example change ticket and put this passenger on another seat.");
    }
}
