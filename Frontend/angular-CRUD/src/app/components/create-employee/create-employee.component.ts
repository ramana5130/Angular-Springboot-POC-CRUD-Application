import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/contracts/Employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee(0, '', '', '', '', '');
  message: string = '';
  constructor(private service: EmployeeService) { }

  ngOnInit(): void {
  }

  createEmployee() {
    this.service.createEmployee(this.employee)
      .subscribe(data => {
        this.message = data;
        //this.message=`employee ${this.employee.id} created`;
        //this.message=`employee ${data.} created`;
        this.employee = new Employee(0, '', '', '', '', '');
      }, error => {
        console.log(error);
        this.message = "Unable to save an employee";
      })
  }

}
