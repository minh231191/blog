import { CategoryDisplay } from './../../../model/CategoryDisplay';
import { CategoryService } from './../../../services/category.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  categories: CategoryDisplay[] = [];

  constructor(private categoryService: CategoryService, private router: Router) { }

  ngOnInit(): void {
    this.categoryService.getAllDisplayCategoryWithSubCategories().subscribe(data => {
      this.categories = data;
    });
    this.router.navigate(['home']);
  }

}
