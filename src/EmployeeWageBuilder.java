import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//Java code to implement interface,Map,list to display multiple companies total wage
public class EmployeeWageBuilder implements IComputeEmpWage{
    public static final int IS_FULL_TIME = 1;
    public static final int IS_PART_TIME = 2;
    private int numOfCompanies=0;
    private LinkedList<CompanyEmpWage> companyWageList;
    private Map<String,CompanyEmpWage> companyToEmpWageMap;

    public EmployeeWageBuilder(){
        companyWageList = new LinkedList<>();
        companyToEmpWageMap = new HashMap<>();
    }//Storing daily wages
    public void  addCompanies(String company,int empRatePerHour, int numOfWorkingDays,int maxHoursPerMonth){
        CompanyEmpWage companyEmpWage = new CompanyEmpWage(company,empRatePerHour,numOfWorkingDays,maxHoursPerMonth);
        companyWageList.add(companyEmpWage);
        companyToEmpWageMap.put(company,companyEmpWage);
    }
    //computing total employee wage
    public void computeWage(){
        for (int i = 0; i < companyWageList.size(); i++) {
            CompanyEmpWage companyEmpWage = companyWageList.get(i);
            companyEmpWage.setTotalEmpWage(this.computeWage(companyEmpWage));
            System.out.println(companyEmpWage);
        }
    }
    @Override
    public int getTotalWage(String company){
        return  companyToEmpWageMap.get(company).totalEmpWage;
    }

    public int computeWage(CompanyEmpWage companyEmpWage) {
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
        IComputeEmpWage empWageBuilder =new EmployeeWageBuilder();
        empWageBuilder.addCompanies("Reliance", 10, 20, 50);
        empWageBuilder.addCompanies("Jio", 10, 20, 50);
       empWageBuilder.computeWage();
        System.out.println("Total Wage for Reliance "+ empWageBuilder.getTotalWage("Reliance"));
    }
}