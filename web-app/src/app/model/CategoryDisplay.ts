import { CategoryStatus } from './CategoryStatus';
export interface CategoryDisplay {

  id: number;
  name: string;
  description: string;
  status: CategoryStatus;
  createdBy: string;
  createdDate: Date;
  categories: CategoryDisplay[];

}
