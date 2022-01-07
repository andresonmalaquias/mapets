import { UserRouteAccessService } from '../user-route-access-service';

describe('UserRouteAccessService', () => {
  let userRouteAccessService: UserRouteAccessService;

  let routerSpy: any;

  let authSpy: any;
  let userSpy: any;

  beforeEach(() => {
    routerSpy = jasmine.createSpyObj('Router', ['navigate', 'navigateUrl']);

    authSpy = jasmine.createSpyObj('AuthService', ['isAuthenticated']);
    userSpy = jasmine.createSpyObj('UserService', ['hasAnyRole']);

    userRouteAccessService = new UserRouteAccessService(routerSpy, userSpy, authSpy);
  });

  it ('#canLoad should return false when user is not authenticated', () => {
    authSpy.isAuthenticated.and.returnValue(false);

    expect(userRouteAccessService.canLoad()).toBeFalse();
  });

  it ('#canLoad should return true when user is authenticated', () => {
    authSpy.isAuthenticated.and.returnValue(true);

    expect(userRouteAccessService.canLoad()).toBeTrue();
  });

  it ('#checkLogin should return true when no roles were passed', () => {
    expect(userRouteAccessService.checkLogin(null)).toBeTrue();
    expect(userRouteAccessService.checkLogin(undefined)).toBeTrue();
    expect(userRouteAccessService.checkLogin([])).toBeTrue();
    expect(userRouteAccessService.checkLogin(0)).toBeTrue();
  });

  it ('#checkLogin should go to login when user is not authenticated', () => {
    authSpy.isAuthenticated.and.returnValue(false);

    const result = userRouteAccessService.checkLogin(1);

    const [[path]] = routerSpy.navigate.calls.first().args;

    expect(path).toBe('/login');
    expect(result).toBeFalse();
  });

  describe('when user is authenticated', () => {
    beforeEach(() => {
      authSpy.isAuthenticated.and.returnValue(true);
    });

    it ('#checkLogin should go to root route when the current role does not match the route roles', () => {
      userSpy.hasAnyRole.and.returnValue(false);

      const result = userRouteAccessService.checkLogin(1);

      const [[path]] = routerSpy.navigate.calls.first().args;

      expect(path).toBe('/');
      expect(result).toBeFalse();
    });

    it ('#checkLogin should return true when user role matches route roles', () => {
      userSpy.hasAnyRole.and.returnValue(true);

      expect(userRouteAccessService.checkLogin(1)).toBeTrue();
    });

  });
});
