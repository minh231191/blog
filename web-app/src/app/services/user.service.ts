import { UserDetails } from './../model/UserDetails';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  createUser(user: UserDetails): Observable<UserDetails> {
    return this.http.post<UserDetails>('blog/users', user);
  }

  register(user: UserDetails): Observable<UserDetails> {
    return this.http.post<UserDetails>('blog/users/register', user);
  }

}
