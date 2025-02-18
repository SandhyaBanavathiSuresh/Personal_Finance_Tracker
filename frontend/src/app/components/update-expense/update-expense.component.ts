import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/services/expenses/expense.service';

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrls: ['./update-expense.component.scss']
})
export class UpdateExpenseComponent {

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
    id: number = this.activatedRoute.snapshot.params["id"];
  
    constructor(private formBuilder: FormBuilder,
      private expenseService: ExpenseService,
      private message: NzMessageService,
      private router: Router,
      private activatedRoute: ActivatedRoute
    ){}
  
    ngOnInit(){
      this.expenseForm = this.formBuilder.group({
        title: [null, Validators.required],
        amount: [null, Validators.required],
        date: [null, Validators.required],
        category: [null, Validators.required],
        description: [null, Validators.required],
      });
      this.getExpenseById();
    }

    getExpenseById(){
      this.expenseService.getExpenseById(this.id).subscribe(res=> {
        this.expenseForm.patchValue(res);
      }, error=> {
        this.message.error("Failed to update expense", {nzDuration: 5000});
      });
    }

    submitForm(){
      this.expenseService.updateExpense(this.id, this.expenseForm.value).subscribe(res=> {
        this.message.success("Updated successfully", {nzDuration: 5000});
        this.router.navigateByUrl("/expense");
      }, error=> {
        this.message.error("Error while updating", {nzDuration: 5000});
      })
    }

}
