import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagAdminComponent } from './tag-admin.component';

describe('TagAdminComponent', () => {
  let component: TagAdminComponent;
  let fixture: ComponentFixture<TagAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TagAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TagAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
