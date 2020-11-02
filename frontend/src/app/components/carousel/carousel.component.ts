import {
  AfterViewInit,
  Component,
  ContentChildren,
  Directive,
  ElementRef,
  Input,
  QueryList,
  Renderer2,
  ViewChild,
  ViewChildren
} from '@angular/core';
import {CarouselItemDirective} from './carousel-item.directive';
import {animate, AnimationBuilder, AnimationFactory, AnimationPlayer, style} from '@angular/animations';

@Directive({
  // tslint:disable-next-line:directive-selector
  selector: '.carousel-item'
})
export class CarouselItemElementDirective {
}

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'carousel',
  template: `
    <section class="carousel-wrapper" [ngStyle]="carouselWrapperStyle">
      <ul class="carousel-inner" #carousel>
        <li *ngFor="let item of items;" class="carousel-item">
          <ng-container [ngTemplateOutlet]="item.tpl"></ng-container>
        </li>
      </ul>
    </section>
  `,
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements AfterViewInit {
  @ContentChildren(CarouselItemDirective) items: QueryList<CarouselItemDirective>;
  @ViewChildren(CarouselItemElementDirective, {read: ElementRef}) private itemsElements: QueryList<ElementRef>;
  @ViewChild('carousel') private carousel: ElementRef;

  @Input() timing = '250ms ease-in';

  private player: AnimationPlayer;
  private itemWidth: number;
  private currentSlide = 0;
  carouselWrapperStyle = {};

  constructor(private builder: AnimationBuilder,
              private renderer: Renderer2) {
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      this.itemWidth = (100 / this.itemsElements.length);

      this.updateCarouselStyleWidth();
      this.updateItemStyleWidth();
    }, 0);
  }

  next(): void {
    if (this.isLastSlide()) {
      return;
    }
    this.currentSlide = (this.currentSlide + 1) % this.items.length;

    this.playAnimation();
  }

  prev(): void {
    if (this.currentSlide === 0) {
      return;
    }
    this.currentSlide = ((this.currentSlide - 1) + this.items.length) % this.items.length;

    this.playAnimation();
  }

  isLastSlide(): boolean {
    return this.currentSlide + 1 === this.items.length;
  }

  getCurrentSlide(): number {
    return this.currentSlide;
  }

  private buildAnimation(offset): AnimationFactory {
    return this.builder.build([
      animate(this.timing, style({transform: `translateX(-${offset}%)`}))
    ]);
  }

  private playAnimation(): void {
    const offset = this.currentSlide * this.itemWidth;
    const myAnimation: AnimationFactory = this.buildAnimation(offset);
    this.player = myAnimation.create(this.carousel.nativeElement);
    this.player.play();
  }

  private updateCarouselStyleWidth(): void {
    const width: string = (this.itemsElements.length * 100) + '%';
    this.renderer.setStyle(this.carousel.nativeElement, 'width', width);
  }

  private updateItemStyleWidth(): void {
    const slideWidthInContainer: string = (100 / this.itemsElements.length) + '%';
    this.itemsElements.forEach(el => {
      this.renderer.setStyle(el.nativeElement, 'width', slideWidthInContainer);
    });
    this.carouselWrapperStyle = {
      width: `100%`
    };
  }
}
