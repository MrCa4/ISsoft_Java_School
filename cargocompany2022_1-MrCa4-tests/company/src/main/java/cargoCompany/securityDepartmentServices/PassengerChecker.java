package cargoCompany.securityDepartmentServices;

import train.wagon.PassengerWagon;
import train.wagon.entity.User;

public abstract class PassengerChecker {

        private PassengerChecker next;
        public abstract boolean check(PassengerWagon wagon, User user);

        public PassengerChecker linkNextValidator(PassengerChecker next) {
            this.next = next;
            return next;
        }

        protected boolean checkNext(PassengerWagon wagon, User user) {
            if (next == null) {
                return true;
            }
            return next.check(wagon,user);
        }
}

