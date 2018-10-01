package kluver.section05.inheritanceExample;

public class Vehicle {
    private String license;

    public Vehicle(String license) {
        this.license = license;
    }

    public void goForADrive() {
        checkLicense();
        System.out.println("vroom vroom, "+this.toString());
    }

    public String getLicense() {
        return license;
    }

    /**
     * check if a license is valid
     * (OK, technically, this checks if a book's ISBN is valid, but whatever)
     * based on code from: https://www.geeksforgeeks.org/program-check-isbn/
     */
    private void checkLicense() {
        boolean valid = true;
        if (license == null) {
            valid =  false;
        }

        int n = license.length();
        if (n != 10)
            valid = false;

        int sum = 0;
        for (int i = 0; i < 9; i++)
        {
            int digit = license.charAt(i) - '0';
            if (0 > digit || 9 < digit)
                valid = false;
            sum += (digit * (10 - i));
        }

        char last = license.charAt(9);
        if (last != 'X' && (last < '0' ||
                last > '9'))
            valid = false;

        if (last == 'X') {
            sum += 10;
        } else {
            sum += last - '0';
        }

        if (sum % 11 != 0 || !valid) {
            System.out.println("!!!Invalid vehcle license: "+this.toString());
        }
    }

    public String toString() {
        return "Vehicle{license='" + license + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Vehicle) {
            Vehicle vobj = (Vehicle) obj;
            return this.license.equals(vobj.license);
        }
        return false;
    }

    public static void main(String[] args) {
        String validLicense = "0134285433";
        String invalidLicense = "I'm bob the builder, you can't sue me!";
        Vehicle moped = new Vehicle(validLicense);

        moped.goForADrive();
    }
}
