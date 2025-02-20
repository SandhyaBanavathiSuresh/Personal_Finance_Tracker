import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  getStatistics(){
    return this.http.get(BASIC_URL + `api/graph`);
  }

  getChartData(){
    return this.http.get(BASIC_URL + `api/graph/chart`);
  }
}
