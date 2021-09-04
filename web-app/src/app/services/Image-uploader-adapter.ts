export class ImageUploadAdapter {

  loader: any;
  xhr: any;

  constructor( loader: any ) {
      this.loader = loader;
  }

  upload(): void {
      return this.loader.file
          .then( (file: any) => new Promise( ( resolve, reject ) => {
              this._initRequest();
              this._initListeners( resolve, reject, file );
              this._sendRequest( file );
          } ) );
  }

  abort(): void {
      if ( this.xhr ) {
          this.xhr.abort();
      }
  }

  _initRequest(): void {
      const xhr = this.xhr = new XMLHttpRequest();
      xhr.open( 'POST', 'blog/images/upload', true ); // TODO change the URL
      xhr.responseType = 'json';
      xhr.setRequestHeader('Accept', 'application/json');
  }

  _initListeners( resolve: any, reject: any, file: any ): void {
      const xhr = this.xhr;
      const loader = this.loader;
      const genericErrorText = `Couldn't upload file: ${ file.name }.`;
      xhr.addEventListener( 'error', () => reject( genericErrorText ) );
      xhr.addEventListener( 'abort', () => reject() );
      xhr.addEventListener( 'load', () => {
          const response = xhr.response;
          if ( !response || response.error ) {
              return reject( response && response.error ? response.error.message : genericErrorText );
          }
          resolve( {
              default: response.url
          } );
      } );
      if ( xhr.upload ) {
          xhr.upload.addEventListener( 'progress', (evt: { lengthComputable: any; total: any; loaded: any; }) => {
              if ( evt.lengthComputable ) {
                  loader.uploadTotal = evt.total;
                  loader.uploaded = evt.loaded;
              }
          } );
      }
  }
  _sendRequest( file: any ): void {
      const data = new FormData();
      data.append( 'image', file );
      this.xhr.send( data );
  }
}
