import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from '../contracts/Employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  baseURL = "http://localhost:8080/app/v1/employees";
  constructor(private http:HttpClient) { }


  getAllEmployees():Observable<Employee[]>{
    return this.http.get<Employee[]>(this.baseURL);
  }
}
