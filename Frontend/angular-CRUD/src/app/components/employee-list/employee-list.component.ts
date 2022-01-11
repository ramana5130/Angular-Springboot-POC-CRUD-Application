import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/contracts/Employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[] = [];
  message: string = '';
  constructor(private empService: EmployeeService) { }

  ngOnInit(): void {
    this.getListOfEmployees();
  }

  getListOfEmployees() {
    this.empService.getAllEmployees().subscribe(
      emp => {
        this.employees = emp;
      }, error => {
        console.log(error)
      });
  }

  deleteEmployee(id: number) {
    //console.log(id);
    //alert("delete clicked" + id);
    this.empService.removeOneEmployee(id).subscribe(
      (data) => {
        this.message = data;
        this.getListOfEmployees();
      },
      (error) => {
        console.log(error);
      }
    )
  }

}
