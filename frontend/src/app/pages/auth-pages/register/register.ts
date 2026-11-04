import { HttpClient } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { map, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment.development';

export interface Allergy {
  name: string;
}

export interface AllergyResponse {
  allergies: Allergy[];
  timestamp: string;
}

@Component({
  selector: 'app-register',
  imports: [RouterLink],
  templateUrl: './register.html',
  styleUrls: ['../auth.css', './register.css'],
})
export class Register implements OnInit {
  private readonly url = `${environment.apiUrl}/allergies/`;
  private http = inject(HttpClient);

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
      .get<AllergyResponse>(this.url)
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
}