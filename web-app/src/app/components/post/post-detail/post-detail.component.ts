import { PostDetails } from './../../../model/PostDetails';
import { PostService } from './../../../services/post.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {

  postId: number | undefined;
  currentPost = {} as PostDetails;

  constructor(private route: ActivatedRoute, private postService: PostService) {
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
