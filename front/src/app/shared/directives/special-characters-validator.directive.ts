import { Directive } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl } from '@angular/forms';
import { SPECIAL_CHARACTERS } from '../util/patterns.util';

@Directive({
  // tslint:disable-next-line: directive-selector
  selector: '[noSpecialCharacters][formControlName], [noSpecialCharacters][formControl], [noSpecialCharacters][ngModel]',
  providers: [{provide: NG_VALIDATORS, useExisting: SpecialCharactersDirective, multi: true}],
})
export class SpecialCharactersDirective implements Validator {

  validate(control: AbstractControl): { [key: string]: any } | null {
    if (control.value && SPECIAL_CHARACTERS.test(control.value)) {
      setTimeout(() => control.setValue(control.value.replace(SPECIAL_CHARACTERS, '')));
    }
    return null;
  }
}
