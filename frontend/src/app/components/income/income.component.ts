import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/services/income/income.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.scss']
})
export class IncomeComponent {

  incomeForm!: FormGroup;

  listOfCategory: any[] = ["Salary", "Investments", "Bank Transfer", "BitCoin", "Other"];

  income: any;

  constructor(private formBuilder: FormBuilder,
      private message: NzMessageService,
      private router: Router,
      private incomeService: IncomeService
    ){}

  ngOnInit(){
    this.getAllIncome();
    this.incomeForm = this.formBuilder.group({
      title: [null, Validators.required],
      amount: [null, Validators.required],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: [null, Validators.required],
    })
  }

  submitForm(){
    this.incomeService.postIncome(this.incomeForm.value).subscribe(res => {
      this.message.success("Income posted successfully!", {nzDuration: 5000});
      this.getAllIncome();
    }, error=> {
      this.message.error("Error posting income", {nzDuration: 5000});
    })
  }

  getAllIncome(){
    this.incomeService.getAllIncome().subscribe(res=> {
      this.income = res;
    }, error=> {
      this.message.error("Error - Could not retrieve income details", {nzDuration: 5000});
    })
  }

  deleteIncome(id:number){
    this.incomeService.deleteIncome(id).subscribe(res =>{
      this.message.success("Income deleted", {nzDuration: 5000});
      this.getAllIncome();
    }, error=> {
      this.message.error("Error deleting", {nzDuration: 5000});
    })
  }

}
