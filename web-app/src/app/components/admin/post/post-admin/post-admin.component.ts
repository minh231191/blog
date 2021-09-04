import { PostDisplay } from '../../../../model/PostDisplay';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Direction } from 'src/app/model/Direction';
import { PostFilter } from 'src/app/model/PostFilter';
import { PostPaged } from 'src/app/model/PostPaged';
import { PostService } from 'src/app/services/post.service';
import { PostPageable } from 'src/app/model/PostPageable';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-post-admin',
  templateUrl: './post-admin.component.html',
  styleUrls: ['./post-admin.component.scss']
})
export class PostAdminComponent implements OnInit, PostPageable {

  posts: PostDisplay[] = [];
  postColumns = ['id', 'title', 'status', 'author', 'createdDate', 'category', 'action'];

  isFirstPage = false;
  isLastPage = false;

  constructor(private postService: PostService, private router: Router) {
  }

  ngOnInit(): void {
    this.postService.getPostPaged(1, new Date(), Direction.NEXT, {} as PostFilter).subscribe(data => {
      this.posts = data.posts;
      this.isFirstPage = data.isFirstPage;
      this.isLastPage = data.isLastPage;
    });
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
    return this.postService.getPostPaged(id, date, direction, {} as PostFilter);
  }

  setPostData(postPaged: Observable<PostPaged>): void {
    postPaged.subscribe(data => {
      this.isFirstPage = data.isFirstPage;
      this.isLastPage = data.isLastPage;
      this.posts = data.posts;
    });
  }

  openViewPost(id: number): void {
    this.router.navigate(['admin/posts/' + id.toString()]);
  }

  openEditPost(id: number): void {
    this.router.navigate(['admin/posts/edit/' + id.toString()]);
  }

  openCreatePost(): void {
    this.router.navigate(['admin/posts/create']);
  }

}
