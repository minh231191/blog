import { PostPageable } from './../../../model/PostPageable';
import { PostFilter } from './../../../model/PostFilter';
import { Observable } from 'rxjs';
import { PostService } from './../../../services/post.service';
import { PostDisplay } from './../../../model/PostDisplay';
import { Component, OnInit } from '@angular/core';
import { PostPaged } from 'src/app/model/PostPaged';
import { Direction } from 'src/app/model/Direction';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, PostPageable {

  posts: PostDisplay[] = [];
  isFirstPage = false;
  isLastPage = false;

  constructor(private postService: PostService) { }

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

}
