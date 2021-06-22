import { Observable } from 'rxjs';
import { Direction } from './Direction';
import { PostPaged } from './PostPaged';

export interface PostPageable {

  onClickNext(): void;
  onClickPrevious(): void;
  getPostData(id: number, date: Date, direction: Direction): Observable<PostPaged>;
  setPostData(postPaged: Observable<PostPaged>): void;

}
