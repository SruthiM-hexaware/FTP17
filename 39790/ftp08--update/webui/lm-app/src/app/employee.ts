export class Employee {
    empId: number;
    empName: String;
    empLeaveBalance:number;
    empEmail: String;
    phoneNumber: number;
    mgrId: number;
    constructor(id: number) {
      this.empId = id;
    }
}
