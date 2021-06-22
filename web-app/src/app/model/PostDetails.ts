import { PostStatus } from './PostStatus';
export interface PostDetails {
  id: number;
  title: string;
  subtitle: string;
  content: string;
  status: PostStatus;
  author: string;
  authorId: number;
  categoryName: string;
  categoryId: number;
  createdDate: Date;
}
