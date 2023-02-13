public interface IComputeEmpWage {
    public void addCompanies(String company,int empRatePerHour, int numOfWorkingDays,int maxHoursPerMonth);
    public void computeWage();
    public int getTotalWage(String company);
}
