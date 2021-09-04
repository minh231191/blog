import { BaseModel } from './../model/BaseModel';

export class ListTransfer<T extends BaseModel> {

  availableElements: T[] = [];
  selectedElements: T[] = [];
  addedElements: T[] = [];

  constructor() {}

  isInSelectedElements(element: T): boolean {
    return this.selectedElements.findIndex(item => item.id === element.id) !== -1;
  }

  toogleActiveElement(element: T): void {
    if (this.selectedElements.find(item => item.id === element.id)) {
      this.selectedElements = this.selectedElements.filter(item => item.id !== element.id);
    } else {
      let intersect: T[] = [];
      if (this.addedElements.find(item => item.id === element.id)) {
        intersect = this.selectedElements.filter(item => this.availableElements.find(ae => ae.id === item.id));
      } else if (this.availableElements.find(item => item.id === element.id)) {
        intersect = this.selectedElements.filter(item => this.addedElements.find(ae => ae.id === item.id));
      }
      this.selectedElements = this.selectedElements.filter(item => !intersect.find(ie => ie.id === item.id));
      this.selectedElements.push(element);
    }
  }

  moveToTheLeft(): void {
    this.addedElements = this.addedElements.filter(ae => {
      if (!this.isInSelectedElements(ae)) {
        return true;
      } else {
        this.availableElements.push(ae);
        return false;
      }
    });
    this.selectedElements.length = 0;
  }

  moveToTheRight(): void {
    this.availableElements = this.availableElements.filter(ae => {
      if (!this.isInSelectedElements(ae)) {
        return true;
      } else {
        this.addedElements.push(ae);
        return false;
      }
    });
    this.selectedElements.length = 0;
  }

}
