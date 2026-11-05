import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { environment } from '../../../../environments/environment.development';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';

export interface Allergy {
  name: string;
}

export interface AllergyResponse {
  allergies: Allergy[];
  timestamp: string;
}

export interface RegisterResponse {
  registered: boolean;
  error?: string;
  timestamp: string;
}

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['../auth.css', './register.css'],
})
export class Register implements OnInit {
  private readonly getAllergiesUrl = `${environment.apiUrl}/allergies/`;
  private readonly registerUrl = `${environment.apiUrl}/register/`;
  private http = inject(HttpClient);
  private router = inject(Router);

  allergies: Allergy[] = [];
  selectedAllergies: Allergy[] = [];
  allergyInput = '';

  ngOnInit(): void {
    this.getAllAllergies().subscribe(allergies => {
      this.allergies = allergies;
    });
  }

  getAllAllergies(): Observable<Allergy[]> {
    return this.http
      .get<AllergyResponse>(this.getAllergiesUrl)
      .pipe(map(res => res.allergies));
  }

  onAllergyInput(e: Event): void {
    const value = (e.target as HTMLInputElement).value.trim();
    this.allergyInput = value;
    
    const match = this.allergies.find(
      a => a.name.toUpperCase() === value.toUpperCase());

    if (!match) {
      return;
    }

    if(!this.selectedAllergies.some(a => a.name === match.name)) {
      this.selectedAllergies.push(match);
    }

    this.allergyInput = '';
  }

  removeAllergy(a: Allergy): void {
    this.selectedAllergies = this.selectedAllergies.filter(a1 => a1.name !== a.name)
  }

  register(form: NgForm) {
    if (form.invalid) {
        form.control.markAllAsTouched();
        return;
    }

    const body = {
      name: form.value.name,
      email: form.value.email,
      password: form.value.password,
      allergies: this.selectedAllergies.map(a => a.name),
    };

    this.http.post<RegisterResponse>(this.registerUrl, body)
      .pipe(
        catchError((err: HttpErrorResponse) =>
          of(err.error as RegisterResponse)
        )
      )
      .subscribe((res) => {
        if (res.registered) {
          alert("Registration was successful!");
          this.router.navigate(["/login"]);
        }

        else {
          alert(res.error);
        }
      });
  }
}