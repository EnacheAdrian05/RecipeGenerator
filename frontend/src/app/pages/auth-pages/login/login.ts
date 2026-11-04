import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { environment } from '../../../../environments/environment.development';
import { catchError, of } from 'rxjs';
import { Router } from '@angular/router';

export interface LoginResponse {
  logged: boolean,
  error?: string,
  timestamp: string
}

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: '../auth.css',
})
export class Login {
  private readonly url = `${environment.apiUrl}/login/`;
  private http = inject(HttpClient);
  private router = inject(Router);

  login(form: NgForm): void {
      if (form.invalid) {
        form.control.markAllAsTouched();
        return;
      }

      const body = form.value;
      this.http
        .post<LoginResponse>(this.url, body)
        .pipe(
          catchError((err: HttpErrorResponse) =>
            of(err.error as LoginResponse)
          )
        )
        .subscribe((res) => {
          if (res.logged) {
            alert(`Welcome, ${body.name}!`);
            this.router.navigate(["/"]);
          } else {
            alert(res.error);
          }
        });
  }
}
