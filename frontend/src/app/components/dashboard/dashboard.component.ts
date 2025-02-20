import { Component, ElementRef, ViewChild } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard/dashboard.service';
import Chart from 'chart.js/auto';
import { CategoryScale } from 'chart.js/auto'; 

Chart.register(CategoryScale);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  statsData: any;
  expenseData: any;
  incomeData: any;

  gridStyle={
    width: '25%',
    textAlign: 'center'
  }

  @ViewChild('incomeChart') private incomeChart: ElementRef;
  @ViewChild('expenseChart') private expenseChart: ElementRef;

  constructor(private dashboardService: DashboardService){
    this.getStatistics();
    this.getChartData();
  }

  

  createChart(){
    const income_ctx = this.incomeChart.nativeElement.getContext('2d');
  
    new Chart(income_ctx, {
      type: 'line',
      data: {
        labels: this.incomeData.map(income => income.date),
        datasets: [{
          label: 'Income',
          data: this.incomeData.map(income => income.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(80, 200, 120)',
          borderColor: 'rgb(0, 100, 0)'
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });


    const expense_ctx = this.expenseChart.nativeElement.getContext('2d');
  
    new Chart(expense_ctx, {
      type: 'line',
      data: {
        labels: this.expenseData.map(expense => expense.date),
        datasets: [{
          label: 'Expense',
          data: this.expenseData.map(expense => expense.amount),
          borderWidth: 1,
          backgroundColor: 'rgb(80, 200, 120)',
          borderColor: 'rgb(0, 100, 0)'
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  
  }

  getStatistics(){
    this.dashboardService.getStatistics().subscribe(res => {
      //.log(res);
      this.statsData = res;
    });
  }

  getChartData(){
    this.dashboardService.getChartData().subscribe(res => {
      console.log(res);
      const data = res as any;
      if(data.expenseList != null && data?.incomeList != null){
        this.expenseData = data.expenseList;
        this.incomeData = data.incomeList;

        this.createChart();
      }
    })
  }

}
