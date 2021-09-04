import { ListTransfer } from 'src/app/common/list-transfer';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoryDisplay } from 'src/app/model/CategoryDisplay';
import { PostDetails } from 'src/app/model/PostDetails';
import { CategoryService } from 'src/app/services/category.service';
import { PostService } from 'src/app/services/post.service';
import { ImageUploadAdapter } from 'src/app/services/Image-uploader-adapter';
import { CkeditorComponent } from 'src/app/components/ckeditor/ckeditor.component';
import { TagDisplay } from 'src/app/model/TagDisplay';
import { TagService } from 'src/app/services/tag.service';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss']
})
export class PostCreateComponent extends ListTransfer<TagDisplay> implements OnInit {

  @ViewChild(CkeditorComponent)
  ckEditor!: CkeditorComponent;

  categories: CategoryDisplay[] = [];

  toBeCreatedPost = {} as PostDetails;

  postForm = new FormGroup({
    title: new FormControl(''),
    subtitle: new FormControl(''),
    category: new FormControl('')
  });

  constructor(private postService: PostService,
              private categoryService: CategoryService,
              private tagService: TagService,
              private router: Router) {
    super();
  }

  ngOnInit(): void {
    this.categoryService.getAllDisplayCategory().subscribe(data => {
      this.categories = data;
    });
    this.tagService.getAllDisplayTags().subscribe(data => {
      this.availableElements = data;
    });
    this.toBeCreatedPost.content = '';
  }

  onReady(editor: any): void {
    editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader: any ) => {
        return new ImageUploadAdapter( loader );
    };
  }

  save(): void {
    this.toBeCreatedPost.title = this.postForm.get('title')?.value;
    this.toBeCreatedPost.subtitle = this.postForm.get('subtitle')?.value;
    this.toBeCreatedPost.categoryId = this.postForm.get('category')?.value;
    this.toBeCreatedPost.tags = this.addedElements;
    this.toBeCreatedPost.content = this.ckEditor.getData();
    this.postService.createPost(this.toBeCreatedPost).subscribe(
      success => {
        this.router.navigate(['/admin/posts']);
      }, error => {
        alert(error);
    });
  }

}
