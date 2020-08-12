import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'st-contact',
  template: `
    <footer data-scroll-identifiant="contact" class="animated actif">
      <div class="contact colonnes">
        <div class="c-50 contenu contenu-typewriter">
          <h1 style="line-height: 0.916667;" aria-label="REQUEST A DEMO" class="blast-root">
            <span class="blast" aria-hidden="true" style="opacity: 1;">R</span>
            <span class="blast" aria-hidden="true" style="opacity: 1;">E</span>
            <span class="blast" aria-hidden="true" style="opacity: 1;">Q</span>
            <span class="blast" aria-hidden="true" style="opacity: 1;">U</span>
            <span class="blast" aria-hidden="true" style="opacity: 1;">E</span>
            <span class="blast" aria-hidden="true" style="opacity: 1;">S</span>
            <span class="blast" aria-hidden="true" style="opacity: 1;">T</span>
            <strong><span class="blast" aria-hidden="true" style="opacity: 1;">A</span>
              <span class="blast" aria-hidden="true" style="opacity: 1;">D</span>
              <span class="blast" aria-hidden="true" style="opacity: 1;">E</span>
              <span class="blast" aria-hidden="true" style="opacity: 1;">M</span>
              <span class="blast" aria-hidden="true" style="opacity: 1;">O</span>
            </strong>
          </h1>
          <form id="form-contact" class="ajax" method="POST" enctype="multipart/form-data" onsubmit="return valider_formulaire('#form-contact');">
            <div class="colonnes marge">
              <div class="c-50 animated">
                <label for="nom">Full name</label>
                <input type="text" name="nom" id="nom" required="">
              </div>
              <div class="c-50 animated">
                <label for="courriel">Email</label>
                <input type="email" name="courriel" id="courriel" required="">
              </div>
              <div class="c-50 animated">
                <label for="telephone">Phone</label>
                <input type="tel" name="telephone" id="telephone">
              </div>
              <div class="c-50 animated">
                <label for="pays">Country</label>
                <input type="text" name="pays" id="pays">
              </div>
              <div class="c-50 animated">
                <label for="entreprise">Company</label>
                <input type="text" name="entreprise" id="entreprise">
              </div>
              <div class="c-50 animated">
                <label for="type-entreprise">Type of company</label>
                <input type="text" name="type-entreprise" id="type-entreprise">
              </div>
              <div class="c-100 animated">
                <label for="message">Message</label>
                <textarea name="message" cols="30" rows="5" id="message" required=""></textarea>
              </div>
            </div>
            <a class="cta animated" onclick="valider_formulaire('#form-contact');">Send</a>
            <input type="hidden" name="action" value="form-contact">
            <input style="position:absolute; left:-9000px;" tabindex="-1" type="text" name="required" autocomplete="off">
          </form>
        </div>
        <div class="c-50">
          <div class="succursale animated">
            <h3 class="animated">MONTREAL</h3>
            <a href="https://www.google.com/maps/place/355+Saint-Catherine+St+W+%23411%2C+Montreal%2C+Quebec+H3B+1A5%2C+Canada">355 Saint-Catherine St W #411, Montreal, Quebec H3B 1A5, Canada</a>
            <a href="tel:18889574935">(+1) 888 957 4935</a>
          </div>
        </div>
      </div>
    </footer>






<!--    <div class="contact">-->
<!--      <h1>Contact with us</h1>-->
<!--        <div class="contact-from">-->
<!--          <form id="form-contact">-->
<!--            <div>-->
<!--              <label for="name">Full name</label>-->
<!--              <input type="text" name="full-name" required>-->
<!--            </div>-->
<!--            <div>-->
<!--              <label for="email">Email</label>-->
<!--              <input type="text" name="email" required>-->
<!--            </div>-->
<!--            <div>-->
<!--              <label for="phone">Phone</label>-->
<!--              <input type="text" name="phone" required>-->
<!--            </div>-->
<!--            <div>-->
<!--              <label for="country">Country</label>-->
<!--              <input type="text" name="country" required>-->
<!--            </div>-->
<!--            <div>-->
<!--              <label for="company">Company</label>-->
<!--              <input type="text" name="company" required>-->
<!--            </div>-->
<!--            <div>-->
<!--              <label for="city">City</label>-->
<!--              <input type="text" name="city" required>-->
<!--            </div>-->
<!--            <div>-->
<!--              <label for="message">Message</label>-->
<!--              <input type="text" name="message" required>-->
<!--            </div>-->
<!--          </form>-->
<!--        </div>-->
<!--        <div class="contact-address">-->
<!--          <h3>Poznań</h3>-->
<!--          <a href="https://www.google.com/maps/place/Muzeum+Historii+Miasta+Poznania/@52.4085868,16.9318455,17z/data=!3m1!4b1!4m5!3m4!1s0x47045b475374faa3:0x65f5d6fb2b0a510b!8m2!3d52.4085868!4d16.9340342">Stary Rynek 1, 00-000 Poznań</a>-->
<!--          <a href="tel:48123456789">(+48) 123 456 789</a>-->
<!--        </div>-->
<!--    </div>-->
  `,
  styleUrls: ['./app-contact.component.scss']
})
export class AppContactComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
