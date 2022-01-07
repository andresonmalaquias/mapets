import { Injectable } from '@angular/core';

@Injectable()
export class ValidatorUtil {
  constructor() {}

  isValidCPF(cpf: string) {
    if (!cpf) {
      return false;
    }

    const cleanCPF = cpf.replace(/\.|-|\s/g, '');
    const firstNineDigits = cleanCPF.substring(0, 9);
    const checker = cleanCPF.substring(9, 11);

    if (cleanCPF.length !== 11) {
      return false;
    }

    // Checking if all digits are equal
    let i;
    for (i = 0; i < 10; i++) {
      if ('' + firstNineDigits + checker === Array(12).join(i)) {
        return false;
      }
    }

    const checker1 = this.calcChecker1(firstNineDigits);
    const checker2 = this.calcChecker2('' + firstNineDigits + checker1);

    if (checker.toString() === checker1.toString() + checker2.toString()) {
      return true;
    }

    return false;
  }

  private calcChecker1(firstNineDigits) {
    let sum = null;

    for (let j = 0; j < 9; ++j) {
      sum += firstNineDigits.toString().charAt(j) * (10 - j);
    }

    const lastSumChecker1 = sum % 11;
    const checker1 = lastSumChecker1 < 2 ? 0 : 11 - lastSumChecker1;

    return checker1;
  }

  private calcChecker2(cpfWithChecker1) {
    let sum = null;

    for (let k = 0; k < 10; ++k) {
      sum += cpfWithChecker1.toString().charAt(k) * (11 - k);
    }

    const lastSumChecker2 = sum % 11;
    const checker2 = lastSumChecker2 < 2 ? 0 : 11 - lastSumChecker2;

    return checker2;
  }

  isValidCNPJ(cnpj: string) {
    if (!cnpj) {
      return false;
    }

    const b = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];

    if ((cnpj = cnpj.replace(/[^\d]/g, '')).length !== 14) {
      return false;
    }

    if (/0{14}/.test(cnpj)) {
      return false;
    }

    let i;
    let n;

    for (i = 0, n = 0; i < 12; n += Number(cnpj[i]) * b[++i]) {}

    // tslint:disable-next-line: no-conditional-assignment
    if (cnpj[12] !== ((n %= 11) < 2 ? 0 : 11 - n).toString()) {
      return false;
    }

    for (i = 0, n = 0; i <= 12; n += Number(cnpj[i]) * b[i++]) {}

    // tslint:disable-next-line: no-conditional-assignment
    if (cnpj[13] !== ((n %= 11) < 2 ? 0 : 11 - n).toString()) {
      return false;
    }

    return true;
  }

  isValidZipCode(zipCode) {
    if (!zipCode) {
      return false;
    }

    return /^[0-9]{2}\.?[0-9]{3}-?\d{3}$/.test(zipCode);
  }

  isValidEmail(email) {
    if (email && email.substr(0, email.indexOf('@')).length > 64) {
      return false;
    }

    return true;
  }
}
