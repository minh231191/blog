
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PostDetails } from 'src/app/model/PostDetails';
import { PostService } from 'src/app/services/post.service';
import { CategoryService } from 'src/app/services/category.service';
import { CategoryDisplay } from 'src/app/model/CategoryDisplay';
import { TagDisplay } from 'src/app/model/TagDisplay';
import { TagService } from 'src/app/services/tag.service';
import { ListTransfer } from 'src/app/common/list-transfer';
import { CkeditorComponent } from 'src/app/components/ckeditor/ckeditor.component';
import { PostStatus } from 'src/app/model/PostStatus';

@Component({
  selector: 'app-post-edit',
  templateUrl: './post-edit.component.html',
  styleUrls: ['./post-edit.component.scss']
})
export class PostEditComponent extends ListTransfer<TagDisplay> implements OnInit {

  @ViewChild(CkeditorComponent)
  ckEditor!: CkeditorComponent;

  categories: CategoryDisplay[] = [];

  postForm = new FormGroup({
    category: new FormControl(''),
    title: new FormControl(''),
    subtitle: new FormControl(''),
    status: new FormControl('')
  });

  postId: number | undefined;
  currentPost = {} as PostDetails;
  postStatuses = Object.keys(PostStatus)
    .map(key => PostStatus[Number(key)]).filter(value => typeof value === 'string') as string[];

  constructor(private route: ActivatedRoute,
              private postService: PostService,
              private categoryService: CategoryService,
              private tagService: TagService,
              private router: Router) {
    super();
    route.params.subscribe(params => {
      this.postId = params.postId;
      postService.getPostDetailsById(params.postId).subscribe(post => {
        this.currentPost = post;
        this.addedElements = post.tags;
        this.categoryService.getAllDisplayCategory().subscribe(categories => {

          this.categories = categories;
          this.postForm.patchValue({
            category: this.currentPost.categoryId,
            title: this.currentPost.title,
            subtitle: this.currentPost.subtitle,
            status: this.currentPost.status
          });
        });

        this.tagService.getAllDisplayTags().subscribe(tags => {
          this.availableElements = tags.filter(t => !this.addedElements.find(pt => pt.id === t.id));
        });

        this.ckEditor.setData(this.currentPost.content);
      });
    });
   }

  ngOnInit(): void {
  }

  save(): void {
    this.currentPost.status = this.postForm.get('status')?.value;
    this.currentPost.title = this.postForm.get('title')?.value;
    this.currentPost.subtitle = this.postForm.get('subtitle')?.value;
    this.currentPost.tags = this.addedElements;
    this.currentPost.content = this.ckEditor.getData();
    this.postService.updatePost(this.currentPost).subscribe(
      success => {
        this.router.navigate(['/admin/posts']);
    }, error => {
      console.log(error);
    });
  }

}
