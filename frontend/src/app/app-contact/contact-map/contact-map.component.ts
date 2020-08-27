import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'st-contact-map',
  template: `
        <iframe
          width="414"
          height="331.175"
          src="https://maps.google.com/maps?width=414&amp;height=331.175&amp;hl=en&amp;q=%20Poznan+()&amp;t=&amp;z=12&amp;ie=UTF8&amp;iwloc=B&amp;output=embed">
        </iframe>
        <script
          type='text/javascript'
          src='https://maps-generator.com/google-maps-authorization/script.js?id=631c245961c053085cc7f27b9ed459b18489af32'>
        </script>
  `,
  styleUrls: ['./contact-map.component.scss']
})
export class ContactMapComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
