import {Injectable} from '@angular/core';
import {QuestionResponseType} from './question-response-type';
import {Question} from './question';

@Injectable({
  providedIn: 'root'
})
export class QuestionsStore {

  category: string;
  questions: Question[];
  private questionResponseByPublicId: Map<string, QuestionResponseType> = new Map<string, QuestionResponseType>();

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

  getNotSureQuestions(): Question[] {
    return this.questions
      .filter(q => QuestionResponseType.NOT_SURE === this.questionResponseByPublicId.get(q.publicId));
  }

  getNotKnowQuestions(): Question[] {
    return this.questions
      .filter(q => QuestionResponseType.DONT_KNOW === this.questionResponseByPublicId.get(q.publicId));
  }

  getUnknownQuestions(): Question[] {
    return [...this.getNotSureQuestions(), ...this.getNotKnowQuestions()];
  }

  clear(): void {
    this.category = '';
    this.questions = undefined;
    this.questionResponseByPublicId.clear();
  }
}
