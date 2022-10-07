'use strict';

/**
 * File add comment controller.
 */
angular.module('docs').controller('FileAddComment', function ($scope, file, $uibModalInstance, Restangular) {
  $scope.close = function(canceled) {
    if (canceled) {
      $uibModalInstance.close();
      return;
    }
    console.log($scope.comment);
    Restangular.one('file/' + file.id + '/rating').put({
      academic: '' + $scope.academicRating,
      activities: '' + $scope.activityRating,
      experience: '' + $scope.experienceRating,
      awards: '' + $scope.awardsRating,
      overall: '' + $scope.overallRating,
      comment: '' + $scope.comment,
    }).then(res => {
      $scope.ratingExists = res.rating_exists;
      if (!res.rating_exists) {
        $uibModalInstance.close();
      }
    });
  }
  $scope.ratingExists = false;
  $scope.academicRating = 5;
  $scope.awardsRating = 5;
  $scope.activityRating = 5;
  $scope.experienceRating = 5;
  $scope.overallRating = 5;
  $scope.comment = '';

});