import { Pipe, PipeTransform } from '@angular/core';
import hljs from 'highlight.js';

@Pipe({
  name: 'highlightCode'
})
export class HighlightCodePipePipe implements PipeTransform {

  transform(htmlMarkup: string): string {
    const preCodeRegex = /<pre><code(?: class="language-(.*)")?>([\s\S]*?)<\/code><\/pre>/g;
    if (htmlMarkup === undefined) {
      htmlMarkup = '';
    }
    htmlMarkup = htmlMarkup.replace(/&lt;/g, '<').replace(/&gt;/g, '>');

    // tslint:disable-next-line: variable-name
    return htmlMarkup.replace(preCodeRegex, (_match, languageName, codeBlock) => {
      let codeBlockHighlighted: string;

      if (!languageName) {
        codeBlockHighlighted = hljs.highlightAuto(codeBlock).value;
      } else {
        codeBlockHighlighted = hljs.highlight(codeBlock, {language: languageName, ignoreIllegals: true}).value;
      }

      return '<pre><code class="hljs">' + codeBlockHighlighted + '</pre></code>';
    });
  }

}
