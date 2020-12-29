import {Injectable} from '@angular/core';
import {QuestionResponseType} from './question-response-type';
import {Question} from './question';

@Injectable({
  providedIn: 'root'
})
export class QuestionsStore {

  category: string;
  questions: Question[];
  private questionsToResponses: Map<string, QuestionResponseType> = new Map<string, QuestionResponseType>();

  constructor() {
  }

  hasAllResponses(): boolean {
    return this.questions && this.questions.length === this.questionsToResponses.size;
  }

  getResponse(question: string): QuestionResponseType {
    return this.questionsToResponses.get(question);
  }

  addResponse(question: string, questionResponseType: QuestionResponseType): void {
    this.questionsToResponses.set(question, questionResponseType);
  }

  getNotSureQuestions(): Question[] {
    const notSureQuestions = this.questions
      .filter(q => QuestionResponseType.NOT_SURE === this.questionsToResponses.get(q.question));
    notSureQuestions.forEach(q => q.notSure != null ? q.notSure++ : q.notSure = 1);
    return notSureQuestions;
  }

  getNotKnowQuestions(): Question[] {
    const notKnowQuestions = this.questions
      .filter(q => QuestionResponseType.DONT_KNOW === this.questionsToResponses.get(q.question));
    notKnowQuestions.forEach(q => q.notKnow != null ? q.notKnow++ : q.notKnow = 1);
    return notKnowQuestions;
  }

  getUnknownQuestions(): Question[] {
    return [...this.getNotSureQuestions(), ...this.getNotKnowQuestions()];
  }

  clear(): void {
    this.category = '';
    this.questions = undefined;
    this.questionsToResponses.clear();
  }
}
