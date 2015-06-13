function CustomMarker(latlng, map, args) {
	this.latlng = latlng;	
	this.args = args;	
	this.setMap(map);	
}

CustomMarker.prototype = new google.maps.OverlayView();

CustomMarker.prototype.draw = function() {
	
	var self = this;
	
	var div = this.div;

    var width = self.args.nb_travel / 15 * 30;
    var height = self.args.nb_travel / 15 * 30;

    var widthDiv = width + 14;
    var heightDiv = height + 14 + 11;
	
	if (!div) {
        var divPin = document.createElement('div');
        var divPulse = document.createElement('div');
        var divPinText = document.createElement('div');

        var marginCenter = self.args.nb_travel / 15 * 8;
        var fontSizeCenter = self.args.nb_travel / 15 * 14;

        divPulse.className = 'pulse';
	
		div = this.div = document.createElement('div');
        div.className = 'marker';
        div.style.width = widthDiv + 'px';
        div.style.height = heightDiv + 'px';
        div.style.position = 'absolute';
        div.style.cursor = 'pointer';

        divPin.className = 'pin';
        divPin.style.width = width + 'px';
        divPin.style.height = height + 'px';
        //div.style.margin = "-" + width + 'px 0 0 -' + (width/2 + 5) + "px";

        divPinText.className = 'pin_text';
        divPinText.innerHTML = self.args.nb_travel + '';
        divPinText.style.top = ((fontSizeCenter/2) + 14) + 'px';
        divPinText.style.height = fontSizeCenter + 'px';
        divPinText.style.fontSize = fontSizeCenter + 'px';
		
		if (typeof(self.args.marker_id) !== 'undefined') {
			div.dataset.marker_id = self.args.marker_id;
            divPin.id = 'marker_' + self.args.marker_id;
		}
		
		google.maps.event.addDomListener(div, "click", function(event) {
			alert('You clicked on a custom marker!');			
			google.maps.event.trigger(self, "click");
		});
		
		var panes = this.getPanes();
		panes.overlayImage.appendChild(div);
        div.appendChild(divPin);
        div.appendChild(divPinText);
        div.appendChild(divPulse);
	}
	
	var point = this.getProjection().fromLatLngToDivPixel(this.latlng);
	
	if (point) {
		div.style.left = (point.x - (widthDiv)/2) + 'px';
		div.style.top = (point.y - (heightDiv)) + 'px';
	}
};

CustomMarker.prototype.remove = function() {
	if (this.div) {
		this.div.parentNode.removeChild(this.div);
		this.div = null;
	}	
};

CustomMarker.prototype.getPosition = function() {
	return this.latlng;	
};