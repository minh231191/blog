import { PostPageable } from './../../../model/PostPageable';
import { PostService } from './../../../services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryDisplay } from './../../../model/CategoryDisplay';
import { CategoryService } from './../../../services/category.service';
import { Component, OnInit } from '@angular/core';
import { PostDisplay } from 'src/app/model/PostDisplay';
import { PostFilter } from 'src/app/model/PostFilter';
import { Observable } from 'rxjs';
import { PostPaged } from 'src/app/model/PostPaged';
import { Direction } from 'src/app/model/Direction';

@Component({
  selector: 'app-post-category',
  templateUrl: './post-category.component.html',
  styleUrls: ['./post-category.component.scss']
})
export class PostCategoryComponent implements OnInit, PostPageable {

  selectedCategory!: CategoryDisplay;
  posts: PostDisplay[] = [];
  isFirstPage = false;
  isLastPage = false;
  postFilter: PostFilter = {} as PostFilter;

  constructor(private postService: PostService, private catagoryService: CategoryService, private route: ActivatedRoute) {
    route.params.subscribe(() => {
      this.selectedCategory = this.catagoryService.getSelectedCategory();
      this.postFilter.categoryId = this.selectedCategory.id;
      this.postService.getPostPaged(1, new Date(), Direction.NEXT, this.postFilter).subscribe(data => {
        this.posts = data.posts;
        this.isFirstPage = data.isFirstPage;
        this.isLastPage = data.isLastPage;
      });
    });
  }

  ngOnInit(): void {
  }

  onClickNext(): void {
    const lastPost = this.posts[this.posts.length - 1];
    this.setPostData(this.getPostData(lastPost.id, lastPost.createdDate, Direction.NEXT));
  }

  onClickPrevious(): void {
    const firstPost = this.posts[0];
    this.setPostData(this.getPostData(firstPost.id, firstPost.createdDate, Direction.PREVIOUS));
  }

  getPostData(id: number, date: Date, direction: Direction): Observable<PostPaged> {
    return this.postService.getPostPaged(id, date, direction, this.postFilter);
  }

  setPostData(postPaged: Observable<PostPaged>): void {
    postPaged.subscribe(data => {
      this.isFirstPage = data.isFirstPage;
      this.isLastPage = data.isLastPage;
      this.posts = data.posts;
    });
  }

}
