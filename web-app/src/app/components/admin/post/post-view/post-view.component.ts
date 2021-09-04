import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostDetails } from 'src/app/model/PostDetails';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {

  postId: number | undefined;
  currentPost = {} as PostDetails;

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.currentPost.content = '';
    route.params.subscribe(params => {
      this.postId = params.postId;
      postService.getPostDetailsById(params.postId).subscribe(data => {
        this.currentPost = data;
      });
    });
   }

  ngOnInit(): void {
  }

}
