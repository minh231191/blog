import { LoginComponent } from './components/user/login/login.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { PostCategoryComponent } from './components/post/post-category/post-category.component';
import { PostDetailComponent } from './components/post/post-detail/post-detail.component';
import { HomeAdminComponent } from './components/admin/home/home-admin/home-admin.component';
import { PostAdminComponent } from './components/admin/post/post-admin/post-admin.component';
import { TagAdminComponent } from './components/admin/tag/tag-admin/tag-admin.component';
import { CommentAdminComponent } from './components/admin/comment/comment-admin/comment-admin.component';
import { TeamAdminComponent } from './components/admin/team/team-admin/team-admin.component';
import { UserAdminComponent } from './components/admin/user/user-admin/user-admin.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { CkeditorComponent } from './components/ckeditor/ckeditor.component';
import { HighlightModule, HIGHLIGHT_OPTIONS } from 'ngx-highlightjs';
import { HighlightCodePipePipe } from './components/ckeditor/highlight-code-pipe.pipe';
import { RegisterComponent } from './components/user/register/register.component';
import { DEFAULT_TIMEOUT, HttpInterceptorService } from './services/http-interceptor.service';
import { AuthGuard } from './services/auth-guard';
import { PostEditComponent } from './components/admin/post/post-edit/post-edit.component';
import { PostViewComponent } from './components/admin/post/post-view/post-view.component';
import { PostCreateComponent } from './components/admin/post/post-create/post-create.component';
import { CategoryAdminComponent } from './components/admin/category/category-admin/category-admin.component';
import { CategoryCreateComponent } from './components/admin/category/category-create/category-create.component';
import { CategoryEditComponent } from './components/admin/category/category-edit/category-edit.component';
import { CategoryViewComponent } from './components/admin/category/category-view/category-view.component';
import { LeftAdminComponent } from './components/admin/home/left-admin/left-admin.component';
import { MainAdminComponent } from './components/admin/home/main-admin/main-admin.component';
import { TagViewComponent } from './components/admin/tag/tag-view/tag-view.component';
import { TagEditComponent } from './components/admin/tag/tag-edit/tag-edit.component';
import { TagCreateComponent } from './components/admin/tag/tag-create/tag-create.component';

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
    CategoryCreateComponent,
    PostViewComponent,
    PostEditComponent,
    PostCreateComponent,
    CkeditorComponent,
    HighlightCodePipePipe,
    RegisterComponent,
    LoginComponent,
    TagViewComponent,
    TagEditComponent,
    TagCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CKEditorModule,
    FormsModule,
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
    HighlightModule
  ],
  providers: [
    {
      provide: HIGHLIGHT_OPTIONS,
      useValue: {
        coreLibraryLoader: () => import('highlight.js/lib/core'),
        languages: {
          typescript: () => import('highlight.js/lib/languages/typescript'),
          java: () => import('highlight.js/lib/languages/java')
        }
      }
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    { provide: DEFAULT_TIMEOUT, useValue: 60000 },
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
