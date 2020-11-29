import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CategoryNamesResponse} from './category-names-response';
import {map} from 'rxjs/operators';
import {Category} from './category';

@Injectable({
  providedIn: 'root'
})
export class CategoryNamesService {

  constructor(private httpClient: HttpClient) {
  }

  getCategories(): Observable<Category[]> {
    return this.httpClient.get<CategoryNamesResponse>(`/api/v1/categories/names`)
      .pipe(
        map(categoryNamesResponse => categoryNamesResponse.categoryNames
          .map(name => ({name, path: `questions/${name}`})))
      );
  }

  getMostPopularCategories(): Observable<Category[]> {
    return this.httpClient.get<CategoryNamesResponse>(`/api/v1/categories/names/most-popular`)
      .pipe(
        map(categoryNamesResponse => categoryNamesResponse.categoryNames
          .map(name => ({name, path: `questions/${name}`})))
      );
  }
}
