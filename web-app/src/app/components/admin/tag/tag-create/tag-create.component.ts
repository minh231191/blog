import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TagDisplay } from 'src/app/model/TagDisplay';
import { TagService } from 'src/app/services/tag.service';

@Component({
  selector: 'app-tag-create',
  templateUrl: './tag-create.component.html',
  styleUrls: ['./tag-create.component.scss']
})
export class TagCreateComponent implements OnInit {

  tagForm = new FormGroup({
    name: new FormControl('')
  });

  tag = {} as TagDisplay;

  constructor(
    private router: Router,
    public tagService: TagService,
    public dialogRef: MatDialogRef<TagCreateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
  }

  save(): void {
    this.tag.name = this.tagForm.get('name')?.value;
    this.tagService.createTag(this.tag).subscribe(
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
