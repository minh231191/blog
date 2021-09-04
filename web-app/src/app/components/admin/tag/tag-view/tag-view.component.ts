import { TagDisplay } from 'src/app/model/TagDisplay';
import { Component, Inject, OnInit } from '@angular/core';
import { TagService } from 'src/app/services/tag.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-tag-view',
  templateUrl: './tag-view.component.html',
  styleUrls: ['./tag-view.component.scss']
})
export class TagViewComponent implements OnInit {

  tag = {} as TagDisplay;

  constructor(
    public tagService: TagService,
    public dialogRef: MatDialogRef<TagViewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.tagService.getTagById(data.id).subscribe(tag => {
      this.tag = tag;
    });
  }

  ngOnInit(): void {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
