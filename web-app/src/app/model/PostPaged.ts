import { PostDisplay } from './PostDisplay';
export interface PostPaged {
  isFirstPage: boolean;
  isLastPage: boolean;
  posts: PostDisplay[];
}
