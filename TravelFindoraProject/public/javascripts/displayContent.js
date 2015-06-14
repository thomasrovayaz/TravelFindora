/**
 * Created by thomas on 14/06/15.
 */
function like(contentId) {
    $.ajax({
        url : "http://localhost:9000/content/" + contentId + "/like",

        success : function(donnee){
        }
    });

    return false;
}
function dislike(contentId) {
    $.ajax({
        url : "http://localhost:9000/content/" + contentId + "/dislike",

        success : function(donnee){
        }
    });
    return false;
}