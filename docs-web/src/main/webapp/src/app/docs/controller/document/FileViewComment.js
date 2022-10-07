'use strict';

/**
 * File view comment controller.
 */

 angular.module('docs').controller('FileViewComment', function ($scope, file, $uibModalInstance, Restangular){
    Restangular.one('file/' + file.id + '/rating/list').get()
      .then(function (res) {
        const fileRatings = res.file_ratings;
        $scope.file = file;
        $scope.comments = fileRatings.map(rating => {
          return {
            academicRating: rating.academics,
            activityRating: rating.activities,
            awardsRating: rating.awards,
            comment: rating.comment,
            experienceRating: rating.experience,
            overallRating: rating.overall,
            name: rating.creator_name,
          };
        })
    });  

    $scope.close = function() {
      $uibModalInstance.close();
    }
 });