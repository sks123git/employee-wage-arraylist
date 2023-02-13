
public class EmployeeWageBuilder{
    public static final int IS_FULL_TIME = 1;
    public static final int IS_PART_TIME = 2;

    private int numOfCompanies=0;
    private  CompanyEmpWage[] companyWageArray;

    public EmployeeWageBuilder(){
        companyWageArray = new CompanyEmpWage[5];
    }

    private void  addCompanies(String company,int empRatePerHour, int numOfWorkingDays,int maxHoursPerMonth){
        companyWageArray[numOfCompanies] = new CompanyEmpWage(company,empRatePerHour,numOfWorkingDays,maxHoursPerMonth);
        numOfCompanies++;
    }

    private void computeWage(){
        for (int i = 0; i < numOfCompanies; i++) {
            companyWageArray[i].setTotalEmpWage(this.computeWage(companyWageArray[i]));
            System.out.println(companyWageArray[i]);
        }
    }

    private int computeWage(CompanyEmpWage companyEmpWage) {
        int empHrs = 0;
        int totalEmpHours = 0;
        int totalWorkingDays = 0;
        while (totalEmpHours <= companyEmpWage.maxHoursPerMonth && totalWorkingDays < companyEmpWage.numOfWorkingDays) {
            totalWorkingDays++;
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;
            switch (empCheck) {
                case IS_FULL_TIME:
                    empHrs = 8;
                    break;
                case IS_PART_TIME:
                    empHrs = 4;
                    break;
                default:
                    empHrs = 0;
            }

            totalEmpHours += empHrs;
            System.out.println("Day#: " + totalWorkingDays + " Emp hrs: " + totalEmpHours);
        }
       return totalEmpHours * companyEmpWage.empRatePerHour;
    }



    public static void main(String[] args) {
       EmployeeWageBuilder employeeWageBuilder = new EmployeeWageBuilder();
       employeeWageBuilder.addCompanies("Reliance", 10, 20, 50);
       employeeWageBuilder.computeWage();
    }
}