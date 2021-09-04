import { TagCreateComponent } from './../tag-create/tag-create.component';
import { TagService } from './../../../../services/tag.service';
import { TagDisplay } from 'src/app/model/TagDisplay';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TagViewComponent } from '../tag-view/tag-view.component';
import { TagEditComponent } from '../tag-edit/tag-edit.component';

@Component({
  selector: 'app-tag-admin',
  templateUrl: './tag-admin.component.html',
  styleUrls: ['./tag-admin.component.scss']
})
export class TagAdminComponent implements OnInit {

  tags: TagDisplay[] = [];
  tagColumns = ['id', 'name', 'status', 'createdBy', 'createdDate', 'action'];

  constructor(private tagService: TagService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.tagService.getAllDisplayTags().subscribe(data => {
      this.tags = data;
    });
  }

  openCreateTag(): void {
    this.dialog.open(TagCreateComponent, {
      width: '800px',
      height: '300px',
      data: {}
    });
  }

  openViewTag(id: number): void {
    this.dialog.open(TagViewComponent, {
      width: '800px',
      height: '300px',
      data: {id}
    });
  }

  openEditTag(id: number): void {
    this.dialog.open(TagEditComponent, {
      width: '800px',
      height: '300px',
      data: {id}
    });
  }
}
