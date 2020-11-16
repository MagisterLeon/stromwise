export interface Question {
  publicId: string;
  question: string;
  answer?: string;
  know?: number;
  notSure?: number;
  notKnow?: number;
}
