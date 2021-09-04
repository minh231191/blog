import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { ImageUploadAdapter } from 'src/app/services/Image-uploader-adapter';

@Component({
  selector: 'app-ckeditor',
  templateUrl: './ckeditor.component.html',
  styleUrls: ['./ckeditor.component.scss']
})
export class CkeditorComponent implements OnInit {

  public Editor = ClassicEditor;
  public config = {
    toolbar: {
      items: [
        'heading',
        '|',
        'bold',
        'italic',
        'alignment',
        '|',
        'bulletedList',
        'numberedList',
        'blockQuote',
        '|',
        'link',
        'imageUpload',
        'codeBlock',
        '|',
        'undo',
        'redo'
      ]
    },
    alignment: {
      options: ['left', 'right', 'center', 'justify']
    },
    removePlugins: ['MediaEmbed'],
    mediaEmbed: {}
  };
  data = '';

  constructor() { }

  ngOnInit(): void {

  }

  onReady(editor: any): void {
    editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader: any ) => {
        return new ImageUploadAdapter( loader );
    };
  }

  getData(): string {
    return this.data;
  }

  setData(data: string): void {
    this.data = data;
  }

}
