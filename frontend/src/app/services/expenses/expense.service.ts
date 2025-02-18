import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  constructor(private http: HttpClient){}

  postExpense(expenseDTO:any):Observable<any>{
    return this.http.post(BASIC_URL+ "api/expense" ,expenseDTO);
  }

  getAllExpense():Observable<any>{
    return this.http.get(BASIC_URL+ "api/expense/getAll");
  }

  getExpenseById(id:number):Observable<any>{
    return this.http.get(BASIC_URL+ `api/expense/getById/${id}`);
  }

  updateExpense(id:number, expenseDTO:any):Observable<any>{
    return this.http.put(BASIC_URL+ `api/expense/update/${id}`, expenseDTO);
  }

  deleteExpense(id:number):Observable<any>{
    return this.http.delete(BASIC_URL+ `api/expense/delete/${id}`);
  }
}
