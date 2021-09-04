import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TagDisplay } from '../model/TagDisplay';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) { }

  getAllDisplayTags(): Observable<TagDisplay[]> {
    return this.http.get<TagDisplay[]>('blog/tags');
  }

  getTagById(id: number): Observable<TagDisplay> {
    return this.http.get<TagDisplay>('blog/tags/' + id.toString());
  }

  createTag(tag: TagDisplay): Observable<TagDisplay> {
    return this.http.post<TagDisplay>('blog/tags/', tag);
  }

  updateTag(tag: TagDisplay): Observable<TagDisplay> {
    return this.http.put<TagDisplay>('blog/tags/' + tag.id.toString(), tag);
  }

}
