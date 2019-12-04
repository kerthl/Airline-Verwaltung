import { Component, HostListener } from '@angular/core';

  // tslint:disable: variable-name
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @HostListener('window:resize', ['$event'])

  title = 'Angular Fluege Karte';
  private _opened = false;
  private _autoCollapseHeight: number = window.innerHeight * 0.7;
  private _autoCollapseWidth: number = window.innerWidth * 0.7;

  private _toggleOpened(): void {
    this._opened = !this._opened;
  }

}
