import { RegisterComponent } from './components/user/register/register.component';
import { CkeditorComponent } from './components/ckeditor/ckeditor.component';
import { UserAdminComponent } from './components/admin/user/user-admin/user-admin.component';
import { TeamAdminComponent } from './components/admin/team/team-admin/team-admin.component';
import { CommentAdminComponent } from './components/admin/comment/comment-admin/comment-admin.component';
import { TagAdminComponent } from './components/admin/tag/tag-admin/tag-admin.component';
import { PostAdminComponent } from './components/admin/post/post-admin/post-admin.component';
import { CategoryAdminComponent } from './components/admin/category/category-admin/category-admin.component';
import { HomeAdminComponent } from './components/admin/home/home-admin/home-admin.component';
import { PostDetailComponent } from './components/post/post-detail/post-detail.component';
import { PostCategoryComponent } from './components/post/post-category/post-category.component';
import { MainComponent } from './components/home/main/main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { LoginComponent } from './components/user/login/login.component';
import { AuthGuard } from './services/auth-guard';
import { PostCreateComponent } from './components/admin/post/post-create/post-create.component';
import { PostEditComponent } from './components/admin/post/post-edit/post-edit.component';
import { PostViewComponent } from './components/admin/post/post-view/post-view.component';
import { MainAdminComponent } from './components/admin/home/main-admin/main-admin.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'home',
        component: MainComponent
      },
      {
        path: 'category/:categoryName',
        component: PostCategoryComponent
      },
      {
        path: 'posts/:postId',
        component: PostDetailComponent
      }
    ]
  },
  {
    path: 'admin',
    component: HomeAdminComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'home',
        component: MainAdminComponent
      },
      {
        path: 'categories',
        component: CategoryAdminComponent
      },
      {
        path: 'posts',
        component: PostAdminComponent
      },
      {
        path: 'tags',
        component: TagAdminComponent
      },
      {
        path: 'comments',
        component: CommentAdminComponent
      },
      {
        path: 'teams',
        component: TeamAdminComponent
      },
      {
        path: 'users',
        component: UserAdminComponent
      },
      {
        path: 'posts/create',
        component: PostCreateComponent
      },
      {
        path: 'posts/:postId',
        component: PostViewComponent
      },
      {
        path: 'posts/edit/:postId',
        component: PostEditComponent
      }
    ]
  },
  {
    path: 'editor',
    component: CkeditorComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
