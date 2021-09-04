import { TagDisplay } from 'src/app/model/TagDisplay';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TagService } from 'src/app/services/tag.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tag-edit',
  templateUrl: './tag-edit.component.html',
  styleUrls: ['./tag-edit.component.scss']
})
export class TagEditComponent implements OnInit {

  tagForm = new FormGroup({
    name: new FormControl('')
  });

  tag = {} as TagDisplay;

  constructor(
    private router: Router,
    public tagService: TagService,
    public dialogRef: MatDialogRef<TagEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.tagService.getTagById(data.id).subscribe(tag => {
      this.tag = tag;
      this.tagForm.patchValue({name: this.tag.name});
    });
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  save(): void {
    this.tag.name = this.tagForm.get('name')?.value;
    this.tagService.updateTag(this.tag).subscribe(
      success => {
        this.refreshPage();
        this.dialogRef.close();
      }, error => {
      console.log(error);
      }
    );
  }

  refreshPage(): void {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

}
