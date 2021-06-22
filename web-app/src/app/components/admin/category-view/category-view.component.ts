import { CategoryService } from 'src/app/services/category.service';
import { Category } from './../../../model/Category';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-category-view',
  templateUrl: './category-view.component.html',
  styleUrls: ['./category-view.component.scss']
})
export class CategoryViewComponent implements OnInit {

  category = {} as Category;

  constructor(
    public categoryService: CategoryService,
    public dialogRef: MatDialogRef<CategoryViewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.categoryService.getCategoryById(data.id).subscribe(category => {
      this.category = category;
    });
  }

  ngOnInit(): void {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
