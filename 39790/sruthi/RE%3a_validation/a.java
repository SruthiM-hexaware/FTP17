
// when the no o days are entered
 if(noofdays<=0)
    {
      System.out.println("Invalid number of days");
    }
//once date is entered 

long duration = ed.getTime()-sd.getTime();
if (sd == null || ed == null)
    {
      System.out.println("Please enter a valid date");
    }
    else if(sd.after(ed)|| sd.equals(ed)) {
    System.out.println("INVALID DATE ENTRY")
    }
    else if(noofdays != duration )
    {
        System.ou.println("INVALID NUMBER OF DAYS");
    }

    //check employee id
    // after entering empid
    Employee employee = Employee.listById(empId);
    if (employee == null) {
      System.out.println("Sorry, No such employee");
    } else {
      System.out.println(employee);
    }

    // check noofdays== avl balance

