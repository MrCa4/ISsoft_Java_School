package cargoCompany.securityDepartmentServices;

import train.wagon.entity.User;

import java.util.ArrayList;
import java.util.List;

public class PoliceService {


    private static List<User> arrestedCriminals = new ArrayList<>();
    private static PoliceService policeService=null;

    private PoliceService(){
    }

    public static PoliceService getPolicyService(){
        if (policeService==null){
            policeService=new PoliceService();
        }
        return policeService;
    }
    public void addCriminal(User user){
        arrestedCriminals.add(user);
    }

    public static List<User> getArrestedCriminals() {
        return arrestedCriminals;
    }
}
