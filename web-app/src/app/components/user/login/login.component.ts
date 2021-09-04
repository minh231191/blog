import { HttpErrorResponse } from '@angular/common/http';
import { JwtRequest } from './../../../model/JwtRequest';
import { AuthenticationService } from './../../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    name: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  login(): void {
    const authRequest = {} as JwtRequest;
    authRequest.username = this.loginForm.get('username')?.value;
    authRequest.password = this.loginForm.get('password')?.value;
    this.authService.authenticate(authRequest).subscribe(
      (data: { token: string; }) => {
        sessionStorage.setItem('username', authRequest.username);
        sessionStorage.setItem('token', 'Bearer ' + data.token);
        this.router.navigate(['/admin/home']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 401) {
          alert('Invalid Username or Password');
        } else {
          alert(error.statusText);
        }
      }
    );
  }

}
