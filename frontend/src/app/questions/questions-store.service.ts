import {Injectable} from '@angular/core';
import {QuestionResponseType} from './question-response-type';
import {Question} from './question';

@Injectable({
  providedIn: 'root'
})
export class QuestionsStore {

  private questionResponseByPublicId: Map<string, QuestionResponseType> = new Map<string, QuestionResponseType>();
  questions: Question[];

  constructor() {
  }

  hasAllResponses(): boolean {
    return this.questions && this.questions.length === this.questionResponseByPublicId.size;
  }

  getResponse(publicId: string): QuestionResponseType {
    return this.questionResponseByPublicId.get(publicId);
  }

  addResponse(publicId: string, questionResponseType: QuestionResponseType): void {
    this.questionResponseByPublicId.set(publicId, questionResponseType);
  }

  getKnownQuestions(): Question[] {
    return this.questions
      .filter(q => QuestionResponseType.KNOW === this.questionResponseByPublicId.get(q.publicId));
  }
}
