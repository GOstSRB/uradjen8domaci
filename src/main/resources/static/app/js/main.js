var wafepaApp = angular.module("wafepa", ["ngRoute"]);

wafepaApp.controller("HomeCtrl", function($scope){
	$scope.message = "Hello JWD!";
});
// kontroler koji omogucava poziv http servreru i listanje svih activities prebacivanje u scope
// kontroler zaduzen za stranicu activities.html
/*
wafepaApp.controller("ActivitiesCtrl", function($scope, $http){
	var baseUrl = "/api/activities";
	$scope.activities=[];
	var getActivities = function (){
		var promise = $http.get(baseUrl);
		promise.then(
				function success(res) {
					$scope.activities = res.data;
				},
				function error () {
				}
		);
	} 
	getActivities();
})

*/
wafepaApp.controller("CountriesCtrl", function($scope, $http){
	var baseUrl = "https://restcountries.eu/rest/v2/all";
	$scope.countries=[];
	var getCountries = function (){
		var promise = $http.get(baseUrl);
		promise.then(
				function success(res) {
					$scope.countries = res.data;
				},
				function error () {
				}
		);
	} 
	getCountries();



$scope.findBy = function(name){
	var Url = "https://restcountries.eu/rest/v2/name/";
	$scope.countries=[];
	
	var getCountries = function(){
		var promise = $http.get(Url + name);
		promise.then(
				function success(res) {
					$scope.countries = res.data;
				},
				function error () {
					
				}
				);
	}
}
//	getCountries();
//	
//	
//});

});






//kontroler koji omogucava poziv http servreru i listanje svih usera prebacivanje u scope
//kontroler zaduzen za stranicu users.html

wafepaApp.controller("UsersCtrl", function($scope, $http, $location){
	var baseUrl = "/api/users";
	$scope.users=[];
	var getUsers = function (){
		var promise = $http.get(baseUrl);
		promise.then(
				function success(res) {
					$scope.users = res.data;
				},
				function error () {
				}
		);
	} 
	getUsers();
	
	$scope.goToEdit = function (id) {
		$location.path("/users/edit/" +id);
	}
	
	$scope.goToAdd = function () {
		$location.path("/users/add");
	}
	
	$scope.goToDelete = function(id){
		$http.delete(baseUrl+"/"+ id).then(
		
		function success(res) {
			getUsers();
		},
		function error (res){
			alert("Couldn't delete user.")
		}
		);
	}
});


wafepaApp.controller("EditUserCtrl", function ($scope, $http, $routeParams, $location){
	var url = "/api/users/" + $routeParams.id;

	$scope.user = {};
	$scope.user.firstname = "";
	$scope.user.lastname = "";
	$scope.user.email = "";
	$scope.user.password = "";
	
	
	var getUser = function () {
		
		var promise = $http.get(url);
		promise.then (
				function success(res) {
					$scope.user = res.data;
				},
				function error () {
					alert ("Couldt fetch user.");
				}
		);
	}
	
	getUser();
	
	$scope.doEdit = function () {
		$http.put(url, $scope.user).then(
				function success(){
					$location.path("/users");
				},
				function error() {
				}
		);
	}
});

wafepaApp.controller("AddUserCtrl", function ($scope, $http, $location){
	
	var url = "/api/users";
	$scope.user = {};
	$scope.user.email = "";
	$scope.user.firstname = "";
	$scope.user.lastname = "";
	
	$scope.user.password = "";
		
	$scope.doAdd = function () {
		var promise = $http.post(url, $scope.user);
		promise.then(
				function succes(res){
					$location.path("/users");
				},
				function error(){
					alert("Couldn`t save user");
				}
		);
	}
	
	
});



wafepaApp.controller ("ActivitiesCtrl", function($scope, $http, $location){
	var baseUrl= "/api/activities";
	$scope.activities=[];

	var getActivities = function () {
		var promise = $http.get(baseUrl);
		promise.then(
				function success(res) {
					$scope.activities = res.data;
				},
				function error () {
					
				}
		);
	}
	getActivities();
	
	$scope.goToEdit = function (id) {
		$location.path("/activities/edit/" +id);
	}
	
	$scope.goToAdd = function () {
		$location.path("/activities/add");
	}
	$scope.goToDelete = function(id){
		$http.delete(baseUrl+"/"+ id).then(
		
		function success(res) {
			getActivities();
		},
		function error (res){
			alert("Couldn't delete activity.")
		}
		);
	}
	$scope.orderByMe = function(a) {
	    $scope.myOrderBy = a;
	  }
	
	
});

wafepaApp.controller("EditActivityCtrl", function ($scope, $http, $routeParams, $location){
	var url = "/api/activities/" + $routeParams.id;

	$scope.activity = {};
	$scope.activity.name = "";
	
	var getActivity = function () {
		
		var promise = $http.get(url);
		promise.then (
				function success(res) {
					$scope.activity = res.data;
				},
				function error () {
					alert ("Couldt fetch activity.");
				}
		);
	}
	
	getActivity();
	
	$scope.doEdit = function () {
		$http.put(url, $scope.activity).then(
				function success(){
					$location.path("/activities");
				},
				function error() {
				}
		);
	}
});

wafepaApp.controller("AddActivityCtrl", function ($scope, $http, $location){
	
	var url = "/api/activities";
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	$scope.doAdd = function () {
		var promise = $http.post(url, $scope.activity);
		promise.then(
				function succes(res){
					$location.path("/activities");
				},
				function error(){
					alert("Couldn`t save activity");
				}
		);
	}
	
	
});

wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
				controller : "HomeCtrl"
		})
		.when('/countries', {
			templateUrl : '/app/html/countries.html'
		})
		.when('/users', {
			templateUrl : '/app/html/users.html'
		})
		.when('/users/add', {
		templateUrl : '/app/html/add-user.html'
		})
		.when('/users/edit/:id', {
			templateUrl : '/app/html/edit-user.html'
		})
		.when('/activities', {
			templateUrl : '/app/html/activities.html'
		})
		.when('/activities/add', {
			templateUrl : '/app/html/add-activity.html'
		})
		.when('/activities/edit/:id', {
			templateUrl : '/app/html/edit-activity.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
