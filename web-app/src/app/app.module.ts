import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { HomeComponent } from './components/home/home/home.component';
import { LeftComponent } from './components/home/left/left.component';
import { RightComponent } from './components/home/right/right.component';
import { MainComponent } from './components/home/main/main.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTreeModule} from '@angular/material/tree';
import {MatListModule} from '@angular/material/list';
import { FooterComponent } from './components/home/footer/footer.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatDividerModule} from '@angular/material/divider';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSortModule} from '@angular/material/sort';
import {MatDialogModule} from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { PostCategoryComponent } from './components/post/post-category/post-category.component';
import { PostDetailComponent } from './components/post/post-detail/post-detail.component';
import { MainAdminComponent } from './components/admin/main-admin/main-admin.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { LeftAdminComponent } from './components/admin/left-admin/left-admin.component';
import { PostAdminComponent } from './components/admin/post-admin/post-admin.component';
import { TagAdminComponent } from './components/admin/tag-admin/tag-admin.component';
import { CommentAdminComponent } from './components/admin/comment-admin/comment-admin.component';
import { TeamAdminComponent } from './components/admin/team-admin/team-admin.component';
import { UserAdminComponent } from './components/admin/user-admin/user-admin.component';
import { CategoryViewComponent } from './components/admin/category-view/category-view.component';
import { CategoryEditComponent } from './components/admin/category-edit/category-edit.component';
import { CategoryAdminComponent } from './components/admin/category-admin/category-admin.component';
import { CategoryCreateComponent } from './components/admin/category-create/category-create.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LeftComponent,
    RightComponent,
    MainComponent,
    FooterComponent,
    PostCategoryComponent,
    PostDetailComponent,
    MainAdminComponent,
    HomeAdminComponent,
    LeftAdminComponent,
    CategoryAdminComponent,
    PostAdminComponent,
    TagAdminComponent,
    CommentAdminComponent,
    TeamAdminComponent,
    UserAdminComponent,
    CategoryViewComponent,
    CategoryEditComponent,
    CategoryCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatButtonModule,
    MatGridListModule,
    MatTreeModule,
    MatListModule,
    MatCardModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatSortModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    FlexLayoutModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
