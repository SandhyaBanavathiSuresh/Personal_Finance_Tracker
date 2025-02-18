import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/services/expenses/expense.service';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.scss']
})
export class ExpenseComponent {

  expenseForm! : FormGroup;

  listOfCategory: any[] = [
    "Shopping",
    "Groceries",
    "Rent",
    "Electricity",
    "Fuel",
    "Travelling",
    "Others"
  ]

  expense: any;

  constructor(private formBuilder: FormBuilder,
    private expenseService: ExpenseService,
    private message: NzMessageService
  ){}

  ngOnInit(){
    this.expenseForm = this.formBuilder.group({
      title: [null, Validators.required],
      amount: [null, Validators.required],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: [null, Validators.required],
    });
    this.getAllExpense;
  }

  submitForm(){
    this.expenseService.postExpense(this.expenseForm.value).subscribe(res=>{
      this.message.success("Expense posted successfully", {nzDuration: 5000});
    }, error=>{
      this.message.error("Error occurred!", {nzDuration: 5000})
    })
  }

  getAllExpense(){
    this.expenseService.getAllExpense().subscribe(res=>{
      this.expense = res;
    })
  }
}
