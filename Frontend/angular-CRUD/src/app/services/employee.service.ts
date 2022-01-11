import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from '../contracts/Employee';



@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  //baseURL = "http://localhost:8080/app/v1/employees";
  baseURL = "http://localhost:8080/app/v1";
  constructor(private http: HttpClient) { }


  // 1. save data
  createEmployee(employee: Employee): Observable<any> {
    return this.http.post(`${this.baseURL}/employees`, employee);
  }

  // 2. fetch all employees
  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseURL}/employees`);
  }

  //3. fetch one employee
  findOneEmployee(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseURL}/employees/${id}`);
  }

  // 4. remove
  removeOneEmployee(id: number) {
    return this.http.delete(`${this.baseURL}/employees/${id}`, {
      responseType: 'text'
    });
  }
  // 5. update

  updateOneEmployee(employee: Employee) {
    return this.http.put(`${this.baseURL}/modify`, {
      responseType: 'text'
    });
  }


}
