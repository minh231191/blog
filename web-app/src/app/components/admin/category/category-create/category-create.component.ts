import { ListTransfer } from 'src/app/common/list-transfer';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/Category';
import { CategoryStatus } from 'src/app/model/CategoryStatus';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.scss']
})
export class CategoryCreateComponent extends ListTransfer<Category> implements OnInit {

  categoryForm = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    status: new FormControl(),
  });
  category = {} as Category;
  categoryStatuses = Object.keys(CategoryStatus)
    .map(key => CategoryStatus[Number(key)]).filter(value => typeof value === 'string') as string[];

  constructor(
    private router: Router,
    public categoryService: CategoryService,
    public dialogRef: MatDialogRef<CategoryCreateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    super();
    this.categoryService.getAllAvailableSubCategories().subscribe(categories => {
      this.availableElements = categories;
    });
    this.categoryForm.patchValue({status: 'ACTIVE'});
   }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(): void {
    this.category.name = this.categoryForm.get('name')?.value;
    this.category.description = this.categoryForm.get('description')?.value;
    this.category.status = this.categoryForm.get('status')?.value;
    this.category.categories = this.addedElements;
    this.categoryService.createCategory(this.category).subscribe(
      success => {
      this.refreshPage();
      this.dialogRef.close();
    }, error => {
      console.log(error);
    });
  }

  refreshPage(): void {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
}

}
