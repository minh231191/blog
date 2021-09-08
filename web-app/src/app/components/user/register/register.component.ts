import { UserDetails } from './../../../model/UserDetails';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    name: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  save(): void {
    const user = {} as UserDetails;
    user.name = this.registerForm.get('name')?.value;
    user.username = this.registerForm.get('username')?.value;
    user.password = this.registerForm.get('password')?.value;
    this.userService.register(user).subscribe(
      success => {
        alert('Success!');
        this.router.navigate(['']);
    }, error => {
      alert(error);
    });
  }

}
