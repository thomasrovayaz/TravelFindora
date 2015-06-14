/**
 * Created by thomas on 14/06/15.
 */

function addTravelPhoto(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/image/add";
}

function addTravelVideo(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/movie/add";

}

function addTravelStory(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/story/add";

}

function addTravelPlace(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/place/add";

}

function addFindora(travelId) {
    window.location.href = "/travel/" + travelId + "/toto/add";
}
