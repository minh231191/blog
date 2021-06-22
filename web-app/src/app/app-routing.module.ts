import { UserAdminComponent } from './components/admin/user-admin/user-admin.component';
import { TeamAdminComponent } from './components/admin/team-admin/team-admin.component';
import { CommentAdminComponent } from './components/admin/comment-admin/comment-admin.component';
import { TagAdminComponent } from './components/admin/tag-admin/tag-admin.component';
import { PostAdminComponent } from './components/admin/post-admin/post-admin.component';
import { CategoryAdminComponent } from './components/admin/category-admin/category-admin.component';
import { HomeAdminComponent } from './components/admin/home-admin/home-admin.component';
import { MainAdminComponent } from './components/admin/main-admin/main-admin.component';
import { PostDetailComponent } from './components/post/post-detail/post-detail.component';
import { PostCategoryComponent } from './components/post/post-category/post-category.component';
import { MainComponent } from './components/home/main/main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';

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
    children: [
      {
        path: 'home',
        component: MainAdminComponent
      },
      {
        path: 'category',
        component: CategoryAdminComponent
      },
      {
        path: 'post',
        component: PostAdminComponent
      },
      {
        path: 'tag',
        component: TagAdminComponent
      },
      {
        path: 'comment',
        component: CommentAdminComponent
      },
      {
        path: 'team',
        component: TeamAdminComponent
      },
      {
        path: 'user',
        component: UserAdminComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
