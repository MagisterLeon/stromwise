import {Directive, ElementRef, Input, OnInit} from '@angular/core';


@Directive({
  // tslint:disable-next-line:directive-selector
  selector: '[matBadgeIcon]'
})
export class MatBadgeIconDirective implements OnInit {

  @Input() matBadgeIcon: string;

  constructor(private el: ElementRef) {}

  ngOnInit(): void {
    const badge = this.el.nativeElement.querySelector('.mat-badge-content');
    badge.style.display = 'flex';
    badge.style.alignItems = 'center';
    badge.style.justifyContent = 'center';
    badge.style.background = 'transparent';
    badge.style.color = '#f2d065';
    badge.innerHTML = `<i class="material-icons" style="font-size: 20px">${this.matBadgeIcon}</i>`;
  }
}
