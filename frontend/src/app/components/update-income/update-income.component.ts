import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/services/income/income.service';

@Component({
  selector: 'app-update-income',
  templateUrl: './update-income.component.html',
  styleUrls: ['./update-income.component.scss']
})
export class UpdateIncomeComponent {

  incomeForm!: FormGroup;
  
    listOfCategory: any[] = ["Salary", "Investments", "Bank Transfer", "BitCoin", "Other"];
  
    id: number = this.activatedRoute.snapshot.params["id"];
    income: any;
  
    constructor(private formBuilder: FormBuilder,
        private message: NzMessageService,
        private router: Router,
        private incomeService: IncomeService,
        private activatedRoute: ActivatedRoute
      ){}
  
    ngOnInit(){
      this.incomeForm = this.formBuilder.group({
        title: [null, Validators.required],
        amount: [null, Validators.required],
        date: [null, Validators.required],
        category: [null, Validators.required],
        description: [null, Validators.required],
      });
      this.getIncomeById();
    }

    getIncomeById(){
      this.incomeService.getIncomeById(this.id).subscribe(res=> {
        this.incomeForm.patchValue(res);
      }, error=> {
        this.message.error("Failed to update income", {nzDuration: 5000});
      });
    }

    submitForm(){
      this.incomeService.updateIncome(this.id, this.incomeForm.value).subscribe(res=> {
        this.message.success("Updated successfully", {nzDuration: 5000});
        this.router.navigateByUrl("/income");
      }, error=> {
        this.message.error("Error while updating", {nzDuration: 5000});
      })
    }
}
