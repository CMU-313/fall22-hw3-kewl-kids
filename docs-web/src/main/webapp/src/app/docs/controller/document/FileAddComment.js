'use strict';

/**
 * File add comment controller.
 */
angular.module('docs').controller('FileAddComment', function ($scope, file, $uibModalInstance) {
  $scope.file = file;
  $scope.close = function(file) {
    if (file === null) {
      $uibModalInstance.close(null);
      return;
    }
    file.academicRating = $scope.academicRating;
    file.awardsRating = $scope.awardsRating;
    file.activityRating = $scope.activityRating;
    file.experienceRating = $scope.experienceRating;
    file.overallRating = $scope.overallRating;
    file.comment = $scope.comment;
    $uibModalInstance.close(file);
  }
  $scope.academicRating = 5;
  $scope.awardsRating = 5;
  $scope.activityRating = 5;
  $scope.experienceRating = 5;
  $scope.overallRating = 5;
  $scope.comment = '';
});