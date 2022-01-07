import { ApiService } from '../../services/api.service';
import { LocalStorageStub } from '../../tests/LocalStorageStub';
import { AuthService } from '../auth.service';

describe('AuthService', () => {
    let authService: AuthService;

    let userSpy: any;
    let httpSpy: any;

    let localStorage: LocalStorageStub;

    beforeEach(() => {
        localStorage = new LocalStorageStub();

        userSpy = jasmine.createSpyObj('UserService', ['getUser']);
        httpSpy = jasmine.createSpyObj('HttpClient', ['get', 'put', 'post', 'request', 'delete']);

        const apiService = new ApiService(httpSpy, localStorage);

        authService = new AuthService(undefined, userSpy, localStorage);
    });

    it ('#isAuthenticated returns true when user is authenticated', () => {
        localStorage.store('token', '12345');
        userSpy.getUser.and.returnValue({
            name: 'John Doe'
        });

        expect(authService.isAuthenticated()).toBeTrue();
    });

    it ('#isAuthenticated returns false when no user in storage', () => {
        localStorage.store('token', '12345');

        expect(authService.isAuthenticated()).toBeFalse();
    });

    it ('#isAuthenticated returns false when no token in storage', () => {
        userSpy.getUser.and.returnValue({
            name: 'John Doe'
        });

        expect(authService.isAuthenticated()).toBeFalse();
    });
});
