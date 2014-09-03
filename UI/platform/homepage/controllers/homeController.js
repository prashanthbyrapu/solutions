angular.module('home', ['ui.bootstrap', "ngRoute", 'ngGrid'])
    .controller("homeController", function ($scope, DataService) {
        $scope.studentsNav = [ "New Admission" , "Get Students" , "Reports"];
        $scope.classesNav = ["Classes Administration" ]
        $scope.items = [
            "The first choice!",
            "And another choice for you.",
            "but wait! A third!"
        ];
        $scope.save = function () {
            $scope.data = DataService.getNewDocument(true);
            console.log($scope.data);
            console.log("In save handler");
        }
    })
    .controller("BranchController", function ($scope, DataService) {

        $scope.data = {};
        $scope.data.address = {};

        $scope.save = function () {

            console.log($scope.data);
            console.log("In save handler of branch controller");
        }
    })
    .controller("BaseController", function ($scope, DataService) {


        $scope.save = function () {

            console.log($scope.data);
            console.log("In save handler of branch controller");
        }
    })
    .controller("StudentsEditController", function ($scope, $location, $routeParams, DataService) {
        var id = $routeParams.id;
        DataService.get("Student", id).then(function (data) {
            $scope.document = data;
            console.log($scope.data);
        });

        $scope.save = function () {
            var saveDocument = new DocumentSave("Student", $scope.document, DataService, MessageService, $scope);
            saveDocument.save();

        };

    })

    .controller("ClassEditController", function ($scope, $location, $routeParams, DataService) {
        var id = $routeParams.id;
        DataService.get("SchoolClass", id).then(function (data) {
            $scope.document = data;
            console.log($scope.data);
        });

        $scope.save = function () {
            var saveDocument = new DocumentSave("SchoolClass", $scope.document, DataService, MessageService, $scope);
            saveDocument.save();

        };

    })

    .controller("ClassTypeEditController", function ($scope, $location, $routeParams, DataService, MessageService) {
        console.log("In Class Type edit controller");

        var id = $routeParams.id;
        DataService.get("ClassType", id).then(function (data) {
            $scope.document = data;
            $scope.title = "Class Type : " + $scope.document.typeDescription;
            console.log($scope.data);
        });
        $scope.save = function () {
            var saveDocument = new DocumentSave("ClassType", $scope.document, DataService, MessageService, $scope);
            saveDocument.save();

        };

    })

    .controller("ClassTypeOverviewController", function ($scope, $location, $routeParams, DataService) {
        var id = $routeParams.id;
        DataService.getList("ClassType").then(function (data) {
            $scope.data = data;
            console.log($scope.data);
        });

        $scope.createClassType = function () {
            $location.path("/c/ClassType");
        };

    })

    .controller("ClassTypeCreateController", function ($scope, $location, $routeParams, DataService, MessageService) {
        $scope.title = "Create New Class Type"
        $scope.subtitle = "";
        $scope.save = function () {
            console.log("In save handler students controller");
            //debugger;

            var saveDocument = new DocumentSave("ClassType", $scope.document, DataService, MessageService, $scope);
            saveDocument.save();

        };

    })

    .controller("ClassCreateController", function ($scope, $location, $routeParams, DataService, MessageService) {
        $scope.title = "New  Class"
        $scope.subtitle = "";
        $scope.ClassType = "ClassType";
        $scope.document = {"feeDetails" :[]};
        $scope.gridOptions = {
            data: 'document.feeDetails',
            enableCellSelection: true,
            enableRowSelection: false,
            enableCellEdit: true,
            columnDefs: [
                {field: 'feeType', displayName: 'Fee Type', enableCellEdit: true},
                {field: 'amount', displayName: 'Amount', enableCellEdit: true},
                {field: 'Actions', displayName:'Actions', cellTemplate: '<button  ng-click="removeRow($index)"><i class="fa fa-trash-o"></i> </buttond>'}
            ]
        };
        $scope.addFee = function () {
            if(  $scope.document.feeDetails){
                var feeDetails = { "feeType" : "", "amount" :0};
                $scope.document.feeDetails.push(feeDetails);
            }else{
                $scope.document.feeDetails = new Array();
                var feeDetails = { "feeType" : "", "amount" :0};
                $scope.document.feeDetails.push(feeDetails);
            }
        };
        $scope.removeRow = function() {
            var index = this.row.rowIndex;
            $scope.gridOptions.selectItem(index, false);
            $scope.document.feeDetails.splice(index, 1);
        };

        $scope.save = function () {
            console.log("In save handler students controller");
            //debugger;

            var saveDocument = new DocumentSave("SchoolClass", $scope.document, DataService, MessageService, $scope);
            saveDocument.save();

        };

    })

    .controller("ClassesListController", function ($scope, $location, $routeParams, DataService, MessageService) {
        var id = $routeParams.id;
        DataService.getList("SchoolClass").then(function (data) {
            $scope.data = data;
            console.log($scope.data);

        });

        $scope.edit = function (id) {
            $location.path("/e/Class/" + id)
        };

    })

    .controller("StudentsListController", function ($scope, $location, $routeParams, DataService, MessageService) {
        var id = $routeParams.id;
        DataService.getList("Student").then(function (data) {
            $scope.data = data;
            console.log($scope.data);

        });

        $scope.edit = function (id) {
            $location.path("/e/Student/" + id)
        };

    })
    .controller("QuickAdmissionController", function ($scope, $location, $routeParams, DataService, MessageService) {
        $scope.save = function () {
            console.log("In save handler students controller");

            var saveDocument = new DocumentSave("Student", $scope.document, DataService, MessageService, $scope);
            saveDocument.save();

        };
    })
    .controller("StudentsController", function ($scope, DataService, MessageService, $location) {

        $scope.opened = false;
        $scope.open = function ($event) {

            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.exit = function () {
            $location.path("/");
        };


        //console.log($scope.data);
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

    .directive('valueHelp', function () {

        return {
            restrict: 'E',
            scope: {
                collectionName: '@',
                field: "="

            },
            controller: function ($scope, DataService) {
                debugger;
                DataService.valueHelp("ClassType").then(function (data) {
                    $scope.texts = data;
                    console.log($scope.texts);
                });

            },
            template: '<select ng-model="field" ng-options="obj.id as obj.text for obj in texts"></select>'
        };
    })

    .directive('pageHeader', function () {
        return {
            restrict: 'E',
            //controller: "StudentsController",
            scope: {
                title: '@',
                subtitle: '@'
            },
            templateUrl: '/platform/homepage/directives/PageHeader.html'
            //template :"<div class='page-header text-center'><h1>{{title}} <small> {{subTitle}}</small></h1></div>"
        };
    })
    .directive('buttonsDiv', function () {
        return {
            restrict: 'E',
            //controller: "StudentsController",
            scope: {
                save: '@',
                exit: '@'
            },
            templateUrl: '/platform/homepage/directives/buttonsDiv.html'
            //template :"<div class='page-header text-center'><h1>{{title}} <small> {{subTitle}}</small></h1></div>"
        };
    })
    .directive('messagesDiv', function () {
        return {
            restrict: 'E',
            controller: function ($scope, MessageService) {

                $scope.$on("messagesChangedEvent", function () {
                    console.log("MEssages changed event handler");
                    $scope.messages = MessageService.getMessages();
                    if ($scope.messages && $scope.messages.length > 0) {
                        $scope.showAlertDiv = true;
                        $scope.closeable = true;
                    }
                });

                $scope.$on("NavigationChangeEvent", function () {
                    $scope.showAlertDiv = false;
                    $scope.messages = null;
                });

                $scope.close = function () {
                    $scope.showAlertDiv = false;
                }

            },
            //templateUrl: '/platform/homepage/directives/PageHeader.html'
            template: "<div> <alert ng-repeat='alert in messages' type='alert.type' >{{alert.message}}</alert></div>"
            //template : "<div>{{messages}}</div>"
        };
    })
    .directive('quickAdmission', function () {
        return {
            restrict: 'E',

            templateUrl: '/modules/students/directives/quickadmission.html',
            // transclude: true,
            controller: "StudentsController"
            //template :"<div class='page-header text-center'><h1>{{title}} <small> {{subTitle}}</small></h1></div>"
        };
    })
    .directive('homeNavigation', function () {
        return {
            restrict: 'E',


            // transclude: true,
            controller: function ($scope, NavigationService) {
                //debugger;
                $scope.navs = NavigationService.getNavigations();

            },
            templateUrl: 'platform/homepage/directives/HomePageNavigation.html'
            //template :"<div class='page-header text-center'><h1>{{title}} <small> {{subTitle}}</small></h1></div>"
        };
    })
    .directive('addressDetails', function () {
        return {
            restrict: 'E',
            scope: {
                address: "="
            },
            templateUrl: 'platform/homepage/directives/addressdetails.html'
            //template :"<div class='page-header text-center'><h1>{{title}} <small> {{subTitle}}</small></h1></div>"
        };
    })
    .directive('personnelDetails', function () {
        return {
            restrict: 'E',
            scope: {
                document: "="
            },
            templateUrl: 'platform/homepage/directives/personneldata.html'
            //template :"<div class='page-header text-center'><h1>{{title}} <small> {{subTitle}}</small></h1></div>"
        };
    })
    .config(['$routeProvider',
        function ($routeProvider) {

            $routeProvider.when('/newadmission', {templateUrl: '/modules/students/newadmission.html', controller: "StudentsController"}).
                when('/quickadmission', {templateUrl: '/modules/students/quickadmission.html', controller: "QuickAdmissionController"}).
                //  when('/studentsoverview', {templateUrl: '/modules/students/studentdetails.html', controller: "StudentsController"}).
                when('/home', {templateUrl: '/platform/homepage/homePage.html', controller: "homeController"}).
                when('/e/Student/:id', {templateUrl: '/modules/students/studentdetails.html', controller: "StudentsEditController"}).
                when('/schoolbranches', {templateUrl: '/platform/settings/branches/branch.html', controller: "BranchController"}).
                when('/o/Student/', {templateUrl: '/modules/students/studentsoverview.html', controller: "StudentsListController"}).
                when('/o/ClassType', {templateUrl: '/modules/classes/partials/classtypeoverview.html', controller: "ClassTypeOverviewController"}).
                when('/c/ClassType', {templateUrl: '/modules/classes/partials/classtypesetup.html', controller: "ClassTypeCreateController"}).
                when('/e/ClassType/:id', {templateUrl: '/modules/classes/partials/classtypesetup.html', controller: "ClassTypeEditController"}).
                when('/o/Class', {templateUrl: '/modules/classes/partials/classesoverview.html', controller: "ClassesListController"}).
                when('/c/Class', {templateUrl: '/modules/classes/partials/class.html', controller: "ClassCreateController"}).
                when('/e/Classdd/:id', {templateUrl: '/modules/classes/partials/class.html', controller: "ClassEditController"}).
                otherwise({redirectTo: '/home'})


        }])
    .factory('DataService', function ($http, $cacheFactory) {
        var cache = $cacheFactory("newDocumentCache");
        return {
            getList: function (collection) {
                //since $http.get returns a promise,
                //and promise.then() also returns a promise
                //that resolves to whatever value is returned in it's
                //callback argument, we can return that.
                return $http.get('/data/' + collection + "?client=100").then(function (result) {
                    console.log(result.data);
                    return result.data;
                }, function (result) {
                    console.log(result.data);
                    return result.data;
                });
            },

            get: function (collection, id) {
                //since $http.get returns a promise,
                //and promise.then() also returns a promise
                //that resolves to whatever value is returned in it's
                //callback argument, we can return that.
                return $http.get('/data/' + collection + '/' + id + "?client=100").then(function (result) {
                    console.log(result.data);
                    return result.data;
                }, function (result) {
                    //console.log(result.data);
                    //return result.data;
                    return {};
                });
            },

            getNewDocument: function (createNew) {

                if (createNew) {
                    cache.put("document", {});
                }
                return cache.get("document");
            },

            create: function (collection, documentData) {
                return $http.put('/data/' + collection + "?client=100", documentData).then(function (result) {
                    console.log(result.data);
                    return result.data;
                }, function (result) {
                    console.log(result.data);
                    return result.data;
                });
            },

            valueHelp: function (collection) {
                return $http.get('/help/texts/' + collection + "?client=100").then(function (result) {
                    console.log(result.data);
                    return result.data;
                }, function (result) {
                    //console.log(result.data);
                    return {};
                });
            }
        }
    })

    .factory('MessageService', function ($http, $cacheFactory, $rootScope) {
        var messages = null;

        return {
            addMessage: function (messagesFromServer) {
                messages = messagesFromServer;
                $rootScope.$broadcast("messagesChangedEvent");
            },

            getMessages: function () {
                return messages;
            }
        }
    })
    .factory('NavigationService', function ($http, $cacheFactory, $rootScope) {
        var navigations = new Array();

        var navigation = new Object();
        navigation.title = "Student";
        navigation.details = [
            { "name": "New Admission", "link": "#newadmission", "description": "Create a new student record with complete information"},
            { "name": "Quick Admission", "link": "#quickadmission"},
            { "name": "Students Overview", "link": "#/o/Student/", "description": "Create a new student record with very minimal information"}
        ];
        navigations.push(navigation);

        var navigation = new Object();
        navigation.title = "Classes";
        navigation.details = [
            { "name": "Class Administration", "link": "#classes"}
        ];
        navigations.push(navigation);

        var navigation = new Object();
        navigation.title = "Attendance";
        navigation.details = [
            { "name": "Mark Attendance", "link": "#markattendance"},
            {"name": "Attendance Reports", "link": "#attendancereports"}
        ];
        navigations.push(navigation);

        var navigation = new Object();
        navigation.title = "Fee";
        navigation.details = [
            { "name": "Fee Payment", "link": "#feepayment"},
            {"name": "Fee Dues", "link": "#feedues"}
        ];
        navigations.push(navigation);


        var navigation = new Object();
        navigation.title = "System Setup";
        navigation.details = [
            {
                "name": "School Branches",
                "link": "#schoolbranches"
            },
            {
                "name": "Class Types",
                "link": "#/o/ClassType"
            }
        ];
        navigations.push(navigation);


        return {
            getNavigations: function () {
                return navigations;
            }


        }
    });

function DocumentSave(collection, documentData, DataService, MessageService, $scope) {
    this.collection = collection;
    this.documentData = documentData;
    this.dataService = DataService;
    this.messageService = MessageService;
    this.scope = $scope;
    this.save = function () {

        var documentSave = this;
        console.log("in save :" + this.collection + ", " + this.documentData);
        this.dataService.create(this.collection, this.documentData).then(function (data) {
            //$scope.data = data;
            documentSave.scope.showAlertDiv = true;
            var messages = new Array();
            if (data.successMessages) {

                for (var i = 0; i < data.successMessages.length; i++) {
                    var message = {};
                    message.type = "success";
                    message.message = data.successMessages[i];
                    messages.push(message);
                }

            }

            if (data.errorMessages) {
                for (var i = 0; i < data.errorMessages.length; i++) {
                    var message = {};
                    message.type = "error";
                    message.message = data.errorMessages[i];
                    messages.push(message);
                }

            }
            // if(messages.lengh > 0 ){
            documentSave.messageService.addMessage(messages);
            // }

        });

    };

}

