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
  $scope.comments = [{username: "firstu", academicRating: 1, awardsRating: 2, activityRating:3, experienceRating:4, overallRating:5, comment:"hi" },
    {username: "firstu", academicRating: 3, awardsRating: 3, activityRating:3, experienceRating:4, overallRating:5, comment:"lo" },
    {username: "firstu", academicRating: 5, awardsRating: 5, activityRating:5, experienceRating:4, overallRating:5, comment:"eee" },
    {username: "firstu", academicRating: 4, awardsRating: 5, activityRating:5, experienceRating:4, overallRating:5, comment:"xxx fghjh gfdfghgf,, tygvbn mqwertyuio xxfghjh gfdf ghgf,,t ygvbn xxfghjh gfdfghgf,,tygvbn" }  ];
    //following is draft for push new comments
    $scope.newComment = {rating: 5};
    $scope.push = function () {
        $scope.comments.push($scope.newComment);
    };


 });