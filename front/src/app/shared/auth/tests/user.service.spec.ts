import { ApiService } from '../../services/api.service';
import { LocalStorageStub } from '../../tests/LocalStorageStub';
import { UserService } from '../user.service';

describe('UserService', () => {
    let userService: UserService;

    let localStorage: LocalStorageStub;

    let  httpSpy: any;

    beforeEach(() => {

        httpSpy = jasmine.createSpyObj('HttpClient', ['get', 'put', 'post', 'request', 'delete']);

        localStorage = new LocalStorageStub();

        const apiService = new ApiService(httpSpy, localStorage);

        userService = new UserService(localStorage, apiService);
    });

    describe('user has ADMIN role', () => {
        beforeEach(() => {
            localStorage.store('user', {
                role: 'ADMIN'
            });
        });

        it ('#hasAnyRole with ADMIN role as argument should return true', () => {
            expect(userService.hasAnyRole(['ADMIN'])).toBeTrue();
            expect(userService.hasAnyRole(['USER', 'ADMIN'])).toBeTrue();
        });

        it ('#hasAnyRole without ADMIN role as argument should return false', () => {
            expect(userService.hasAnyRole([])).toBeFalse();
            expect(userService.hasAnyRole(['USER', 'GUEST'])).toBeFalse();
        });

        it('#hasRole with same user role returns true', () => {
            expect(userService.hasRole('ADMIN')).toBeTrue();
        });

        it('#hasRole with other role than the user returns false', () => {
            expect(userService.hasRole('')).toBeFalse();
            expect(userService.hasRole(undefined)).toBeFalse();
            expect(userService.hasRole('GUEST')).toBeFalse();
        });

        it ('#isAdmin should return true', () => {
            expect(userService.isAdmin()).toBeTrue();
        });
    });

    it ('#storeAuthenticationUser should store user', () => {
        localStorage.store('user', { name: 'John Doe', role: 'GUEST' });

        // NB: Just as a guarantee
        expect(localStorage.retrieve('user').name).toBe('John Doe');

        userService.storeAuthenticationUser({
            name: 'Jane Doe',
            role: 'ADMIN'
        });

        expect(localStorage.retrieve('user').name).toBe('Jane Doe');
    });
});
