'use strict';

/**
 * File view comment controller.
 */

 angular.module('docs').controller('FileViewComment', function ($scope, file, $uibModalInstance){
    $scope.file = file;
    $scope.close = function(file) {
    if (file === null) {
      $uibModalInstance.close(null);
      return;
    }
    $uibModalInstance.close(file);
  }
    $scope.comments = [];
    Restangular.one('file/' + file.id + '/rating/list').get()
      .then(function (res) {
        $scope.comments = res.file_ratings;
        console.log($scope.comments);
      });
 });