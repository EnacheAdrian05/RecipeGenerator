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
  styleUrl: '../auth.css',
})
export class Register implements OnInit {
  private readonly url = `${environment.apiUrl}/recipe-gen/allergies/`;
  private http = inject(HttpClient);

  allergies: Allergy[] = [];

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
}