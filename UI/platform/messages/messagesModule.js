angular.module('platformModule', ['ui.bootstrap', "ngRoute"])
    .controller("messagesDivcontroller", function ($scope, DataService) {
        $scope.isCollapsed = true;
        $scope.close = function () {
            $scope.isCollapsed = true;
        }
    })
    .directive('navDropdown', function () {

        return {
            restrict: 'E',
            controller: function ($scope, NavigationService) {
                //debugger;
                $scope.navs = NavigationService.getNavigations();

            },
            templateUrl: '/platform/homepage/directives/NavDropDown.html'
        };
    })

;