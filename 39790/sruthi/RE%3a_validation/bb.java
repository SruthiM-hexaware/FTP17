private void applyLeave() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fromDate = null, toDate = null;
    long dayDiff = 0;
    System.out.println("Enter your Employee Id : ");
    int empId = option.nextInt();
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Please enter a valid EmpId.");
    } else {
      System.out.println("Enter The Required Leave Details : ");
      System.out.println("Enter Start Date (YYYY-MM-DD) : ");
      String startDate = option.next();
      System.out.println("Enter End Date (YYYY-MM-DD) : ");
      String endDate = option.next();
      if (Employee.verifyApplyDates(startDate, endDate)) {
        try {
          fromDate = sdf.parse(startDate);
          toDate = sdf.parse(endDate);
          dayDiff = (toDate.getTime() - fromDate.getTime()) / 86400000;
          //System.out.println("Diff : " + dayDiff);
        } catch (Exception e) {
          System.out.println(e);
        }
        System.out.println("Enter No Of Days : ");
        int days = option.nextInt();
        if (dayDiff != days) {
          System.out.println("No Of Days does not match.");
        } else {
          System.out.println("Reason For Leave (Not Mandatory) : Would You like to give reasons ? (Yes/No)");
          String reasonChoice = option.next();
          option.nextLine();
          String reason = "";
          if (reasonChoice.equalsIgnoreCase("Yes")) {
            System.out.println("Please Enter your reasons for Leave : ");
            reason = option.nextLine();
          }
          Employee.insertLeave(empId, sdf.format(fromDate), sdf.format(toDate), days, reason);
        }
      }
    }
  }


/**
   * Verify the Leave ApplicationDates.
   * @param startDate Leave Application fromDate.
   * @param endDate Leave Application toDate.
   * @return boolean;
   */
  public static boolean verifyApplyDates(final String startDate, final String endDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date fromDate = sdf.parse(startDate);
      Date toDate = sdf.parse(endDate);
      Date curDate = new Date();
      if (!fromDate.after(curDate)) {
        System.out.println("Start Date must be greater than Current Date");
        return false;
      } else if (!toDate.after(fromDate)) {
        System.out.println("End Date must be greater than Start Date");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return true;
  }