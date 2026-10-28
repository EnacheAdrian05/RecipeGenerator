import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'my-header',
  imports: [RouterLink],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
}
