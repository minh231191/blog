import { Router } from '@angular/router';
import { CategoryService } from './../../../services/category.service';
import { CategoryDisplay } from './../../../model/CategoryDisplay';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-left',
  templateUrl: './left.component.html',
  styleUrls: ['./left.component.scss']
})
export class LeftComponent implements OnInit {

  @Input()
  categories: CategoryDisplay[] = [];

  constructor(private categoryService: CategoryService, private router: Router) {}

  ngOnInit(): void {
  }

  navigateToCategoryPost(category: CategoryDisplay): void {
    this.setSelectedCategory(category);
    this.router.navigate(['/category', category.name]);
  }

  setSelectedCategory(category: CategoryDisplay): void {
    this.categoryService.setSelectedCategory(category);
  }

}
