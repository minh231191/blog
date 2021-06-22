import { CategoryCreateComponent } from './../category-create/category-create.component';
import { CategoryViewComponent } from './../category-view/category-view.component';
import { Component, OnInit } from '@angular/core';
import { CategoryDisplay } from 'src/app/model/CategoryDisplay';
import { CategoryService } from 'src/app/services/category.service';
import { MatDialog } from '@angular/material/dialog';
import { CategoryEditComponent } from '../category-edit/category-edit.component';

@Component({
  selector: 'app-category-admin',
  templateUrl: './category-admin.component.html',
  styleUrls: ['./category-admin.component.scss']
})
export class CategoryAdminComponent implements OnInit {

  categories: CategoryDisplay[] = [];
  categoryColumns = ['id', 'name', 'status', 'createdBy', 'createdDate', 'action'];

  constructor(private categoryService: CategoryService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.categoryService.getAllDisplayCategory().subscribe(data => {
      this.categories = data;
    });
  }

  openViewCategory(id: number): void {
    this.dialog.open(CategoryViewComponent, {
      width: '800px',
      height: '800px',
      data: {id}
    });
  }

  openEditCategory(id: number): void {
    this.dialog.open(CategoryEditComponent, {
      width: '800px',
      height: '800px',
      data: {id}
    });
  }

  openCreateCategory(): void {
    this.dialog.open(CategoryCreateComponent, {
      width: '800px',
      height: '800px',
      data: {}
    });
  }

}
