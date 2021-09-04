import { BaseModel } from './BaseModel';

export interface TagDisplay extends BaseModel {

  name: string;
  createdBy: string;
  createdDate: Date;

}
