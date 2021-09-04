import { ListTransfer } from 'src/app/common/list-transfer';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/model/Category';
import { CategoryService } from 'src/app/services/category.service';
import { CategoryStatus } from 'src/app/model/CategoryStatus';

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.scss']
})
export class CategoryEditComponent extends ListTransfer<Category> implements OnInit {

  categoryForm = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    status: new FormControl(''),
  });

  category = {} as Category;

  categoryStatuses = Object.keys(CategoryStatus)
    .map(key => CategoryStatus[Number(key)]).filter(value => typeof value === 'string') as string[];

  constructor(
    public categoryService: CategoryService,
    public dialogRef: MatDialogRef<CategoryEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    super();
    this.categoryService.getCategoryById(data.id).subscribe(category => {
      this.category = category;
      this.addedElements = category.categories;
      this.categoryForm.patchValue({
        name: this.category.name,
        description: this.category.description,
        status: this.category.status
      });
      this.categoryService.getAvailableSubCategories(this.category.id).subscribe(categories => {
        this.availableElements = categories;
      });
    });
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
    this.categoryService.updateCategory(this.category).subscribe(
      success => {
      this.dialogRef.close();
    }, error => {
      console.log(error);
    });

  }

}
