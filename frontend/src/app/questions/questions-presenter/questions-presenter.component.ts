import {Component, OnInit} from '@angular/core';
import {GetQuestionsService} from "../get-questions.service";

@Component({
  selector: 'st-questions-presenter',
  template: `
    <mdb-carousel [animation]="'slide'">
      <mdb-carousel-item>
        <img class="d-block w-100" src="https://mdbootstrap.com/img/Photos/Slides/img%20(130).jpg" alt="First slide">
      </mdb-carousel-item>
      <mdb-carousel-item>
        <img class="d-block w-100" src="https://mdbootstrap.com/img/Photos/Slides/img%20(129).jpg" alt="Second slide">
      </mdb-carousel-item>
      <mdb-carousel-item>
        <img class="d-block w-100" src="https://mdbootstrap.com/img/Photos/Slides/img%20(70).jpg" alt="Third slide">
      </mdb-carousel-item>
    </mdb-carousel>
  `,
  styleUrls: ['./questions-presenter.component.scss']
})
export class QuestionsPresenterComponent implements OnInit {

  constructor(private getQuestionsService: GetQuestionsService) {

  }

  ngOnInit(): void {
  }

}
