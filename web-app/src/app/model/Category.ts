import { CategoryStatus } from './CategoryStatus';

export interface Category {

  id: number;
  name: string;
  description: string;
  status: CategoryStatus;
  createdBy: string;
  createdDate: Date;
  categories: Category[];

}
