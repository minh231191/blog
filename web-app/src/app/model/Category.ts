import { BaseModel } from './BaseModel';
import { CategoryStatus } from './CategoryStatus';

export interface Category extends BaseModel {

  name: string;
  description: string;
  status: CategoryStatus;
  createdBy: string;
  createdDate: Date;
  categories: Category[];

}
