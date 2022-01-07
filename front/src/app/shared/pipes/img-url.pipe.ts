import { Pipe, PipeTransform } from '@angular/core';

import { environment } from '../../../environments/environment';

@Pipe({
  name: 'imgUrl'
})
export class ImgUrlPipe implements PipeTransform {

  transform(imgPath: string): any {

    if (imgPath && imgPath.startsWith('./')) {
      return imgPath;
    } else if (imgPath) {
      return `${environment.uploadUrl}/${imgPath}`;
    }

    return '';
  }
}
