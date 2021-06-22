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
export class CategoryCreateComponent implements OnInit {

  categoryForm = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    status: new FormControl(''),
  });
  category = {} as Category;
  categoryStatuses = Object.keys(CategoryStatus)
    .map(key => CategoryStatus[Number(key)]).filter(value => typeof value === 'string') as string[];

  subCategories = [] as Category[];
  availableSubCategories = [] as Category[];
  selectedCategories = [] as Category[];

  constructor(
    private router: Router,
    public categoryService: CategoryService,
    public dialogRef: MatDialogRef<CategoryCreateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.categoryService.getAllAvailableSubCategories().subscribe(categories => {
      this.availableSubCategories = categories;
    });
   }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  toogleActiveCategory(category: Category): void {
    if (this.selectedCategories.find(item => item === category)) {
      this.selectedCategories = this.selectedCategories.filter(item => item !== category);
    } else {
      if (this.subCategories.indexOf(category) !== -1) {
        const filteredCategory = this.selectedCategories.filter(item => this.availableSubCategories.includes(item));
        this.selectedCategories = this.selectedCategories.filter(item => !filteredCategory.includes(item));
      } else if (this.availableSubCategories.indexOf(category) !== -1) {
        const filteredCategory = this.selectedCategories.filter(item => this.subCategories.includes(item));
        this.selectedCategories = this.selectedCategories.filter(item => !filteredCategory.includes(item));
      }
      this.selectedCategories.push(category);
    }
  }

  isInActiveItems(category: Category): boolean {
    return this.isIn(category, this.selectedCategories);
  }

  isIn(category: Category, toFind: Category[]): boolean {
    return toFind.findIndex(item => item === category) !== -1;
  }

  moveToTheRight(): void {
    this.availableSubCategories = this.availableSubCategories.filter(category => {
      if (!this.isInActiveItems(category)) {
        return true;
      } else {
        this.subCategories.push(category);
        return false;
      }
    });
    this.selectedCategories.length = 0;
  }

  moveToTheLeft(): void {
    this.subCategories = this.subCategories.filter(category => {
      if (!this.isInActiveItems(category)) {
        return true;
      } else {
        this.availableSubCategories.push(category);
        return false;
      }
    });
    this.selectedCategories.length = 0;
  }

  save(): void {
    this.category.name = this.categoryForm.get('name')?.value;
    this.category.description = this.categoryForm.get('description')?.value;
    this.category.status = this.categoryForm.get('status')?.value;
    this.category.categories = this.subCategories;
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
