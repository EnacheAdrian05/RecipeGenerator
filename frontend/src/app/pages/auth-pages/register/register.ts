import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { environment } from '../../../../environments/environment.development';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';

export interface Allergy {
  name: string;
}

export interface DietaryRestriction {
  name: string;
  category: string;
}

export interface AllergyResponse {
  allergies: Allergy[];
  timestamp: string;
}

export interface RestrictionResponse {
  restrictions: DietaryRestriction[];
  timestamp: string;
}

export interface RegisterResponse {
  registered: boolean;
  error?: string;
  timestamp: string;
}

export interface CategoryRestrictions {
  category: string;
  restrictions: DietaryRestriction[];
}

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['../auth.css', './register.css'],
})
export class Register implements OnInit {
  private readonly getAllergiesUrl = `${environment.apiUrl}/allergies`;
  private readonly getRestrictionsUrl = `${environment.apiUrl}/dietary-restrictions`;
  private readonly registerUrl = `${environment.apiUrl}/register`;
  private http = inject(HttpClient);
  private router = inject(Router);

  allergies: Allergy[] = [];
  selectedAllergies: Allergy[] = [];
  allergyInput: string = '';
  restrictionsGroups: CategoryRestrictions[] = [];
  selectedRestrictions: DietaryRestriction[] = [];
  restrictionInput: string = '';

  ngOnInit(): void {
    this.getAllAllergies().subscribe(
      allergies => {this.allergies = allergies;}
    );

    this.getAllDietaryRestrictions().subscribe(
      restrictions => {
        const categories: Set<string> = new Set(restrictions.map(r => r.category));
        for (let c of categories) {
          const categoryRestrictions = restrictions.filter(r => r.category === c);
          this.restrictionsGroups.push({
            category: c,
            restrictions: categoryRestrictions
          }) 
        }
      }
    );
  }

  getAllAllergies(): Observable<Allergy[]> {
    return this.http
      .get<AllergyResponse>(this.getAllergiesUrl)
      .pipe(map(res => res.allergies));
  }

  getAllDietaryRestrictions(): Observable<DietaryRestriction[]> {
    return this.http
      .get<RestrictionResponse>(this.getRestrictionsUrl)
      .pipe(map(res => res.restrictions));
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

  onRestrictionInput(e: Event): void {
    const value = (e.target as HTMLInputElement).value.trim();
    this.restrictionInput = value;

    const match = this.restrictionsGroups
      .flatMap(g => g.restrictions)
      .find(r => r.name.toUpperCase() === value.toUpperCase());

    if (!match) {
      return;
    }

    if (match.category === "Religious" && 
      this.selectedRestrictions.some(r => r.category === "Religious")) {
      alert("You cannot have 2 religious dietary restrictions!");
      return;
    }

    if (!this.selectedRestrictions.some(r => r.name === match.name)) {
      this.selectedRestrictions.push(match);
    }
    
    this.restrictionInput = '';
  }

  removeAllergy(a: Allergy): void {
    this.selectedAllergies = this.selectedAllergies.filter(a1 => a1.name !== a.name);
  }

  removeRestriction(r: DietaryRestriction): void {
    this.selectedRestrictions = this.selectedRestrictions.filter(r1 => r1.name !== r.name);
  }

  register(form: NgForm) {
    if (form.invalid) {
        form.control.markAllAsTouched();
        return;
    }

    const body = {
      name: form.value.name,
      password: form.value.password,
      allergies: this.selectedAllergies.map(a => a.name),
      restrictions: this.selectedRestrictions.map(r => r.name)
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